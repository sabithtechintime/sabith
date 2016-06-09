package com.baabtra.user.facebook;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Users extends Fragment {
    View rootview;
    ListView  list;
    MysqlHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview =inflater.inflate(R.layout.activity_users,container,false);
        list = (ListView) rootview.findViewById(R.id.Userlist);
        users();
        return rootview;
    }
    public void users()
    {
        db = new MysqlHelper(getActivity());
        ArrayList<String> allemails = new ArrayList<String>();
        allemails = db.listUser();
        UserAdapter userAdapter = new UserAdapter(getActivity(),allemails);
        list.setAdapter(userAdapter);
    }
}
