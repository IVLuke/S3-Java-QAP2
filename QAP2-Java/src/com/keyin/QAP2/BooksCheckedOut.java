package com.keyin.QAP2;

import java.util.Date;

public class BooksCheckedOut {
    private int checkout_id;
    private int patron_id;
    private int book_id;
    private Date checkout_date;
    private Date due_date;
    private Date return_date;
    public BooksCheckedOut(int checkout_id, int patron_id, int book_id, Date checkout_date, Date due_date, Date return_date) {
        this.checkout_id = checkout_id;
        this.patron_id = patron_id;
        this.book_id = book_id;
        this.checkout_date = checkout_date;
        this.due_date = due_date;
        this.return_date = return_date;
    }

    public int getCheckout_id() {
        return checkout_id;
    }

    public void setCheckout_id(int checkout_id) {
        this.checkout_id = checkout_id;
    }

    public int getPatron_id() {
        return patron_id;
    }

    public void setPatron_id(int patron_id) {
        this.patron_id = patron_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Date getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(Date checkout_date) {
        this.checkout_date = checkout_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "BooksCheckedOut{" +
                "checkout_id=" + checkout_id +
                ", patron_id=" + patron_id +
                ", book_id=" + book_id +
                ", checkout_date=" + checkout_date +
                ", due_date=" + due_date +
                ", return_date=" + return_date +
                '}';
    }
}
