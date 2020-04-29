package com.example.contactapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonAdapter extends BaseAdapter{

    Activity mActivity;
    AddressBook addressBook;


    public PersonAdapter(Activity mActivity, AddressBook addressBook) {
        this.mActivity = mActivity;
        this.addressBook = addressBook;
    }


    @Override
    public int getCount() {
        return addressBook.getContactBook().size();
    }

    @Override
    public BaseContact getItem(int position) {
        return addressBook.getContactBook().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View oneLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        oneLine = inflater.inflate(R.layout.basic_contact_show,parent,false);

        TextView tv_name, tv_phonenumber, tv_nickname;

        tv_name = oneLine.findViewById(R.id.tv_name);
        tv_phonenumber = oneLine.findViewById(R.id.tv_contactnumber);
        tv_nickname = oneLine.findViewById(R.id.tv_nickname);

        BaseContact person = this.getItem(position);

        tv_name.setText((position+1)+". "+person.getName().getFirstName() + " "+person.getName().getLastName());
        tv_phonenumber.setText(person.getPhone());
        //tv_nickname.setText("nickname: "+person.getName().getNickName());
        tv_nickname.setText("");

        return oneLine;
    }
}
