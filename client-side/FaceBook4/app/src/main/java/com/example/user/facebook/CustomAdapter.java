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

public class CustomAdapter extends ArrayAdapter<String> {
    Context c;
    ArrayList<String> email;

    public CustomAdapter(Context context, ArrayList<String> resource) {
        super(context,R.layout.activity_custom_adapter,R.id.textAdapter ,resource);
        this.c=context;
        this.email=resource;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lv=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lv.inflate(R.layout.activity_custom_adapter,parent,false);

        TextView tv1=(TextView) row.findViewById(R.id.textAdapter);
        Button bt=(Button)row.findViewById(R.id.buttonAdapter);
        bt.setTag(position);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int position=(Integer)arg0.getTag();
                String item=getItem(position);
                System.out.println(item);
                Intent intent = new Intent(c, friendrequest.class);
                intent.putExtra("value", item);
                c.startActivity(intent);
            }
        });
        tv1.setText(email.get(position));
        return row;
    }




}
