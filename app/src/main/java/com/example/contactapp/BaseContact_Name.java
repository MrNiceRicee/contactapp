package com.example.contactapp;

public class BaseContact_Name {

    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;

    public BaseContact_Name(String firstName,String lastName) {
        //only first name and last name is deemed necessary to have
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName(){
        return (this.firstName + " "+ this.middleName+ " " + this.lastName +" "+ this.nickName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
