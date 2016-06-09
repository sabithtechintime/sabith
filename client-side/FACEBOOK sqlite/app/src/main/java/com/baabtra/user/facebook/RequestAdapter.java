package com.baabtra.user.facebook;

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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RequestAdapter extends ArrayAdapter<String>{
Context c;
    ArrayList<String> allemails;

    public RequestAdapter(Context context, ArrayList<String> resource) {
        super(context,R.layout.activity_request_adapter,R.id.requsets, resource);
        this.c=context;
        this.allemails=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lv = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = lv.inflate(R.layout.activity_request_adapter,parent,false);

        TextView t1 =(TextView)row.findViewById(R.id.requsets);
        Button b1=(Button)row.findViewById(R.id.Accept);
        Button b2=(Button)row.findViewById(R.id.Decline);

        b1.setTag(position);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                String item = getItem(position);
                Intent intent = new Intent(c, Accept.class);
                intent.putExtra("value", item);
                c.startActivity(intent);

            }
        });
        b2.setTag(position);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                String st = getItem(position);
                Intent j = new Intent(c,Decline.class);
                j.putExtra("value",st);
                c.startActivity(j);
            }
        });
        t1.setText(allemails.get(position));
        return row;
    }
}
