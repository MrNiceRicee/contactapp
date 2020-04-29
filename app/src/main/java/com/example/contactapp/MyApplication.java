package com.example.contactapp;

import android.app.Application;

public class MyApplication extends Application {

    private AddressBook addressBook = new AddressBook();


    public AddressBook getAddressBook() {

        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
