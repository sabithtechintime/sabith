package com.example.user.facebook;

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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class friendrequests extends Fragment {
    public static final String FR_URL = "http://api.baabtra.com/facebook1/index.php/Services_api/showRequests";

    private static final String Key_user="Email_id_user_one";
    SharedPreferences sharedPreferences;
    ListView listView1;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.activity_friendrequests,container,false);

        listView1 = (ListView) rootView.findViewById(R.id.listrequests);
        sharedPreferences = getActivity().getSharedPreferences("Myprefer", Context.MODE_PRIVATE);
        String s = sharedPreferences.getString("emailpass","");
        System.out.println("login"+s);
        requests(s);
        return rootView;
    }
    private void requests(final String s )
    {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, FR_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                if (s.contains("200") && s.contains("success")) {

                    try
                    {
                    JSONObject o = new JSONObject(s);

                        ArrayList<String> allemails = new ArrayList<String>();
                    for(int i =0;i<o.length()-1;i++)
                    {
                        String count = String.valueOf(i);
                        JSONArray value = o.getJSONArray(count);
                        System.out.println(value);
                        JSONObject temp = value.getJSONObject(0);
                        String sample = temp.getString("Email_id");
                        allemails.add(sample);
                        System.out.println(allemails);

                    }
                    CustomAdapter_req custom = new CustomAdapter_req(getActivity(),allemails);
                        listView1.setAdapter(custom);

                }catch(JSONException e){
                        e.printStackTrace();
                        Toast.makeText(getActivity(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),volleyError.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<>();
                map.put(Key_user,s);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }



}
