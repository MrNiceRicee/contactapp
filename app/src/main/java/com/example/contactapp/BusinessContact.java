package com.example.contactapp;


public class BusinessContact extends BaseContact {
    String businessHours;
    String url;

    public String getBusinessHours() {
        return this.businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BusinessContact(String name, String phone, String address, String street, String city, String state, String country, String postal, String businessHours, String url) {
        super(name, phone, address, street, city, state, country, postal);
        this.businessHours = businessHours;
        this.url = url;
    }

    public String toString() {
        return "Hi, our business is " + this.name + ", our contact number is " + this.phone + "\n\t We operate on " + this.getLocation() + ".\n\t We're open " + this.businessHours + ". Website is: " + this.url + "\n";
    }
}