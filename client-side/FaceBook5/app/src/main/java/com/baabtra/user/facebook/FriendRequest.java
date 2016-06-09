package com.baabtra.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FriendRequest extends Activity {
    MysqlHelper db = new MysqlHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        Intent i = getIntent();
        String email2=i.getStringExtra("value");
        SharedPreferences sharedPreferences = getSharedPreferences("myprefer", Context.MODE_PRIVATE);
        String email1 =sharedPreferences.getString("emailpass","");
        System.out.println("user1"+email1);
        System.out.println("user2"+email2);
        user(email1, email2);
    }
    public void user(String u1,String u2 )
    {
      int result = db.getId(u1,u2);
        if(result==0) {
            Toast.makeText(FriendRequest.this, "success", Toast.LENGTH_SHORT).show();

            Intent j = new Intent(FriendRequest.this, Homepage.class);
            startActivity(j);
        }
        else
        {
            Toast.makeText(FriendRequest.this, "something wrong", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_request, menu);
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
