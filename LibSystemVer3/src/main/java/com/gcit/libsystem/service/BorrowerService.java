package com.gcit.libsystem.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.libsystem.dao.*;
import com.gcit.libsystem.entity.*;

public class BorrowerService {
	
	@Autowired
	BookDao 	 bookDao;
	@Autowired
	BranchDao 	 branchDao;
	@Autowired
	AuthorDao 	 authorDao;
	@Autowired
	GenreDao 	 genreDao;
	@Autowired
	PublisherDao publisherDao;
	@Autowired
	BorrowerDao  borrowerDao;
	@Autowired
	BookLoanDao  bookLoanDao;
	
	public List<Branch> readAllBranchs(){
		try {
			List<Branch> branchs = branchDao.readAllBranch();
			for (Branch branch : branchs){
				branch.setBooks(bookDao.readBookByBranch(branch.getBranchID()));
				}
			return branchs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Branch readBranchWithLimit(Integer branchID){
		try {
			List<Book> books = bookDao.readBookByBranchWithLimit(branchID);
			Branch branch = branchDao.readBranch(branchID);
			branch.setBooks(books);
			return branch;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Branch readBranch(Integer branchID){	
		try {
			Branch branch = branchDao.readBranch(branchID);
			branch.setBooks(bookDao.readBookByBranch(branchID));
			return branch;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Book readBook(Integer bookID){
		try {
			Book book = bookDao.readBook(bookID);
			book.setAuthors(authorDao.readAuthorByBook(bookID));
			book.setGenres(genreDao.readGenreByBook(bookID));
			book.setPublisher(publisherDao.readPublisherByBook(bookID));
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean checkCardID(Integer cardID){
		try {
			return borrowerDao.checkBorrowerExist(cardID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public void checkOutBook(Integer bookID, Integer branchID, Integer borrowerID){
		try {
			bookLoanDao.addBookLoan(bookID, branchID, borrowerID);
			branchDao.decNoOfCopies(branchDao.readBranch(branchID),bookID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void returnBook(BookLoan bookLoan){
		try {
			bookLoanDao.returnBookLoanDate(bookLoan);
			branchDao.incNoOfCopies(branchDao.readBranch(bookLoan.getBranchID()),bookLoan.getBookID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Borrower readBorrower(Integer borrowerID) {
		try {
			Borrower borrower = borrowerDao.readBorrower(borrowerID);
			return borrower;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<BookLoan> readBookLoan(Integer borrowerID){
		try {
			List<BookLoan> bookLoan = bookLoanDao.readBookLoan(borrowerID);
			return bookLoan;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}



//public String parseListItems(List<?> items) {
//if (items == null || items.isEmpty()) {
//	return "";
//}
//if (items.get(0).getClass() == Author.class) {
//	String authorName = "";
//	Author author = null;
//	for (int i = 0; i < items.size(); i++){
//		author = (Author) items.get(i);
//		authorName += author.getAuthorName();
//		if (i >= 0 && i < items.size()-1 && items.size()>1){
//			authorName += " | ";
//		}
//	}
//	return authorName;
//}
//if (items.get(0).getClass() == Genre.class){
//	String genreName = "";
//	for (int i = 0; i < items.size(); i++){
//		Genre genre = (Genre) items.get(i);
//		genreName += genre.getGenreName();
//		if (i >= 0 && i < items.size()-1 && items.size()>1){
//			genreName += " | ";
//		}
//	}
//	return genreName;
//}
//return null;
//}



//public boolean checkDupBook(Integer bookID, Integer branchID, Integer borrowerID) throws SQLException{
//	Connection conn = null;
//
//	try {
//		conn = ConnectionUtil.getConnection();
//		BookLoanDao bldao = new BookLoanDao(conn);
//		BranchDao brdao = new BranchDao(conn);		
//		return bldao.checkBookLoan(bookID, branchID, borrowerID);
//	} catch (ClassNotFoundException | SQLException e) {
//		e.printStackTrace();
//		return false;
//	} finally{
//		if(conn!=null){
//			conn.close();
//		}
//	}
//}

//public BookLoan readBookLoan(Integer borrowerID, Integer bookID, Integer branchID, Integer num) throws SQLException{
//Connection conn = null;
//
//try {
//	conn = ConnectionUtil.getConnection();
//	BookLoanDao bldao = new BookLoanDao(conn);
//	BookLoan bookLoan = bldao.readBookLoan(bookID, branchID, borrowerID, num);
//	return bookLoan;
//} catch (ClassNotFoundException | SQLException e) {
//	e.printStackTrace();
//} finally{
//	if(conn!=null){
//		conn.close();
//	}
//}
//return null;
//}

