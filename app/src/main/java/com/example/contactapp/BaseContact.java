package com.example.contactapp;


public class BaseContact {
    protected String name;
    protected String address;
    protected String phone;
    protected String street;
    protected String city;
    protected String country;
    protected String state;
    protected String postal;
    protected String location;

    public String getLocation() {
        return this.address + " " + this.street + ", " + this.city + ", " + this.state + ", " + this.postal + ", " + this.country;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    protected void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    protected void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return this.street;
    }

    protected void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    protected void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    protected void setCountry(String country) {
        this.country = country;
    }

    public String getPostal() {
        return this.postal;
    }

    protected void setPostal(String postal) {
        this.postal = postal;
    }

    public void makeCall(String phone) {
    }

    public void navigate(String address) {
    }

    public void sendEmail(String email) {
    }

    public void sendText(String text) {
    }

    public String toString() {
        return "Hi, my name is " + this.name;
    }

    public BaseContact(String name, String phone, String address, String street, String city, String state, String country, String postal) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postal = postal;
    }
}