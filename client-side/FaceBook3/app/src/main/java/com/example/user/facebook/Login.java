package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends Activity {
    public static String Log_url ="http://api.baabtra.com/facebook1/index.php/Services_api/login";
    public static String Key_email="email";
    public static String Key_pass="password";
    public static String Mypreference = "Myprefer";
    SharedPreferences sharedPreferences;

    private String email;
    private String password;
    private EditText e1,e2;
    TextView t1;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        b=(Button) findViewById(R.id.button);
        t1=(TextView)findViewById(R.id.textView);
        sharedPreferences = getSharedPreferences(Mypreference, Context.MODE_PRIVATE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }
    private void userLogin()
    {
        email = e1.getText().toString();
        password=e2.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key_email,email);
        editor.commit();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Log_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("200") && s.contains("success")) {
                    openProfile();
                } else {
                    Toast.makeText(Login.this, "Error Loginfailed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Login.this,"Error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                map.put(Key_email,email);
                map.put(Key_pass,password);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void openProfile()
    {
        Intent i = new Intent(Login.this,Homepage.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
