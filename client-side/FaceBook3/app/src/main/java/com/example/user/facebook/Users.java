package com.example.user.facebook;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Users extends Fragment {

    public static final String URL ="http://api.baabtra.com/facebook1/index.php/Services_api/users";

    private ListView listView ;
    private String jresponseString;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.activity_users,container,false);
        listView=(ListView) rootView.findViewById(R.id.list);
        users();
        return rootView;
    }
    private void users() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("200") && s.contains("success")) {
                    try {
                        JSONObject o = new JSONObject(s);
                        JSONArray values = o.getJSONArray("data");
                        jresponseString = "";
                        ArrayList<String> allemails = new ArrayList<String>();
                        for (int i = 0; i < values.length(); i++) {
                            JSONObject test = values.getJSONObject(i);
                            String email = test.getString("Email");
                            allemails.add(email);
                            jresponseString += "" + email + "\n\n";
                        }
                        CustomAdapter customAdapter = new CustomAdapter(getActivity(), allemails);
                        listView.setAdapter(customAdapter);
                    } catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(getActivity(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();

                    }
                }
                 else {
                    Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
