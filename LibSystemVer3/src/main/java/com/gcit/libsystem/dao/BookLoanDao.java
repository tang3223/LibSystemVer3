package com.gcit.libsystem.dao;

import org.springframework.jdbc.core.*;
import java.sql.*;
import java.util.*;

import com.gcit.libsystem.entity.BookLoan;

public class BookLoanDao extends BaseDao implements ResultSetExtractor<List<BookLoan>> {
	
	public void addBookLoan(Integer bookID, Integer branchID, Integer borrowerID) throws SQLException {
		String  addBookLoan  = "INSERT INTO tbl_book_loans "
				+ "(bookId,branchId,cardNo,dateOut,dueDate) VALUE (?,?,?,NOW(),DATE_ADD(NOW(),INTERVAL 7 DAY))";
		Object[] bookLoanInfo = {bookID,branchID,borrowerID};
		template.update(addBookLoan, bookLoanInfo);
	}
	
	public void updateBookLoanDate(BookLoan bookLoan) throws SQLException {
		String   updateBookLoanDate = "UPDATE tbl_book_loans SET dueDate=? WHERE bookId=? "
				+ "AND branchId=? AND cardNo=? AND dateOut=?";
		Object[] bookLoanInfo = {bookLoan.getDueDate(), bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchID(), 
				bookLoan.getBorrower().getBorrowerID(), bookLoan.getDateOut()};
		template.update(updateBookLoanDate, bookLoanInfo);
	}
	
	public boolean checkBookLoanExists(BookLoan bookLoan) throws SQLException{
		String  readBookLoan  = "SELECT * FROM tbl_book_loans WHERE bookId=? AND branchId=? "
				+ "AND cardNo=? AND dateOut=?";
		Object[] bookLoanInfo  = {bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchID(), 
				bookLoan.getBorrower().getBorrowerID(), bookLoan.getDateOut()};
		List<BookLoan> bookLoans = template.query(readBookLoan, bookLoanInfo, this);
		return !bookLoans.isEmpty();
	}
	
	public void returnBookLoanDate(BookLoan bookLoan) throws SQLException {
		String  returnBookLoanDate = "UPDATE tbl_book_loans SET dateIn=NOW() WHERE bookId=? "
				+ "AND branchId=? AND cardNo=? AND dateOut=?";
		Object[] bookLoanInfo = {bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchID(), 
				bookLoan.getBorrower().getBorrowerID(), bookLoan.getDateOut()};
		template.update(returnBookLoanDate, bookLoanInfo);
	}
	
	public void deleteBookLoan(BookLoan bookLoan) throws SQLException{
		String  deleteBookLoan = "DELETE FROM tbl_book_loans WHERE bookId=? AND branchId=? "
				+ "AND cardNo=? AND dateOut=?";
		Object[] bookLoanInfo  = {bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchID(), 
				bookLoan.getBorrower().getBorrowerID(), bookLoan.getDateOut()};
		template.update(deleteBookLoan, bookLoanInfo);
	}
	
	public List<BookLoan> readAllBookLoan() throws SQLException{
		String readBookLoan = "SELECT * FROM tbl_book_loans";
		return template.query(readBookLoan, this);
	}
	
	public BookLoan readBookLoan(BookLoan bookLoan) throws SQLException{
		String   readBookLoan = "SELECT * FROM tbl_book_loans WHERE bookId=? AND branchId=? "
				+ "AND cardNo=? AND dateOut=?";
		Object[] bookLoanInfo = {bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchID(), 
				bookLoan.getBorrower().getBorrowerID(), bookLoan.getDateOut()};
		List<BookLoan> bookLoans = template.query(readBookLoan, bookLoanInfo, this);
		if(bookLoans!=null && !bookLoans.isEmpty()){
			return bookLoans.get(0);
		}
		return null;
	}
	
	public List<BookLoan> readBookLoan(Integer borrowerID) throws SQLException{
		String   readBookLoan = "SELECT * FROM tbl_book_loans WHERE cardNo=? AND dataIn IS NULL";
		Object[] bookLoanInfo = {borrowerID};
		return template.query(readBookLoan, bookLoanInfo, this);

	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		List<BookLoan> bookLoans = new ArrayList<>();
		while(rs.next()){
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBookID(rs.getInt("bookId"));
			bookLoan.setBorrowerID(rs.getInt("cardNo"));
			bookLoan.setBranchID(rs.getInt("branchId"));
			bookLoan.setDateOut(rs.getTimestamp("dateOut"));
			bookLoan.setDueDate(rs.getTimestamp("dueDate"));
			bookLoan.setDateIn(rs.getTimestamp("dateIn"));;
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

}

//
//@Override
//public List<BookLoan> extractDataReadOnly(ResultSet rs) throws SQLException {
//	return null;
//}
//
//@Override
//protected <T> T extractSingleData(ResultSet rs) throws SQLException {
//	return null;
//}
