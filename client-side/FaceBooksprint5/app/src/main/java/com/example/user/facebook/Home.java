package com.example.user.facebook;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends Fragment {

TextView t1,t2,t3,t4,t5;
    ImageView im1,im2,im3,im4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home,container,false);
        Intent i = getActivity().getIntent();
        String s = i.getStringExtra("result");
        t5=(TextView)rootView.findViewById(R.id.result);
        t5.setText(s);
        t1=(TextView)rootView.findViewById(R.id.t1);
        t2=(TextView)rootView.findViewById(R.id.t2);
        t3=(TextView)rootView.findViewById(R.id.t3);
        t4=(TextView)rootView.findViewById(R.id.t4);
        im1=(ImageView)rootView.findViewById(R.id.im1);
        im2=(ImageView)rootView.findViewById(R.id.im2);
        im3=(ImageView)rootView.findViewById(R.id.im3);
        im4=(ImageView)rootView.findViewById(R.id.im4);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Add_Location.class);
                startActivity(i);
            }
        });

        return rootView;
    }
}
