package com.example.contactapp;


import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class AddressBook extends Application {
    List<BaseContact> contactBook;

    public AddressBook(List<BaseContact> contactBook) {
        this.contactBook = contactBook;
    }

    public AddressBook(int amount) {
        this.contactBook = new ArrayList<>();
        Randomizer makerandom = new Randomizer();
        for (int i = 0; i < amount; i++) {
            contactBook.add(makerandom.makeBaseContact());
        }
    }

    public AddressBook(){
        this.contactBook = new ArrayList<>();
    }

    public List<BaseContact> getContactBook() {
        return contactBook;
    }

    public void setContactBook(List<BaseContact> contactBook) {
        this.contactBook = contactBook;
    }
}