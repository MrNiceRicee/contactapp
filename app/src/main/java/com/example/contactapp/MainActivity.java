package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_search;
    ImageButton btn_addcontact, btn_sort;
    ListView lv_contacts;

    AddressBook addressBook;

    PersonAdapter adapter;

    int sortType = 0;

    private static final String FILE_NAME = "saveData.txt";

    Button btn_test,btn_test2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_search = findViewById(R.id.et_search);
        btn_addcontact = findViewById(R.id.btn_addcontact);
        btn_sort = findViewById(R.id.btn_sort);
        lv_contacts = findViewById(R.id.lv_contacts);

        btn_test = findViewById(R.id.btn_testee);
        btn_test2 = findViewById(R.id.btn_loadtest);

        addressBook = ((MyApplication)this.getApplication()).getAddressBook();      //checks if theres anything in the current app saved
        //check shared preferences

        Collections.sort(addressBook.getContactBook(), new Comparator<BaseContact>() {
            @Override
            public int compare(BaseContact o1, BaseContact o2) {
                return o1.getName().getFirstName().compareToIgnoreCase(o2.getName().getFirstName());
            }
        });


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
            //BaseContact_Address address= new BaseContact_Address(streetnumber,street,city,state,postal);
            BaseContact_Address address = new BaseContact_Address();
            address.setStreet(streetnumber);
            address.setStreet2(street);
            address.setCity(city);
            address.setState(state);
            address.setPostal(postal);
            address.setCountry(country);

            //date of birth
            //BaseContact_DateOfBirth dateOfBirth = new BaseContact_DateOfBirth(day,month,year);
            BaseContact_DateOfBirth dateOfBirth = new BaseContact_DateOfBirth();
            dateOfBirth.setDay(day);
            dateOfBirth.setMonth(month);
            dateOfBirth.setYear(year);

            BaseContact contact = new BaseContact(name,phonenumber);
            contact.setName(name);
            contact.setPhone(phonenumber);
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

        btn_addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),newBaseContactForm.class);
                startActivity(intent);
            }
        });

        btn_addcontact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(v.getContext(),GenerateContacts.class);
                startActivity(intent);
                return false;
            }
        });


        btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortType++;
                if (sortType>1){
                    sortType=0;
                }
                if(sortType==0){
                    //ABC sort
                    Collections.sort(addressBook.getContactBook(), new Comparator<BaseContact>() {
                        @Override
                        public int compare(BaseContact o1, BaseContact o2) {
                            return o1.getName().getFirstName().compareToIgnoreCase(o2.getName().getFirstName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                }else if(sortType==1){
                    //ABC sort
                    Collections.sort(addressBook.getContactBook(), new Comparator<BaseContact>() {
                        @Override
                        public int compare(BaseContact o1, BaseContact o2) {
                            return o2.getName().getFirstName().compareToIgnoreCase(o1.getName().getFirstName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
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

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                Log.d("tag",addressBook.getContactBook().size()+"");
                if(text.length() > 0){
                    List<BaseContact> temptArray = new ArrayList<BaseContact>();
                    for (BaseContact xcontact : addressBook.getContactBook()){
                        if(xcontact.getName().getFullName().toLowerCase().contains(text.toLowerCase())){
                            if(!temptArray.contains(xcontact)){
                                temptArray.add(xcontact);
                            }
                        }
                        if(xcontact.getPhone().toLowerCase().contains(text.toLowerCase())){
                            if(!temptArray.contains(xcontact)){
                                temptArray.add(xcontact);
                            }
                        }
                        if(xcontact.getAddress().getFullAddress().toLowerCase().contains(text.toLowerCase())){
                            if(!temptArray.contains(xcontact)){
                                temptArray.add(xcontact);
                            }
                        }
                        if(xcontact.getDateOfBirth().getFullDateOfBirth().toLowerCase().contains(text.toLowerCase())){
                            if(!temptArray.contains(xcontact)){
                                temptArray.add(xcontact);
                            }
                        }
                        if(xcontact.getEmail().toLowerCase().contains(text.toLowerCase())){
                            if(!temptArray.contains(xcontact)){
                                temptArray.add(xcontact);
                            }
                        }
                        if(xcontact.getUrl().toLowerCase().contains(text.toLowerCase())){
                            if(!temptArray.contains(xcontact)){
                                temptArray.add(xcontact);
                            }
                        }
                    }
                    lv_contacts.setAdapter(new PersonAdapter(MainActivity.this, new AddressBook(temptArray)));
                }
                else {
                    lv_contacts.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        btn_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressBook.getContactBook().removeAll(addressBook.getContactBook());
                addressBook.getContactBook().addAll(load().getContactBook());
                adapter.notifyDataSetChanged();
            }
        });
    }

    //save data to sharedpreferences

    public void save(){
        Gson gson = new Gson();
        String text = gson.toJson(addressBook);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());

            //Toast.makeText(this,"Saved "+getFilesDir()+"/"+FILE_NAME,Toast.LENGTH_SHORT).show();

        }catch(FileNotFoundException e){

        }catch (IOException e){

        }finally {
            if(fileOutputStream!= null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public AddressBook load(){
        FileInputStream fileInputStream = null;
        Gson gson = new Gson();
        AddressBook toReturn = null;
        try {
            fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String test;

            while ((test = bufferedReader.readLine())!=null){
                stringBuilder.append(test).append("\n");
            }

            toReturn = gson.fromJson(stringBuilder.toString(), AddressBook.class);

            //Toast.makeText(this,"Got: "+toReturn.getContactBook().size(),Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (toReturn==null){
                addressBook = ((MyApplication)this.getApplication()).getAddressBook();
            }
        }
        return toReturn;
    }


}
