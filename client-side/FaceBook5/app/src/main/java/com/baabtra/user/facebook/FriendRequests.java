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
import java.util.zip.Inflater;

public class FriendRequests extends Fragment {

    View rootview;
    ListView list;
    MysqlHelper db;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.activity_friend_requests, container, false);
        list = (ListView) rootview.findViewById(R.id.RequestLists);
        sharedPreferences = getActivity().getSharedPreferences("myprefer", Context.MODE_PRIVATE);
        db = new MysqlHelper(getActivity());
        String e = sharedPreferences.getString("emailpass", "");
        System.out.println("iam user"+e);
        users(e);
        return rootview;
    }

    public void users(String e) {
        ArrayList<String> emails = new ArrayList<String>();
        int val =db.getId(e);
        System.out.println("my id"+val);
        String v = db.getEmail(val);
        System.out.println("its again me"+v);
       emails = db.getRequests(e);
        RequestAdapter re = new RequestAdapter(getActivity(), emails);
        list.setAdapter(re);
    }
}