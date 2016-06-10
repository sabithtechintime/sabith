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

public class Decline extends Activity {
    public static final String DEC_URL="http://api.baabtra.com/facebook1/index.php/Services_api/declineFriend";

    private static final String user1 ="Email_id_user_one";
    private static final String user2 ="Email_id_user_two";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decline);
        sharedPreferences = getSharedPreferences("Myprefer", Context.MODE_PRIVATE);
        String email1 = sharedPreferences.getString("emailpass","");
        Intent i = getIntent();
        String email2 = i.getStringExtra("value");
        decline(email1,email2 );
    }



    private void decline(final String s1,final String s2) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DEC_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("Request declined 200") && s.contains("success")) {
                    sendrequest();

                } else {
                    Toast.makeText(Decline.this, "Request canot send", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Decline.this,volleyError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String>  getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<>();
                map.put(user1,s1);
                map.put(user2,s2);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void sendrequest()
    {
        Intent i = new Intent(Decline.this,Homepage.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_decline, menu);
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
