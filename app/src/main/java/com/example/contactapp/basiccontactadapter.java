package com.example.contactapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class basiccontactadapter extends BaseAdapter {

    Activity activity;
    List<BaseContact> addressBook;

    public basiccontactadapter(Activity mActivity, List<BaseContact> addressBook1){
        this.activity = mActivity;
        this.addressBook = addressBook1;
    }

    @Override
    public int getCount() {
        return addressBook.size();
    }

    @Override
    public BaseContact getItem(int position) {
        return addressBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View basicContactShow;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        basicContactShow = inflater.inflate(R.layout.basic_contact_show,parent,false);

        TextView tv_name = basicContactShow.findViewById(R.id.tv_name);
        TextView tv_number = basicContactShow.findViewById(R.id.tv_contactnumber);
        ImageView iv_pic = basicContactShow.findViewById(R.id.iv_profilepic);

        BaseContact person = this.getItem(position);

        tv_name.setText(person.getName());
        tv_number.setText(person.getPhone());

        return basicContactShow;
    }
}
