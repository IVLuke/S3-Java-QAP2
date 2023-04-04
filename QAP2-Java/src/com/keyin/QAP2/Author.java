package com.keyin.QAP2;

import java.util.Date;

public class Author {
    private int author_id;
    private String name;
    private Date dateOfBirth;

    public Author(int author_id, String name, Date dateOfBirth) {
        this.author_id = author_id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
