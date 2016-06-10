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
import java.util.zip.Inflater;

public class Friends extends Fragment {
    private static final String Show_Url = "http://api.baabtra.com/facebook1/index.php/Services_api/showFriends";

    private final String key_email ="Email_id_user_one";
    SharedPreferences sharedPreferences;
    ListView listView;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.activity_friends,container,false);
        listView=(ListView) rootView.findViewById(R.id.listfriend);
        sharedPreferences =getActivity().getSharedPreferences("Myprefer", Context.MODE_PRIVATE);
        String email =sharedPreferences.getString("emailpass","");
        System.out.println();
        listFriends(email);
        return rootView;
    }
    private void listFriends( final String s1)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Show_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("200") && s.contains("success")) {
                    try{
                        JSONObject o =new JSONObject(s);
                        ArrayList<String> allemails = new ArrayList<>();
                        for(int j=0;j<o.length()-1;j++)
                        {
                            String count = String.valueOf(j);
                            JSONArray value=o.getJSONArray(count);
                            JSONObject temp =value.getJSONObject(0);
                            String email=temp.getString("Email_id");
                            allemails.add(email);
                        }
                        Custom_adapter_friend custom_adapter_friend = new Custom_adapter_friend(getActivity(),allemails);
                        listView.setAdapter(custom_adapter_friend);

                    }catch (JSONException e){

                        e.printStackTrace();
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    //sendrequest();
                } else {
                    Toast.makeText(getActivity(), "Error requset cannot send", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),volleyError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<>();
                map.put(key_email,s1);
                return map;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
