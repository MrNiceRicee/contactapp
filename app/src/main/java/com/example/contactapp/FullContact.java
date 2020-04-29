package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FullContact extends AppCompatActivity {


    final static int PERMISSION_TO_CALL = 1;

    ImageButton btn_edit, btn_back;


    ImageButton btn_call, btn_text, btn_email, btn_maps;

    TextView tv_name, tv_nickname, tv_mobile, tv_email, tv_address1, tv_address2, tv_url, tv_dob, tv_notes;

    AddressBook addressBook;

    BaseContact contact;

    int itemposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullcontact);

        //set parameters
        btn_back = findViewById(R.id.btn_generataback);
        btn_edit = findViewById(R.id.btn_edit);

        btn_call = findViewById(R.id.btn_call);
        btn_text = findViewById(R.id.btn_text);
        btn_email = findViewById(R.id.btn_email);
        btn_maps = findViewById(R.id.btn_map);

        tv_name = findViewById(R.id.tv_name);
        tv_nickname = findViewById(R.id.tv_nickname);
        tv_mobile = findViewById(R.id.tv_getmobile);
        tv_email = findViewById(R.id.tv_getemail);
        tv_address1 = findViewById(R.id.tv_address1);
        tv_address2 = findViewById(R.id.tv_address2);
        tv_url = findViewById(R.id.tv_url);
        tv_dob = findViewById(R.id.tv_dateofbirth);
        tv_notes = findViewById(R.id.tv_notes);


        //grab bundle and other information
        Bundle incomingintent = getIntent().getExtras();
        addressBook = ((MyApplication)this.getApplication()).getAddressBook();
        contact = addressBook.getContactBook().get(incomingintent.getInt("position"));
        itemposition = incomingintent.getInt("position");


        //establish things
        tv_name.setText(contact.getName().getFirstName() + " " +contact.getName().getLastName());
        tv_nickname.setText(contact.getName().getNickName());
        tv_mobile.setText(contact.getPhone());
        tv_email.setText(contact.getEmail());
        //address 12345 W Street St, City, State, Postal
        tv_address1.setText(contact.getAddress().getStreet() + " " +contact.getAddress().getStreet2());
        tv_address2.setText(contact.getAddress().getCity() + ", "+ contact.getAddress().getState() + ", "+ contact.getAddress().getPostal());
        tv_url.setText(contact.getUrl());
        tv_dob.setText(contact.getDateOfBirth().getDay() + " "+contact.getDateOfBirth().getMonth() + " "+ contact.getDateOfBirth().getYear());
        tv_notes.setText(contact.getDescription());



        //listeners

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPerson(itemposition);
            }
        });


        //android services

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(contact.getPhone());
            }
        });

        tv_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(contact.getPhone());
            }
        });

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(contact.getPhone(),"");
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[1];
                addresses[0] = contact.getEmail();
                composeEmail(addresses,"Hello! From someone");
            }
        });

        tv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[1];
                addresses[0] = contact.getEmail();
                composeEmail(addresses,"Hello! From someone");
            }
        });

        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:0,0?q=" + contact.getAddress().getFullAddress();
                Uri mapUri =  Uri.parse(mapsQuery);

                showMap(mapUri);
            }
        });

        tv_address1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:0,0?q=" + contact.getAddress().getFullAddress();
                Uri mapUri =  Uri.parse(mapsQuery);

                showMap(mapUri);
            }
        });

        tv_address2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:0,0?q=" + contact.getAddress().getFullAddress();
                Uri mapUri =  Uri.parse(mapsQuery);

                showMap(mapUri);
            }
        });

        tv_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(contact.getUrl());
            }
        });

    }

    public  void editPerson(int position){
        Intent intent = new Intent(getApplicationContext(), newBaseContactForm.class);

        BaseContact contact = addressBook.getContactBook().get(position);

        intent.putExtra("edit",position);

        intent.putExtra("firstname",contact.getName().getFirstName());
        intent.putExtra("middlename",contact.getName().getMiddleName());
        intent.putExtra("lastname",contact.getName().getLastName());
        intent.putExtra("nickname",contact.getName().getNickName());

        intent.putExtra("phonenumber",contact.getPhone());

        intent.putExtra("streetnumber",contact.getAddress().getStreet());
        intent.putExtra("street",contact.getAddress().getStreet2());
        intent.putExtra("city",contact.getAddress().getCity());
        intent.putExtra("state",contact.getAddress().getState());
        intent.putExtra("postal",contact.getAddress().getPostal());
        intent.putExtra("country",contact.getAddress().getCountry());

        intent.putExtra("day",contact.getDateOfBirth().getDay());
        intent.putExtra("month",contact.getDateOfBirth().getMonth());
        intent.putExtra("year",contact.getDateOfBirth().getYear());

        intent.putExtra("email",contact.getEmail());
        intent.putExtra("url",contact.getUrl());
        intent.putExtra("description",contact.getDescription());

        startActivity(intent);
    }



    //call services

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(FullContact.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    PERMISSION_TO_CALL);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.

        }else {
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }


    public void composeMmsMessage(String phoneNumber,String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"+phoneNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
