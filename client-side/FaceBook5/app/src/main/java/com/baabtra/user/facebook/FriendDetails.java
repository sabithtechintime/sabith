package com.baabtra.user.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FriendDetails extends Activity {
ListView list;
    List<Bean> rowItem;
    MysqlHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_details);
        list = (ListView) findViewById(R.id.details);
        db= new MysqlHelper(this);
        Intent i = getIntent();
        String u =i.getStringExtra("value");
        user(u);
    }
    public void user(String e)
    {
        ArrayList<String> all = new ArrayList<String>();
        all = db.getDetails(e);
        String id1 =all.get(0);
        String id2 =all.get(1);
        String id3 =all.get(2);
        String id4=all.get(3);
        String id5=all.get(4);
        rowItem = new ArrayList<Bean>();
        Bean item =  new Bean(id1,id2,id3,id4,id5);
        rowItem.add(item);
        DetailsAdapter custom = new DetailsAdapter(FriendDetails.this,rowItem);
        list.setAdapter(custom);
        System.out.println("hahah"+id1);
        System.out.println(all);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_details, menu);
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
