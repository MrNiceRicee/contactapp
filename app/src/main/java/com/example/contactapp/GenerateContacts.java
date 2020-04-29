package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GenerateContacts extends AppCompatActivity {

    Button btn_back, btn_generate;

    EditText et_amount;
    AddressBook addressBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_contacts);

        btn_back = findViewById(R.id.btn_generataback);
        btn_generate = findViewById(R.id.btn_confirm);
        et_amount = findViewById(R.id.et_amount);

        addressBook = ((MyApplication)this.getApplication()).getAddressBook();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                try{
                    int amount = Integer.parseInt(et_amount.getText().toString());
                    addressBook.getContactBook().addAll(addressBook.generateContacts(amount));
                    startActivity(intent);
                }catch (Exception e){
                    et_amount.setError("Please enter a number");
                }
            }
        });
    }
}
