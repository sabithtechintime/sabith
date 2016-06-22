package sab123.user.frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
EditText e1,e2,e3,e4,e5,e6;
    Button b;
    Mysql sq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        e1=(EditText)findViewById(R.id.fname);
        e2=(EditText)findViewById(R.id.lname);
        e3=(EditText)findViewById(R.id.address);
        e4=(EditText)findViewById(R.id.email);
        e5=(EditText)findViewById(R.id.mobile);
        e6=(EditText)findViewById(R.id.password);
        b=(Button)findViewById(R.id.b1);
        sq=new Mysql(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });

    }
    public void open()
    {
        String s1,s2,s3,s4,s5,s6;
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        s3=e3.getText().toString();
        s4=e4.getText().toString();
        s5=e5.getText().toString();
        s6=e6.getText().toString();

        int f = sq.insert(s1,s2,s3,s4,s5,s6);
        if(f == 0){
            Toast.makeText(Signup.this,"some error",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Signup.this,"success",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Signup.this,Login.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
