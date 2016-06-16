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
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<String> {
Context c;
    ArrayList<String> all;
    public Adapter(Context context, ArrayList<String> resource) {
        super(context,R.layout.activity_adapter,R.id.text, resource);
        this.c=context;
        this.all=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater l =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = l.inflate(R.layout.activity_adapter,parent,false);
        TextView t = (TextView)row.findViewById(R.id.text);
        t.setTag(position);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                String s =getItem(position);
                System.out.println("yahoo"+s);
                Intent i = new Intent(c,Updatestatus.class);
                i.putExtra("value",s);
                c.startActivity(i);
            }
        });
        t.setText(all.get(position));
        return row;
    }

}
