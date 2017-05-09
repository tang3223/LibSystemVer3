package com.gcit.libsystem.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import java.sql.*;
import java.util.*;

import com.gcit.libsystem.dao.*;
import com.gcit.libsystem.entity.*;

public class AdminService {

	@Autowired
	BookDao 	 bookDao;
	@Autowired
	AuthorDao 	 authorDao;
	@Autowired
	PublisherDao publisherDao;
	@Autowired
	GenreDao 	 genreDao;
	@Autowired
	BranchDao 	 branchDao;
	@Autowired
	BorrowerDao  borrowerDao;
	@Autowired
	BookLoanDao  bookLoanDao;
	
	@Transactional
	public void addAuthor(Author author) {
		try {
			authorDao.addAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void addBook(Book book){
			try {
				book.setBookId(bookDao.addBookReplyID(book));
				bookDao.addBook(book);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	public void addGenre(Genre genre){
		try {
			genreDao.addGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void addPublisher(Publisher publisher){
		try {
			publisherDao.addPublisher(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void addBranch(Branch branch){
		try {
			branchDao.addBranch(branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void addBorrower(Borrower borrower){
		try {
			borrowerDao.addBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updateAuthor(Author author){
		try {
			authorDao.updateAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updateBook(Book book){
			try {
				bookDao.updateBook(book);
				bookDao.deleteBookAuthors(book);
				bookDao.addBookAuthors(book);
				bookDao.deleteBookGenres(book);
				bookDao.addBookGenres(book);
				bookDao.updateBookPublisher(book);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	public void updateGenre(Genre genre){
		try {
			genreDao.updateGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updatePublisher(Publisher publisher){
		try {
			publisherDao.updatePublisherName(publisher);
			publisherDao.updatePublisherAddress(publisher);
			publisherDao.updatePublisherPhone(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Transactional
	public void updateBranch(Branch branch){
		try {
			branchDao.updateBranchName(branch);
			branchDao.updateBranchAddress(branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
 	
	@Transactional
	public void updateBorrower(Borrower borrower){
		try {
			borrowerDao.updateBorrowerName(borrower);
			borrowerDao.updateBorrowerAddress(borrower);
			borrowerDao.updateBorrowerPhone(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void deleteAuthor(Author author){
		try {
			authorDao.deleteAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void deleteBook(Book book){
		try {
			bookDao.deleteBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void deleteGenre(Genre genre){
		try {
			genreDao.deleteGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void deletePublisher(Publisher publisher){
		try {
			publisherDao.deletePublisher(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void deleteBranch(Branch branch){
		try {
			branchDao.deleteBranch(branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void deleteBorrower(Borrower borrower){
		try {
			borrowerDao.deleteBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> readAllBook(){
		try {
			List<Book> books = bookDao.readAllBook();
			for (Book b : books){
				b.setAuthors(authorDao.readAuthorByBook(b.getBookId()));
				b.setGenres(genreDao.readGenreByBook(b.getBookId()));
				b.setPublisher(publisherDao.readPublisherByBook(b.getBookId()));
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public void updateDueDate(BookLoan bookLoan){
		try {
			bookLoanDao.updateBookLoanDate(bookLoan);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Author> readAllAuthors(Integer pageNum){
		try {
			List<Author> authors = authorDao.readAllAuthors(pageNum);
			for (Author author : authors){
				author.setBooks(bookDao.readBookByAuthor(author.getAuthorID()));
				}
			return authors;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Author readAuthor(Integer authorID){
		try {
			Author author = authorDao.readAuthor(authorID);
			author.setBooks(bookDao.readBookByAuthor(authorID));
			return author;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Author> readAuthor(Integer pageNum, String authorName){
		try {
			List<Author> authors = authorDao.readAuthor(pageNum, authorName);
			for (Author author : authors){
				author.setBooks(bookDao.readBookByAuthor(author.getAuthorID()));
				}
			return authors;
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
	
	public List<Genre> readAllGenre(){
		try {
			List<Genre> genres = genreDao.readAllGenre();
			for (Genre genre : genres){
				genre.setBooks(bookDao.readBookByGenre(genre.getGenreId()));
				}
			return genres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Genre readGenre(Integer genreID){
		try {
			Genre genre = genreDao.readGenre(genreID);
			genre.setBooks(bookDao.readBookByGenre(genreID));
			return genre;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Publisher> readAllPublisher(){
		try {
			List<Publisher> publishers = publisherDao.readAllPublisher();
			for (Publisher publisher : publishers){
				publisher.setBooks(bookDao.readBookByPublisher(publisher.getPublisherId()));
				}
			return publishers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Publisher readPublisher(Integer publisherID){
		try {
			Publisher publisher = publisherDao.readPublisher(publisherID);
			publisher.setBooks(bookDao.readBookByPublisher(publisherID));
			return publisher;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
	
	public List<Borrower> readAllBorrower() {
		try {
			List<Borrower> borrowers = borrowerDao.readAllBorrower();
			return borrowers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
	
	public List<BookLoan> readAllBookLoans(){
		try {
			List<BookLoan> bookLoans = bookLoanDao.readAllBookLoan();
			for (BookLoan bookLoan : bookLoans){
				bookLoan.setBook(bookDao.readBook(bookLoan.getBookID()));
				bookLoan.setBorrower(borrowerDao.readBorrower(bookLoan.getBorrowerID()));
				bookLoan.setBranch(branchDao.readBranch(bookLoan.getBranchID()));
				}
			return bookLoans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public BookLoan readBookLoan(BookLoan bookLoan) {
		try {
			BookLoan bl = bookLoanDao.readBookLoan(bookLoan);
			bl.setBook(bookDao.readBook(bookLoan.getBookID()));
			bl.setBorrower(borrowerDao.readBorrower(bookLoan.getBorrowerID()));
			bl.setBranch(branchDao.readBranch(bookLoan.getBranchID()));
			return bl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String parseListItems(List<?> items) {
		if (items == null || items.isEmpty()) {
			return "";
		}
		if (items.get(0).getClass() == Author.class) {
			String authorName = "";
			Author author = null;
			for (int i = 0; i < items.size(); i++){
				author = (Author) items.get(i);
				authorName += author.getAuthorName();
				if (i >= 0 && i < items.size()-1 && items.size()>1){
					authorName += " | ";
				}
			}
			return authorName;
		}
		if (items.get(0).getClass() == Genre.class){
			String genreName = "";
			for (int i = 0; i < items.size(); i++){
				Genre genre = (Genre) items.get(i);
				genreName += genre.getGenreName();
				if (i >= 0 && i < items.size()-1 && items.size()>1){
					genreName += " | ";
				}
			}
			return genreName;
		}
	return null;
	}
	
	public Integer countAuthors(){
		try {
			return authorDao.countAuthors();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}























