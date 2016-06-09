package com.baabtra.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class UserAdapter extends ArrayAdapter<String> {
Context c;
    ArrayList<String> allemails;
    public UserAdapter(Context context, ArrayList<String> resource) {
        super(context,R.layout.activity_user_adapter,R.id.username, resource);
        this.c = context;
        this.allemails= resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lv = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = lv.inflate(R.layout.activity_user_adapter,parent,false);
        TextView t =(TextView) row.findViewById(R.id.username);
        Button b=(Button) row.findViewById(R.id.FriendRequest);
        b.setTag(position);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                String item = getItem(position);
                Intent i = new Intent(c,FriendRequest.class);
                i.putExtra("value",item);
                c.startActivity(i);
            }
        });
        t.setText(allemails.get(position));
        return row;
    }
}
