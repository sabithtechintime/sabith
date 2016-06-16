package com.example.user.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class home1 extends Activity {
    public final String URL="http://api.baabtra.com/facebook1/index.php/Services_api/get_name";
    private final String email="Email_id_user_one";
    ImageView im1,im2,im3;
    TextView u,t2,loc,loc2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        im1=(ImageView)findViewById(R.id.im1);
        im2=(ImageView)findViewById(R.id.im2);
        im3=(ImageView)findViewById(R.id.im3);
        u=(TextView)findViewById(R.id.user);
        t2=(TextView)findViewById(R.id.t2);
        loc=(TextView)findViewById(R.id.loc);
        loc2=(TextView)findViewById(R.id.loc2);

        Intent i = getIntent();
        String email=i.getStringExtra("result");
        String locate=i.getStringExtra("loc");
        loc.setText(locate);
        loc2.setText(locate);
        user(email);

    }
    public void user(final String s1)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("200") && s.contains("success")) {
                    try {
                        JSONObject o = new JSONObject(s);
                        JSONArray value = o.getJSONArray("data");
                        JSONObject o1 = value.getJSONObject(0);
                        String name = o1.getString("first_name");
                        u.setText(name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(home1.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(home1.this, "response error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(home1.this,volleyError.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams(){
                Map<String,String> map = new HashMap<>();
                map.put(email,s1);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home1, menu);
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
