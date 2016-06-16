package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class post extends Activity {
    public static final String Url="http://api.baabtra.com/sprint5/checkIn.php";
    private static String post="user_text";
    private static String loc="location";
    SharedPreferences sharedPreferences;
    String email,l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        sharedPreferences = getSharedPreferences("Myprefer", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("emailpass", "");
        TextView t1=(TextView)findViewById(R.id.t1);
        TextView t2=(TextView)findViewById(R.id.t2);
        Intent i = getIntent();
        String s=i.getStringExtra("Location");

        String s2=i.getStringExtra("Post");
        l=s2;
        t1.setText(email);
        t2.setText(s2);

        System.out.println("sabit"+s);
        System.out.println("sabith"+s2);
        user(s,s2);
    }
    public void user(final String s1,final String s2)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("200") && s.contains("success")) {
                    send();
                    Toast.makeText(post.this, "no error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(post.this, "error in response", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(post.this,volleyError.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams(){
                Map<String,String> map = new HashMap<>();
                map.put(loc,s1);
                map.put(post,s2);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void send(){
        Intent i = new Intent(post.this,home1.class);
       String s = email;
        String loc=l;
        i.putExtra("result",s);
        i.putExtra("loc",l);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
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
