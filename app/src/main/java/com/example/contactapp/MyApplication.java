package com.example.contactapp;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    DataManager dataManager = new DataManager();

    private AddressBook addressBook = new AddressBook();

    public AddressBook generate(){
        AddressBook toreturn = dataManager.load();
        return toreturn;
    }


    public AddressBook getAddressBook() {
        //Log.d()
        //addressBook.getContactBook().addAll(dataManager.load().getContactBook());
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
