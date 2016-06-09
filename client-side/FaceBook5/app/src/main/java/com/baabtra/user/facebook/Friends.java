package com.baabtra.user.facebook;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Friends extends Fragment {

    View rootview;
    ListView listView;
    MysqlHelper db;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.activity_friends, container, false);
        listView = (ListView) rootview.findViewById(R.id.Friends);
        db = new MysqlHelper(getActivity());
        sharedPreferences = getActivity().getSharedPreferences("myprefer", Context.MODE_PRIVATE);
        String e = sharedPreferences.getString("emailpass","");
        users(e);
        return rootview;
    }

    public void users(String e) {
   ArrayList<String> emails = new ArrayList<String>();
        System.out.println("iam  here"+e);
   emails = db.getFriends(e);
    FriendsAdapter fr = new FriendsAdapter(getActivity(),emails);
    listView.setAdapter(fr);
    }
}