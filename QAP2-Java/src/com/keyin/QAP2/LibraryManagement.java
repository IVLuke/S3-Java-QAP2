package com.keyin.QAP2;

import java.util.List;

public class LibraryManagement {
    private List<Books> books;
    private List<Author> author;
    private List<Patron> patron;
    private List<BooksCheckedOut> BooksCheckedOut;
    public List<Books> getBooks(){
        return books;
    };
    public void setBooks(List<Books> books){
        this.books = books;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<Patron> getPatron() {
        return patron;
    }

    public List<BooksCheckedOut> getBooksCheckedOut() {
        return BooksCheckedOut;
    }

    public void setBooksCheckedOut(List<BooksCheckedOut> BooksCheckedOut) {
        this.BooksCheckedOut = BooksCheckedOut;
    }

    public void setPatron(List<Patron> patron) {
        this.patron = patron;
    }
    public Books getBooksById(int id){
        for(Books book : books){
            if(book.getBook_id() == id){
                return book;
            }
        }
        return null;
    }
}
