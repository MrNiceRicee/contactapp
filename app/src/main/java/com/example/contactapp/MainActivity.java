package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AndroidException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_search;
    ImageButton btn_addcontact;
    ListView lv_contacts;

    AddressBook addressBook;

    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_search = findViewById(R.id.btn_Search);
        btn_addcontact = findViewById(R.id.btn_addcontact);
        lv_contacts = findViewById(R.id.lv_contacts);

        addressBook = ((MyApplication)this.getApplication()).getAddressBook();

        adapter = new PersonAdapter(MainActivity.this,addressBook);
        lv_contacts.setAdapter(adapter);


        //listen for incoming messages
        Bundle incomingmessages = getIntent().getExtras();
        if (incomingmessages!= null){
            //name
            String firstname = incomingmessages.getString("firstname");
            String middlename = incomingmessages.getString("middlename");
            String lastname = incomingmessages.getString("lastname");
            String nickname = incomingmessages.getString("nickname");

            //phone number
            String phonenumber = incomingmessages.getString("phonenumber");

            //address
            String streetnumber = incomingmessages.getString("streetnumber");
            String street = incomingmessages.getString("street");
            String city = incomingmessages.getString("city");
            String state = incomingmessages.getString("state");
            String postal = incomingmessages.getString("postal");
            String country = incomingmessages.getString("country");

            //date of birth
            String day = incomingmessages.getString("day");
            String month = incomingmessages.getString("month");
            String year = incomingmessages.getString("year");


            //rest
            String email = incomingmessages.getString("email");
            String url = incomingmessages.getString("url");
            String description = incomingmessages.getString("description");


            int positionEdited = incomingmessages.getInt("edit");
            if(positionEdited > -1){
                addressBook.getContactBook().remove(positionEdited);
            }

            //name
            BaseContact_Name name = new BaseContact_Name(firstname,lastname);
            name.setMiddleName(middlename);
            name.setNickName(nickname);

            //address
            BaseContact_Address address= new BaseContact_Address(streetnumber,street,city,state,postal);
            address.setCountry(country);

            //date of birth
            BaseContact_DateOfBirth dateOfBirth = new BaseContact_DateOfBirth(day,month,year);

            BaseContact contact = new BaseContact(name,phonenumber);
            contact.setName(name);
            contact.setAddress(address);
            contact.setDateOfBirth(dateOfBirth);
            contact.setEmail(email);
            contact.setUrl(url);
            contact.setDescription(description);

            addressBook.getContactBook().add(contact);

            adapter.notifyDataSetChanged();

        }
        //capture incoming data

        //create new person object

        //add person to the list and update adapter

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),newBaseContactForm.class);
                startActivity(intent);
            }
        });

        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),FullContact.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });



    }


}
