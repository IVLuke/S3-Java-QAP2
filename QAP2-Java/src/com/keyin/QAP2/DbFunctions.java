// QAP2 Advanced Java
// By: Luke Jones
// Date Due: March 30th,2023
package com.keyin.QAP2;

import java.sql.*;

public class DbFunctions {

// Connecting
   public Connection PostgresClient(String dbname,String user,String password) {
       Connection connection = null;
       Statement statement = null;
       ResultSet rs = null;

       try {
           Class.forName("org.postgresql.Driver");
           connection = DriverManager.getConnection("jdbc:postgresql://database-1.c5gm64sfhwq2.us-east-1.rds.amazonaws.com:5432/"+ dbname, user, password);
           if (connection != null) {
               System.out.println("Connected.");
           } else {
               System.out.println("Failed to Connect.");
           }
       } catch (ClassNotFoundException | SQLException e) {
           throw new RuntimeException(e);
       }
       return connection;
   }
   // Creating tables
    public void createPatronTable(Connection connection, String name){
        Statement statement;
        try{
            String query = "CREATE TABLE " + name + "(patron_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n"+
                    "name VARCHAR(50) NOT NULL,\n" +
                    "address VARCHAR(50) NOT NULL,\n"+
                    "phone_number VARCHAR(50) NOT NULL)\n";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Patron Table Has Been Created!");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void createAuthorTable(Connection connection, String name){
        Statement statement;
        try{
            String query = "CREATE TABLE " + name + "(author_id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n"+
                    "name VARCHAR(50) NOT NULL,\n" +
                    "dob DATE NOT NULL)";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Author Table Has Been Created!");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
   public void createBookTable(Connection connection, String name){
       Statement statement;
       try{
           String query = "CREATE TABLE " + name + "(book_id BIGINT  NOT NULL  PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n" +
                   "title VARCHAR(200) NOT NULL,\n"+
                   "author_id INT NOT NULL,\n" +
                   "publisher VARCHAR(100),\n"+
                   "isbn VARCHAR(100),\n"+
                   "FOREIGN KEY (author_id) REFERENCES Author(author_id))";
           statement = connection.createStatement();
           statement.executeUpdate(query);
           System.out.println("Book Table Has Been Created!");
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
   }
    public void createBookCheckOutTable(Connection connection, String name){
        Statement statement;
        try{
            String query = "CREATE TABLE " + name +
                    "(checkout_id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n" +
                    "patron_id INT NOT NULL,\n" +
                    "book_id INT NOT NULL,\n" +
                    "checkout_date DATE NOT NULL,\n" +
                    "due_date DATE NOT NULL,\n" +
                    "return_date DATE,\n" +
                    "FOREIGN KEY (patron_id) REFERENCES Patron(patron_id),\n" +
                    "FOREIGN KEY (book_id) REFERENCES Book(book_id)\n" +
                    ")";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Book Checkout Table Has Been Created!");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public ResultSet queryDatabase(Connection conn, String query) {
        ResultSet rs = null;
        try {
            // Execute a query (To retrieve data from the database)
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public void insertIntoPatron(Connection conn, String name, String address, String phone_number) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO patron ( name, address, phone_number) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone_number);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patron has been successfully added");
            } else {
                System.out.println("Error! Patron could not be added");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void insertIntoAuthor(Connection conn, String name, Date dob) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO author (name, dob) VALUES (?, ?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setDate(2, dob);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Author has been successfully added");
            } else {
                System.out.println("Error! Author could not be added");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void insertIntoBook(Connection conn,String title, int author_id, String publisher, String isbn) {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO Book (title, author_id, publisher, isbn) VALUES (?, ?, ?,?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setInt(2, author_id);
            statement.setString(3, publisher);
            statement.setString(4, isbn);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book has been successfully added");
            } else {
                System.out.println("Error! Book could not be added");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }}
        public void insertIntoBookCheckoutFromTables(Connection conn, int patron_id,
        int book_id, Date checkout_date, Date due_date, Date return_date){
            PreparedStatement statement = null;
            ResultSet rs = null;
            try {
                String query = "INSERT INTO bookcheckout (patron_id, book_id, checkout_date, " +
                        "due_date, return_date) " +
                        "SELECT b.book_id, p.patron_id, ?, ?, ? " +
                        "FROM Book b " +

                        "JOIN Patron p ON p.patron_id = ? " +
                        "WHERE b.book_id = ?";

                statement = conn.prepareStatement(query);

                statement.setDate(1, checkout_date);
                statement.setDate(2, due_date);
                statement.setDate(3, return_date);
                statement.setInt(4, patron_id);
                statement.setInt(5, book_id);



                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Book has been successfully added");
                } else {
                    System.out.println("Error! Book could not be added");
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

    }
