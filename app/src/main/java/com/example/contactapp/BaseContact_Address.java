package com.example.contactapp;

public class BaseContact_Address {

    private String street;
    private String street2;
    private String city;
    private String state;
    private String postal;
    private String country;     //not necessary to fill in

    public BaseContact_Address(String street, String street2, String city, String state, String postal) {
        //if adding an address, need everything
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.postal = postal;
    }

    //12345 N Street st, city, state, postal,
    public String getFullAddress(){
        return (this.street+ " "+this.street2+ ", "+this.city+ ", "+this.state+", "+this.postal);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
