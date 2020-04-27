package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_search, btn_addcontact;

    ListView lv_contacts;

    basiccontactadapter basicContactAdapter;

    List<BaseContact> addressBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_search = findViewById(R.id.btn_Search);
        btn_addcontact = findViewById(R.id.btn_addcontact);
        lv_contacts = findViewById(R.id.lv_contacts);

        addressBook = ((AddressBook) this.getApplication()).getAddressBookcontacts();

        basicContactAdapter = new basiccontactadapter(MainActivity.this, addressBook);

        lv_contacts.setAdapter(basicContactAdapter);


    }
}
