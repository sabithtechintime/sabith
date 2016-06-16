package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class friendrequest extends Activity{

    private static final String REQ_URL="http://api.baabtra.com/facebook1/index.php/Services_api/friendRequest";
    private static final String user1 ="Email_id_user_one";
    private static final String user2 ="Email_id_user_two";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendrequest);

        sharedPreferences = getSharedPreferences("Myprefer", Context.MODE_PRIVATE);
        String email1 = sharedPreferences.getString("emailpass","");
        System.out.println(email1);
        Intent i = getIntent();
        String email2 = i.getStringExtra("value");
//        System.out.println(email2);
        request(email1,email2);

    }
    private void request(final String s1, final String s2)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REQ_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("200") && s.contains("success")) {
                    sendrequest();
                } else {
                    Toast.makeText(friendrequest.this, "Request cannot sent", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(friendrequest.this,"Error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String,String> map = new HashMap<>();
                map.put(user1,s1);
                map.put(user2,s2);
                return map;
            }
        };
        RequestQueue requestQue= Volley.newRequestQueue(this);
        requestQue.add(stringRequest);
    }
    private void sendrequest()
    {
        Intent i = new Intent(friendrequest.this,Homepage.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friendrequest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
