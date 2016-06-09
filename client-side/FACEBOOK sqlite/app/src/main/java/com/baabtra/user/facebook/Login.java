package com.baabtra.user.facebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
    public static  final String Myprefer = "myprefer";
    EditText e1,e2;
    TextView t1;
    Button b,b1;
    String user,password;
    MysqlHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences shareprefrences = getSharedPreferences(Myprefer, Context.MODE_PRIVATE);

        db = new MysqlHelper(this);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        t1=(TextView)findViewById(R.id.t1);
        b=(Button)findViewById(R.id.b1);
        b1=(Button)findViewById(R.id.show);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = e1.getText().toString();
                password=e2.getText().toString();
                SharedPreferences.Editor edit = shareprefrences.edit();
                edit.putString("emailpass",user);
                edit.commit();
              int re =  db.getUsername(user,password);
              if(re==1){
                    openfile();
                }
                else{
                  Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

              }

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor re = db.getAllData();
                if(re.getCount()==0)
                {

                    showAll("error", "no data inserted");
                    return;

                }
                StringBuffer text = new StringBuffer();
                if(re.moveToFirst())
                {
                    do {
                        text.append("id "+re.getInt(0)+"\n");
                        text.append("id1 "+re.getInt(1)+"\n");
                        text.append("id2 "+re.getInt(2)+"\n");
                        text.append("status "+re.getInt(3)+"\n\n");
                        System.out.println(text);
                    }while(re.moveToNext());
                    showAll("success",text.toString());
                    }
                }
            }
        );
    }
    public void showAll(String title,String message)
    {
        AlertDialog.Builder dialoh = new AlertDialog.Builder(this);
        dialoh.setCancelable(true);
        dialoh.setTitle(title);
        dialoh.setMessage(message);
        dialoh.show();
    }
    public void openfile(){
        Intent i= new Intent(Login.this,Homepage.class);
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
