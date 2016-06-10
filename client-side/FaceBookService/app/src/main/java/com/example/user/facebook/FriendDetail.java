package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.List;
import java.util.Map;

public class FriendDetail extends Activity {
    private static final String URL_DETAIL="http://api.baabtra.com/facebook1/index.php/Services_api/Friends_details";
    private String user1="Email_id_user_one" ;
    private String user2= "Email_id_user_two";
    SharedPreferences sharedPreferences;
    ListView list;
    List<Bean> rowItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        sharedPreferences = getSharedPreferences("Myprefer", Context.MODE_PRIVATE);
        String email1=sharedPreferences.getString("emailpass","");
        Intent i = getIntent();
        String email2=i.getStringExtra("value");
        list = (ListView) findViewById(R.id.details1);
        System.out.println("user1"+email1);
        System.out.println("user2"+email2);
        user(email1, email2);
    }
    public void user(final String s1,final String s2)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DETAIL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                if (s.contains("success") && s.contains("success 200")) {
                    try {
                        ArrayList<String> det= new ArrayList<String>();
                        JSONObject o = new JSONObject(s);
                        JSONArray value = o.getJSONArray("data");
                        JSONObject v =value.getJSONObject(0);
                        String id1=v.getString("first_name");
                        String id2=v.getString("last_name");
                        String id3=v.getString("address");
                        String id4=v.getString("Email");
                        String id5=v.getString("Mobile_no");
                        System.out.println("hahah" + id1);
                        det.add(id1);
                        det.add(id2);
                        det.add(id3);
                        det.add(id4);
                        det.add(id5);
                        System.out.println(det);
                        rowItem = new ArrayList<Bean>();
                        Bean bean = new Bean(id1,id2,id3,id4,id5);
                        rowItem.add(bean);
                        System.out.println("iam row"+rowItem);
                        DetailAdapter de = new DetailAdapter(FriendDetail.this,rowItem);
                        list.setAdapter(de);




                    }catch(JSONException e){
                        e.printStackTrace();
                        Toast.makeText(FriendDetail.this,"message"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FriendDetail.this, "Some thing wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(FriendDetail.this,volleyError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError{
                Map<String,String> map = new HashMap<>();
                map.put(user1,s1);
                map.put(user2,s2);
                return map;
            }
        };
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_detail, menu);
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
