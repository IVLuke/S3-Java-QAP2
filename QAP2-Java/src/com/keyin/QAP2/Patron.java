package com.keyin.QAP2;

public class Patron {
    private int patron_id;
    private String name;
    private String address;
    private String phone_number;

    public Patron(int patron_id, String name, String address, String phone_number) {
        this.patron_id = patron_id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getPatron_id() {
        return patron_id;
    }

    public void setPatron_id(int patron_id) {
        this.patron_id = patron_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "patron_id=" + patron_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
