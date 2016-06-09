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
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsAdapter extends ArrayAdapter<String> {

Context c;
    ArrayList<String> emails;
    public FriendsAdapter(Context context, ArrayList<String> resource) {
        super(context,R.layout.activity_friends_adapter,R.id.FriendsList, resource);
        this.c =context;
        this.emails=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lv = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lv.inflate(R.layout.activity_friends_adapter,parent,false);

        TextView t = (TextView)row.findViewById(R.id.FriendsList);
        Button b =(Button)row.findViewById(R.id.Block);
        b.setTag(position);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                String st = getItem(position);
                Intent i = new Intent(c, Block.class);
                i.putExtra("Value", st);
                c.startActivity(i);
            }
        });
        t.setTag(position);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                String item=getItem(position);
                Intent i= new Intent(c,FriendDetails.class);
                i.putExtra("value",item);
                c.startActivity(i);
            }

        });
        t.setText(emails.get(position));
        return row;
    }
}
