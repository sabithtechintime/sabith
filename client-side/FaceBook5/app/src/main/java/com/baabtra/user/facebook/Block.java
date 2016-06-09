package com.baabtra.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Block extends Activity {
SharedPreferences sharedPreferences;
    MysqlHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        db=new MysqlHelper(this);
        sharedPreferences = getSharedPreferences("myprefer", Context.MODE_PRIVATE);
        String user1=sharedPreferences.getString("emailpass","");
        Intent i = getIntent();
        String user2=i.getStringExtra("Value");
        System.out.println("user1"+user1);
        System.out.println("user2"+user2);
        db.block(user1,user2);
        Intent j = new Intent(Block.this,Homepage.class);
        startActivity(j);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_block, menu);
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
