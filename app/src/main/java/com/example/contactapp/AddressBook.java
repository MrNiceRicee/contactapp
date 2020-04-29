package com.example.contactapp;


import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    public List<BaseContact> generateContacts(int amount){
        Randomizer makerandom = new Randomizer();
        List<BaseContact> generated = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            generated.add(makerandom.makeBaseContact());
        }
        return  generated;
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