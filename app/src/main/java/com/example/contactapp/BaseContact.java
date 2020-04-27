package com.example.contactapp;


import android.content.Intent;

public class BaseContact {
    private BaseContact_Name name;
    private String phone;
    private BaseContact_Address address;
    private BaseContact_DateOfBirth dateOfBirth;
    private String email;
    private String url;
    private String description;

    //necessary to have a name and phonenumber


    public BaseContact(BaseContact_Name name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public BaseContact_Name getName() {
        return name;
    }

    public void setName(BaseContact_Name name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        try {
            Integer.parseInt(phone);
            String phonenum = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            this.phone = phonenum;
        }catch(Exception e){
            this.phone = phone;
        };
    }

    public BaseContact_Address getAddress() {
        return address;
    }

    public void setAddress(BaseContact_Address address) {
        this.address = address;
    }

    public BaseContact_DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(BaseContact_DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}