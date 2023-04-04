// QAP2 Advanced Java
// By: Luke Jones
// Date Due: March 30th,2023
package com.keyin;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.keyin.QAP2.*;



public class Main {

  public static void main(String[] args)  throws SQLException {

    // Connecting to server
    DbFunctions db =new DbFunctions();
    Connection conn = db.PostgresClient("QAP2","postgres","password");

    // Creating tables
//    db.createPatronTable(conn,"Patron");
//    db.createAuthorTable(conn,"Author");
//    db.createBookTable(conn,"Book");
//    db.createBookCheckOutTable(conn,"BookCheckout");

    //Array lists
    List<Author> listOfAuthors = new ArrayList<>();
    List<Patron> listOfPatrons = new ArrayList<>();
    List<Books> listOfBooks = new ArrayList<>();
    List<BooksCheckedOut> listOfBooksCheckedOut = new ArrayList<>();

    //SQL Statements
    String GET_ALL_AUTHORS_QUERY = "SELECT * FROM Author";
    String GET_ALL_PATRONS_QUERY = "SELECT * FROM Patron";
    String GET_ALL_BOOKS_QUERY = "SELECT * FROM Book";
    String GET_ALL_BOOKS_CHECKED_OUT_QUERY = "SELECT c.checkout_id, p.patron_id, b.book_id, c.checkout_date, c.due_date, c.return_date, p.name " +
            "FROM bookcheckout c " +
            "JOIN patron p ON c.patron_id = p.patron_id " +
            "JOIN book b ON c.book_id = b.book_id " +
            "ORDER BY p.name";

    //Patron Inserts
    db.insertIntoPatron(conn, "Jack Sparrow", "123 Pirate Cove", "709-277-5216");
    db.insertIntoPatron(conn, "David Jones", "312 Pirate Cove", "709-277-1111");

    //Author Inserts
    db.insertIntoAuthor(conn, "J.K Rowling", Date.valueOf("1996-07-31"));
    db.insertIntoAuthor(conn, "Rick Riordan", Date.valueOf("2000-07-31"));

    //Book Inserts
    db.insertIntoBook(conn,  "Philosopher's Stone",1,"Atlantic","12345");
    db.insertIntoBook(conn,  "The Lightning Thief",2,"Atlantic","12345");
    //BookCheckOut Insert/Populated
    db.insertIntoBookCheckoutFromTables(conn,  1, 1,  Date.valueOf("2023-03-25"), Date.valueOf("2023-04-03"), Date.valueOf("2023-04-1"));
    db.insertIntoBookCheckoutFromTables(conn,  2, 2,  Date.valueOf("2023-03-22"), Date.valueOf("2023-03-31"), Date.valueOf("2023-04-1"));



    String CREATE_BOOK_CHECKOUT_TABLE = "CREATE TABLE BookCheckout(\n" +
            "checkout_id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n" +
            "patron_id INT NOT NULL,\n" +
            "book_id INT NOT NULL,\n" +
            "checkout_date DATE NOT NULL,\n" +
            "due_date DATE NOT NULL,\n" +
            "return_date DATE,\n" +
            "FOREIGN KEY (patron_id) REFERENCES Patron(patron_id),\n" +
            "FOREIGN KEY (book_id) REFERENCES Book(book_id)\n" +
            ");";



//  Result sets
    ResultSet resultSetForAllBooks = db.queryDatabase(conn,GET_ALL_BOOKS_QUERY);
    ResultSet resultSetForAllAuthors = db.queryDatabase(conn,GET_ALL_AUTHORS_QUERY);
    ResultSet resultSetForAllPatrons = db.queryDatabase(conn,GET_ALL_PATRONS_QUERY);
    ResultSet resultSetForBooksCheckedOut = db.queryDatabase(conn,GET_ALL_BOOKS_CHECKED_OUT_QUERY);



//Query
    while(resultSetForAllBooks.next()){
      Books books = new Books(resultSetForAllBooks.getInt("book_id"),resultSetForAllBooks.getString("title"),resultSetForAllBooks.getInt("author_id"),resultSetForAllBooks.getString("publisher"),resultSetForAllBooks.getString("isbn"));
      listOfBooks.add(books);
    }
    while(resultSetForAllAuthors.next()){
      Author author = new Author(resultSetForAllAuthors.getInt("author_id"),resultSetForAllAuthors.getString("name"),resultSetForAllAuthors.getDate("dob"));
      listOfAuthors.add(author);
    }
    while(resultSetForAllPatrons.next()){
      Patron patron = new Patron(resultSetForAllPatrons.getInt("patron_id"),resultSetForAllPatrons.getString("name"),resultSetForAllPatrons.getString("address"),resultSetForAllPatrons.getString("phone_number"));
      listOfPatrons.add(patron);
    }
    while (resultSetForBooksCheckedOut.next()){
      BooksCheckedOut bookCheckedOut = new BooksCheckedOut(resultSetForBooksCheckedOut.getInt("checkout_id"), resultSetForBooksCheckedOut.getInt("patron_id"),resultSetForBooksCheckedOut.getInt("book_id"), resultSetForBooksCheckedOut.getDate("checkout_date"), resultSetForBooksCheckedOut.getDate("due_date"), resultSetForBooksCheckedOut.getDate("return_date")
      );
      listOfBooksCheckedOut.add(bookCheckedOut); }

    //Library Management System
    LibraryManagement libraryManagement = new LibraryManagement();
    libraryManagement.setBooks(listOfBooks);
    libraryManagement.setAuthor(listOfAuthors);
    libraryManagement.setPatron(listOfPatrons);
    libraryManagement.setBooksCheckedOut(listOfBooksCheckedOut);


    List<BooksCheckedOut> results = libraryManagement.getBooksCheckedOut();
    int counter = 0;
    System.out.println("----------List Of Checked Out Books----------");
    for (BooksCheckedOut item : results) {
      counter+=1;
      System.out.println(counter+ "." + item + "\n");

    }


  }
}
