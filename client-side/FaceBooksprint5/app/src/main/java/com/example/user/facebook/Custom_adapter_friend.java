package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_adapter_friend extends ArrayAdapter<String> {
    Context c;
    ArrayList<String> email;

    public Custom_adapter_friend(Context context, ArrayList<String> resource) {
        super(context, R.layout.activity_custom_adapter_friend, R.id.textfriends, resource);
        this.c = context;
        this.email = resource;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lv = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =lv.inflate(R.layout.activity_custom_adapter_friend,parent,false);
        TextView t =(TextView) row.findViewById(R.id.textfriends);
        t.setTag(position);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =(Integer)v.getTag();
                String item =getItem(position);
                Intent i = new Intent(c,FriendDetail.class);
                i.putExtra("value",item);
                c.startActivity(i);
            }
        });
        t.setText(email.get(position));
        return row;

    }
}