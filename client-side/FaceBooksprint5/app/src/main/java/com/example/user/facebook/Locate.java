package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Locate extends Activity {
    double lan,lon;
    ListView list;
    int maxresult=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);
        list =(ListView)findViewById(R.id.list);
        Intent i = getIntent();
        lan =i.getDoubleExtra("value1",0);
        lon=i.getDoubleExtra("value",0) ;
        user();
    }
    public void user(){
        if(lan!=0&&lon!=0) {
            Geocoder geocoder = new Geocoder(Locate.this, Locale.ENGLISH);
            List<Address> address = null;
            try {
                address = geocoder.getFromLocation(lan, lon, maxresult);
                Address address1 = address.get(0);
                ArrayList<String> al = new ArrayList<String>();
                for (int i = 0; i < address1.getMaxAddressLineIndex(); i++) {
                    al.add(address1.getAddressLine(i));
                }
                if (al != null) {
                    Adapter ad = new Adapter(Locate.this, al);
                    list.setAdapter(ad);
                } else {
                    Toast.makeText(Locate.this, "Sorry error", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(Locate.this, "Some thing wrong", Toast.LENGTH_SHORT).show();
            }

        }
       // MyLocationListener listener = new MyLocationListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_locate, menu);
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
