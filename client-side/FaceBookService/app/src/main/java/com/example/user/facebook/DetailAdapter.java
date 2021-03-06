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

import java.util.List;

public class DetailAdapter extends ArrayAdapter<Bean> {
Context c;
    private String name;

    public DetailAdapter(Context context, List<Bean> resource) {
        super(context,R.layout.activity_detail_adapter, resource);
        this.c=context;
    }
    private class ViewHolder{
        TextView t1,t2,t3,t4,t5;
        Button b;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final Bean rowItem = getItem(position);
        LayoutInflater lv = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
            convertView = lv.inflate(R.layout.activity_detail_adapter,null);
            holder = new ViewHolder();
            holder.t1=(TextView)convertView.findViewById(R.id.t1);
            holder.t2=(TextView)convertView.findViewById(R.id.t2);
            holder.t3=(TextView)convertView.findViewById(R.id.t3);
            holder.t4=(TextView)convertView.findViewById(R.id.t4);
            holder.t5=(TextView)convertView.findViewById(R.id.t5);
            holder.b=(Button)convertView.findViewById(R.id.block);
            final ViewHolder finalHolder=holder;
            holder.b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = finalHolder.t4.getText().toString();
                    Intent i = new Intent(c,Block.class);
                    i.putExtra("value",name);
                    c.startActivity(i);

                }
            });
            convertView.setTag(holder);


        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.t1.setText(rowItem.getFirst_name());
        holder.t2.setText(rowItem.getLast_name());
        holder.t3.setText(rowItem.getAddress());
        holder.t4.setText(rowItem.getemail());
        holder.t5.setText(rowItem.getMobile());

        return convertView;
    }



}
