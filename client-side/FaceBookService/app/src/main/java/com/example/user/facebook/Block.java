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

public class Block extends Activity {

    public static final String Url ="http://api.baabtra.com/facebook1/index.php/Services_api/blockFriend";

    private static final String user1="Email_id_user_one";
    private static final String user2="Email_id_user_two";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        sharedPreferences = getSharedPreferences("Myprefer", Context.MODE_PRIVATE);
        String u=sharedPreferences.getString("emailpass","");
        System.out.println("user1"+u);
        Intent i = getIntent();
        String v=i.getStringExtra("value");
        System.out.println(v);
        user(u,v);
    }
    public void user(final String s1,final String s2 )
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("success") && s.contains("blocked 200")) {
                    Toast.makeText(Block.this, "Blocked", Toast.LENGTH_SHORT).show();
                    openProfile();
                } else {
                    Toast.makeText(Block.this, "notBlocked", Toast.LENGTH_SHORT).show();
                    openProfile();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Block.this,volleyError.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<>();
                map.put(user1,s1);
                map.put(user2,s2);
                return map;
            }
        };
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);
    }
    public void openProfile()
    {
        Intent i = new Intent(Block.this,Homepage.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_block, menu);
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
