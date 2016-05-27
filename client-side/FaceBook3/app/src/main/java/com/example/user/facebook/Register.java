package com.example.user.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends Activity {
    public static final String REG_URL="http://api.baabtra.com/facebook1/index.php/Services_api/registration";
    public static final String firstname="fname";
    public static final String lastname="lname";
    public static final String addres="address";
    public static final String emai="email";
    public static final String mob="mobile_phone_no";
    public static final String pass="password";

    private EditText eusername,elname,eaddress,eemail,emobile,epass;
    private Button b;
    private String fname;
    private String lname;
    private String address;
    private String email;
    private String mobile_phone_no;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        eusername=(EditText)findViewById(R.id.editText1);
        elname=(EditText)findViewById(R.id.editText2);
        eaddress=(EditText)findViewById(R.id.editText3);
        eemail=(EditText)findViewById(R.id.editText4);
        emobile=(EditText)findViewById(R.id.editText5);
        epass=(EditText)findViewById(R.id.editText6);
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();

            }
        });
    }
    private void userRegister(){
        fname=eusername.getText().toString();
        lname=elname.getText().toString();
        address=eaddress.getText().toString();
        email=eemail.getText().toString();
        mobile_phone_no=emobile.getText().toString();
        password=epass.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.contains("200") && s.contains("success")) {
                    System.out.println(s);
                    openProfile();
                } else {
                    Toast.makeText(Register.this, "Sorry Registration failed", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Register.this,"Error",Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put(firstname,fname);
                map.put(lastname,lname);
                map.put(addres,address);
                map.put(emai,email);
                map.put(mob,mobile_phone_no);
                map.put(pass,password);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void openProfile()
    {
        Intent i = new Intent(Register.this,Login.class);
        startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
