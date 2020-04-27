package com.example.contactapp;


public class PersonContact extends BaseContact{
    //Properties
    String DoB;
    String description;
    //Methods
    public String getDoB() {
        return DoB;
    }
    public void setDoB(String doB) {
        DoB = doB;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //Constructor

    public PersonContact(String name, String phone, String address, String street, String city, String state,
                         String country, String postal, String doB, String description) {
        super(name, phone, address, street, city, state, country, postal);
        DoB = doB;
        this.description = description;
    }

    //Personal Contact needs
    //name, phone, address, street, city, state, country, postal, dob, description

    public PersonContact(String name, String phone, String address, String street, String city, String state,
                         String country, String postal) {
        super(name, phone, address, street, city, state, country, postal);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return("Hi, my name is " + this.name + ", my number is " + this.phone +
                "\n\t I live on "+getLocation() + ".\n\t Born on " +this.DoB+ " and I'm a/an " +this.description+"\n");
    }


}
