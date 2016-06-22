package sab123.user.frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    TextView t1;
    Button b1,b2;
    Mysql sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sq = new Mysql(this);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.l);
        b2=(Button)findViewById(R.id.b2);
        b1.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View v) {
          open();
        }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(Login.this,Signup.class);
                //startActivity(i);
                Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
                close();
            }
        });
    }
    public void close(){
        Intent i = new Intent(Login.this,Signup.class);
        startActivity(i);
    }
    public void open(){
        String u = e1.getText().toString();
        String v =e2.getText().toString();
        int r=sq.getuser(u,v);
        if(r==1)
        {
            Intent j =new Intent(Login.this,Home.class);
            startActivity(j);
        }
        else{
            Toast.makeText(Login.this,"sorry invalid username or password",Toast.LENGTH_SHORT).show();
        }
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
