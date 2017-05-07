package com.gcit.libsystem.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import java.sql.*;
import java.util.*;

import com.gcit.libsystem.dao.*;
import com.gcit.libsystem.entity.*;

public class LibrarianService {
	
	@Autowired
	BranchDao 	 branchDao;
	@Autowired
	BookDao 	 bookDao;
	@Autowired
	AuthorDao 	 authorDao;
	@Autowired
	GenreDao 	 genreDao;
	@Autowired
	PublisherDao publisherDao;
	
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
	public void addNoOfCopies(Integer bookID, Branch branch){
		try {
			branchDao.deleteNoOfCopies(bookID, branch);
			branchDao.addNoOfCopies(bookID, branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updateNoOfCopies(Integer bookID, Branch branch){
		try {
			branchDao.updateNoOfCopies(bookID, branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public void deleteNoOfCopies(Integer bookID, Branch branch){
		try {
			branchDao.deleteNoOfCopies(bookID, branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}









