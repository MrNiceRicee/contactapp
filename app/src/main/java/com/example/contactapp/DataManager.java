package com.example.contactapp;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataManager extends AppCompatActivity {

    private static final String FILE_NAME = "saveData.txt";


    public void save(AddressBook addressBook){
        Gson gson = new Gson();
        String text = gson.toJson(addressBook);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());

            Toast.makeText(this,"Saved "+getFilesDir()+"/"+FILE_NAME,Toast.LENGTH_SHORT).show();

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

            Toast.makeText(this,"Got: "+toReturn.getContactBook().size(),Toast.LENGTH_SHORT).show();
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
                toReturn = ((MyApplication)this.getApplication()).getAddressBook();
                Log.d("my",toReturn.getContactBook().size()+"");
            }
        }
        return toReturn;
    }
}
