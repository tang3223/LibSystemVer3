package com.gcit.libsystem.dao;

import org.springframework.jdbc.core.*;
import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.Author;

public class AuthorDao extends BaseDao implements ResultSetExtractor<List<Author>> {
		
	public void addAuthor(Author author) throws SQLException {
		final String addAuthor   = "INSERT INTO tbl_author (authorName) VALUE (?)";
		Object[]     authorsInfo = {author.getAuthorName()};
		template.update(addAuthor, authorsInfo);
	}
	
	public void updateAuthor(Author author) throws SQLException{
		final String updateAuthor = "UPDATE tbl_author SET authorName=? WHERE authorId=?";
		Object[]     authorsInfo  = {author.getAuthorName(),author.getAuthorID()};
		template.update(updateAuthor, authorsInfo);
	}
	
	public void deleteAuthor(Author author) throws SQLException{
		final String deleteAuthor = "DELETE FROM tbl_author WHERE authorId=?";
		Object[]     authorsInfo  = {author.getAuthorID()};
		template.update(deleteAuthor, authorsInfo);
	}
	
	public List<Author> readAllAuthors(Integer pageNum) throws SQLException{	
		final String readAuthor = "SELECT * FROM tbl_author LIMIT ?,?";
		if (pageNum != null) {
			setPageNum(pageNum);
		}
		Integer index = (getPageNum()-1)*10;
		Object[] authorInfo = {index, getPageSize()};
		return template.query(readAuthor, authorInfo, this);
	}
	
	public Author readAuthor(Integer authorID) throws SQLException{
		final String readAuthor  = "SELECT * FROM tbl_author WHERE authorId=?";
		Object[]     authorsInfo = {authorID};
		List<Author> authors     = template.query(readAuthor, authorsInfo, this);
		if(authors!=null && !authors.isEmpty()){
			return authors.get(0);
		}
		return null;
	}
	
	public List<Author> readAuthor(Integer pageNum, String authorName) throws SQLException{
		final String readAuthor  = "SELECT * FROM tbl_author WHERE authorName LIKE ? LIMIT ?,?";
		if (pageNum != null) {
			setPageNum(pageNum);
		}
		Integer index = (getPageNum()-1)*10;
		Object[] authorsInfo = {("%" + authorName + "%"), index, getPageSize()};
		return template.query(readAuthor, authorsInfo, this);
	}
	
	public List<Author> readAuthorByBook(Integer bookID){
		final String readAuthor  = "SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId=?)";
		Object[]     bookInfo = {(bookID)};
		return template.query(readAuthor, bookInfo, this);
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author author = new Author();
			author.setAuthorID(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}

}

//public Integer countAuthors() throws SQLException{
//String readAuthor = "SELECT COUNT(*) as 'count_author' FROM tbl_author";
//return template.query(readAuthor, this);
//}

//@Override
//protected Integer extractSingleData(ResultSet rs) throws SQLException {
//	while (rs.next()){
//		return rs.getInt("count_author");
//	}
//	return null;
//}
