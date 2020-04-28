package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.Random;

public class newBaseContactForm extends AppCompatActivity {

    Button btn_add, btn_cancel;

    EditText et_firstname, et_middlename, et_lastname, et_nickname;
    EditText et_phonenumber;
    EditText et_streetnumber, et_street, et_city, et_state, et_postal, et_country;
    EditText et_day, et_month, et_year;
    EditText et_email, et_url, et_description;

    int positionToEdit = -1;

    private String[] monthList = {"January", "February", "March","April","May","June"
            ,"July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_base_contact_form);

        btn_add = findViewById(R.id.btn_addcontact);
        btn_cancel = findViewById(R.id.btn_cancel);

        //gather name
        et_firstname = findViewById(R.id.et_firstname);
        et_middlename = findViewById(R.id.et_middlename);
        et_lastname = findViewById(R.id.et_lastname);
        et_nickname = findViewById(R.id.et_nickname);

        //gather phone
        et_phonenumber = findViewById(R.id.et_phonenumber);

        //gather address
        et_streetnumber = findViewById(R.id.et_streetnumber);
        et_street = findViewById(R.id.et_street);
        et_city = findViewById(R.id.et_city);
        et_state = findViewById(R.id.et_state);
        et_postal = findViewById(R.id.et_postal);
        et_country = findViewById(R.id.et_country);

        //gather date of birth
        et_day = findViewById(R.id.et_day);
        et_month = findViewById(R.id.et_month);
        et_year = findViewById(R.id.et_year);

        //gather the rest
        et_email = findViewById(R.id.et_email);
        et_url = findViewById(R.id.et_url);
        et_description = findViewById(R.id.et_notes);


        //listen if filled in
        Bundle incomingIntent = getIntent().getExtras();
        if (incomingIntent!= null){
            String firstname = incomingIntent.getString("firstname");
            String middlename = incomingIntent.getString("middlename");
            String lastname = incomingIntent.getString("lastname");
            String nickname = incomingIntent.getString("nickname");

            //phone number
            String phonenumber = incomingIntent.getString("phonenumber");

            //address
            String streetnumber = incomingIntent.getString("streetnumber");
            String street = incomingIntent.getString("street");
            String city = incomingIntent.getString("city");
            String state = incomingIntent.getString("state");
            String postal = incomingIntent.getString("postal");
            String country = incomingIntent.getString("country");

            //date of birth
            String day = incomingIntent.getString("day");
            String month = incomingIntent.getString("month");
            String year = incomingIntent.getString("year");


            //rest
            String email = incomingIntent.getString("email");
            String url = incomingIntent.getString("url");
            String description = incomingIntent.getString("description");

            //check if edited
            positionToEdit = incomingIntent.getInt("edit");


            et_firstname.setText(firstname);
            et_middlename.setText(middlename);
            et_lastname.setText(lastname);
            et_nickname.setText(nickname);

            et_phonenumber.setText(phonenumber);

            et_streetnumber.setText(streetnumber);
            et_street.setText(street);
            et_city.setText(city);
            et_state.setText(state);
            et_postal.setText(postal);
            et_country.setText(country);

            et_day.setText(day);
            et_month.setText(month);
            et_year.setText(year);

            et_email.setText(email);
            et_url.setText(url);
            et_description.setText(description);
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //establish intent
                Intent intent = new Intent(v.getContext(),MainActivity.class);


                //get string from the edit text objects
                    //name
                String firstname = et_firstname.getText().toString();
                String middlename = et_middlename.getText().toString();
                String lastname = et_lastname.getText().toString();
                String nickname = et_nickname.getText().toString();

                    //phone number
                String phonenumber = et_phonenumber.getText().toString();

                    //address
                String streetnumber = et_streetnumber.getText().toString();
                String street = et_street.getText().toString();
                String city = et_city.getText().toString();
                String state = et_state.getText().toString();
                String postal = et_postal.getText().toString();
                String country = et_country.getText().toString();

                    //date of birth
                String day = et_day.getText().toString();
                String month;
                if(TextUtils.isEmpty(et_month.getText().toString())){
                    month = null;
                }else if(Arrays.asList(monthList).contains(et_month.getText().toString())){
                    month = monthList[Arrays.asList(monthList).indexOf(et_month.getText().toString())];
                }else if(Integer.parseInt(et_month.getText().toString()) <= 12) {
                    month = monthList[Integer.parseInt(et_month.getText().toString())];
                }else if (Integer.parseInt(et_month.getText().toString()) > 12 ){
                    month = monthList[new Random().nextInt(monthList.length)];
                }else {
                    month = monthList[new Random().nextInt(monthList.length)];
                }
                String year = et_year.getText().toString();

                    //rest
                String email = et_email.getText().toString();
                String url = et_url.getText().toString();
                String description = et_description.getText().toString();

                //put the string in to a message for main activity
                intent.putExtra("firstname",firstname);
                intent.putExtra("middlename",middlename);
                intent.putExtra("lastname",lastname);
                intent.putExtra("nickname",nickname);

                intent.putExtra("phonenumber",phonenumber);

                intent.putExtra("streetnumber",streetnumber);
                intent.putExtra("street",street);
                intent.putExtra("city",city);
                intent.putExtra("state",state);
                intent.putExtra("postal",postal);
                intent.putExtra("country",country);

                intent.putExtra("day",day);
                intent.putExtra("month",month);
                intent.putExtra("year",year);

                intent.putExtra("email",email);
                intent.putExtra("url",url);
                intent.putExtra("description",description);

                intent.putExtra("edit",positionToEdit);

                //start main activity again

                //main check


                if (TextUtils.isEmpty(firstname)){
                    et_firstname.setError("First Name cannot be empty");
                }
                if (TextUtils.isEmpty(lastname)){
                    et_lastname.setError("Last Name cannot be empty");
                }
                if (TextUtils.isEmpty(phonenumber)){
                    et_phonenumber.setError("Phone Number cannot be empty");
                }
                if ((firstname.length() > 0) && (lastname.length() > 0) &&(phonenumber.length() >0)){
                    startActivity(intent);
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
