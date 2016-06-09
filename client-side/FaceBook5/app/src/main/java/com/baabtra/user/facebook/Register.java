package com.baabtra.user.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
    EditText e1,e2,e3,e4,e5,e6;
    Button b;
    String fname,lname,address,email,mobile,password;
    MysqlHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new MysqlHelper(this);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        e6=(EditText)findViewById(R.id.e6);
        b=(Button)findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = e1.getText().toString();
                lname=e2.getText().toString();
                address=e3.getText().toString();
                email=e4.getText().toString();
                mobile=e5.getText().toString();
                password=e6.getText().toString();
                System.out.println(fname);
                System.out.println(lname);
                System.out.println(address);
                System.out.println(email);
                System.out.println(mobile);
                System.out.println(password);
                int result = db.insertData(fname,lname,address,email,mobile,password);
                if(result==0)
                {
                    Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                    openfile();
                }
                else
                {
                    Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                }



            }
        });





    }
    public void openfile()
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
