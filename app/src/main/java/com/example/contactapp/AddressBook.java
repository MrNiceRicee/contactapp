package com.example.contactapp;


import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class AddressBook extends Application {
    List<BaseContact> contactBook = new ArrayList();

    public AddressBook(List<BaseContact> ContactList) {
        contactBook = ContactList;
    }


    public List<BaseContact> getAddressBookcontacts(){ return contactBook; }


    public void viewAllContact() {
        for(int i = 0; i < this.contactBook.size(); ++i) {
            System.out.println(i + 1 + ". " + ((BaseContact)this.contactBook.get(i)).toString());
        }
    }

    public void viewPersonalContact() {
        int count = 1;

        for(int i = 0; i < this.contactBook.size(); ++i) {
            if (this.contactBook.get(i) instanceof PersonContact) {
                System.out.println(count + ". " + ((BaseContact)this.contactBook.get(i)).toString());
                ++count;
            }
        }

    }

    public void viewBusinessContact() {
        int count = 1;

        for(int i = 0; i < this.contactBook.size(); ++i) {
            if (this.contactBook.get(i) instanceof PersonContact) {
                System.out.println(count + ". " + ((BaseContact)this.contactBook.get(i)).toString());
                ++count;
            }
        }

    }
}