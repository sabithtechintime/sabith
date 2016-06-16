package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Location extends Activity {
    EditText e1;
    TextView t1;
    Button b1,b2,b3;
    MyLocationListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__location);
        LocationManager loc = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
       listener = new MyLocationListener();
        loc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        loc.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
        e1=(EditText)findViewById(R.id.e1);
        t1=(TextView)findViewById(R.id.t1);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = listener.user();
                Toast.makeText(Add_Location.this, s, Toast.LENGTH_SHORT).show();
                if(listener.getLat()!=0 && listener.getLon()!=0) {
                    Intent i = new Intent(Add_Location.this, Locate.class);
                    i.putExtra("value1",listener.getLat());
                    i.putExtra("value",listener.getLon());
                    startActivity(i);
                }else
                {
                    Toast.makeText(Add_Location.this,"sorry ",Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(Add_Location.this,Locate.class);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__location, menu);
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
