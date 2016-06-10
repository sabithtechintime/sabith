package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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

public class CustomAdapter_req extends ArrayAdapter<String> {
    Context s;
    ArrayList<String> email;


    public CustomAdapter_req(Context context, ArrayList<String> resource) {
        super(context,R.layout.activity_custom_adapter_req,R.id.tv1, resource);
        this.s = context;
        this.email=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lv = (LayoutInflater) s.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =lv.inflate(R.layout.activity_custom_adapter_req,parent,false);
        TextView t = (TextView) row.findViewById(R.id.tv1);
        Button b =(Button) row.findViewById(R.id.buttonAd);
        Button b2=(Button) row.findViewById(R.id.buttonDe);
        b.setTag(position);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int position = (Integer) arg0.getTag();
                String item = getItem(position);
                Intent intent = new Intent(s, Accept.class);
                intent.putExtra("value", item);
                s.startActivity(intent);

            }
        });
        b2.setTag(position);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                String item = getItem(position);
                Intent i = new Intent(s,Decline.class);
                i.putExtra("value",item);
                s.startActivity(i);
            }
        });
        t.setText(email.get(position));
        return row;
    }
}
