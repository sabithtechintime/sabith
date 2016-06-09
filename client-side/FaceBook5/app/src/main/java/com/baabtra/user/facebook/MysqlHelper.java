package com.baabtra.user.facebook;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 6/7/2016.
 */
public class MysqlHelper extends SQLiteOpenHelper {

    public MysqlHelper(Context context) {
        super(context, "facebook", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table person(id INTEGER PRIMARY KEY AUTOINCREMENT,fname text,lname text,address text,email text,mobile text,password text)");
            db.execSQL("create table login(id integer primary key autoincrement,email text,password text)");
            db.execSQL("create table relation(id1 integer primary key autoincrement,id2 integer,id3 integer ,status integer)");
        } catch (SQLiteException e) {
            Log.d("Error in table creation", e.getLocalizedMessage().toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists person");
        db.execSQL("drop table if exists login");
        db.execSQL("drop table if exists relation");
        onCreate(db);

    }

    public int insertData(String fname, String lname, String address, String email, String mobile, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = -1, second = -1;
        ContentValues register = new ContentValues();
        ContentValues login = new ContentValues();
        register.put("fname", fname);
        register.put("lname", lname);
        register.put("address", address);
        register.put("email", email);
        register.put("mobile", mobile);
        register.put("password", password);
        login.put("email", email);
        login.put("password", password);
        try {

            result = db.insert("person", null, register);
            second = db.insert("login", null, login);

        } catch (SQLiteException e) {
            Log.d("message", e.getLocalizedMessage().toString());

        }
        if (result == -1 || second == -1) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getUsername(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("hai" + username);
        System.out.println("hello" + password);
        long r = -1;
        String get = "select email,password from  person where email=? and password=?";
        try {
            Cursor cursor = db.rawQuery(get, new String[]{username, password});
            System.out.println("for loop" + cursor.getCount());
            if(cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {
                    do {
                        System.out.println("for loop");

                        String user = cursor.getString(0);

                        System.out.println("user " + user);
                        String pass = cursor.getString(1);
                        System.out.println("password " + pass);
                    } while (cursor.moveToNext());
                    r = 0;
                }
            }

        } catch (SQLiteException e) {
            Log.d("message", e.getLocalizedMessage().toString());
        }
       /* int count=cursor.getCount();*/
        if (r == -1 ) {
            return 0;

        } else {
            return 1;
        }

    }

    public ArrayList<String> listUser() {
        ArrayList<String> allemails = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from person";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String user = cursor.getString(cursor.getColumnIndex("email"));
                System.out.println(user);
                allemails.add(user);
            } while (cursor.moveToNext());
        }
        return allemails;
    }
    public int getId(String email1,String email2)
    {   SQLiteDatabase db = this.getReadableDatabase();
        Integer u1 =-1,u2 =-1;
        String query1 ="select id from person where email=?";
        Cursor cursor = db.rawQuery(query1,new String[]{email1});
        System.out.println("for loop" + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                System.out.println("for loop");
                u1 = cursor.getInt(cursor.getColumnIndex("id"));
                System.out.println(u1);
            } while (cursor.moveToNext());
        }
        cursor = db.rawQuery(query1,new String[]{email2});
        System.out.println("for loop" + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                System.out.println("for loop");
                u2 = cursor.getInt(cursor.getColumnIndex("id"));
                System.out.println(u2);
            } while (cursor.moveToNext());
        }
        if(u1!=-1 && u2!=-1 ) {
            int respone = friendRequest(u1, u2);
            if(respone == 1)
            {
                return 1;
            }
            else
            {
              return 0;
            }
        }
        else
        {
            return 1;
        }
        }
    public int friendRequest(int u1, int u2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues rel = new ContentValues();
        rel.put("id2", u1);
        rel.put("id3",u2);
        rel.put("status", 0);
        long result = -1;
        try {
             result=db.insert("relation",null,rel);
        }catch (SQLiteException e)
        {
            Log.d("message",e.getLocalizedMessage().toString());
        }
        if(result == -1)
        {
            return 1;
        }
        else{
            return 0;
        }
        }
        public String getEmail(int id)
        {   String u=null;
            SQLiteDatabase db= this.getReadableDatabase();
            String query ="select email from person where id=?";
            Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(id)});
            System.out.println("for loop" + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    System.out.println("for loop");
                    u = cursor.getString(cursor.getColumnIndex("email"));
                    System.out.println(u);
                } while (cursor.moveToNext());
            }
            return u;

        }
        public int getId(String u)
        {   SQLiteDatabase db= this.getReadableDatabase();
            Integer u2 =-1;
            String query1 ="select id from person where email=?";
            Cursor cursor = db.rawQuery(query1,new String[]{u});
            System.out.println("for loop" + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    System.out.println("for loop");
                    u2 = cursor.getInt(cursor.getColumnIndex("id"));
                    System.out.println(u2);
                } while (cursor.moveToNext());
            }
            return u2;
        }
        public ArrayList<String> getRequests(String u1)
        {   int p = -1;
            ArrayList<String> allemails = new ArrayList<String>();
            SQLiteDatabase db = this.getReadableDatabase();
            System.out.println("I m "+u1);
            int id3 = getId(u1);
            System.out.println("us id"+id3);
            String query = "select id2 from relation where id3=? and status =0";
            Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(id3)});
            System.out.println("for loop" + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    System.out.println("for loop");
                    p = cursor.getInt(cursor.getColumnIndex("id2"));
                    System.out.println(p);
                    String email = getEmail(p);
                    allemails.add(email);

                } while (cursor.moveToNext());
            }
            return  allemails;

        }
    public ArrayList<String> getFriends(String u1)
    {   int p = -1;
        ArrayList<String> allemails = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("I m "+u1);
        int id3 = getId(u1);
        System.out.println("us id"+id3);
        String query = "select id2 from relation where id3=? and status =1";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(id3)});
        System.out.println("for loop" + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                System.out.println("for loop");
                p = cursor.getInt(cursor.getColumnIndex("id2"));
                System.out.println(p);
                String email = getEmail(p);
                allemails.add(email);

            } while (cursor.moveToNext());
        }
        System.out.println("check here"+allemails);
        return  allemails;

    }
    public void accept(String u1,String u2)
    {   SQLiteDatabase db = this.getWritableDatabase();
        int id1=getId(u1);
        int id2=getId(u2);
        String sql="update relation set status =1 where id2=? and id3=? and status=0";
        db.execSQL(sql, new String[]{String.valueOf(id2), String.valueOf(id1)});
    }
    public void decline(String u1,String u2)
    {   SQLiteDatabase db = this.getWritableDatabase();
        int id1=getId(u1);
        int id2=getId(u2);
        String sql="update relation set status =2 where id2=? and id3=? and status=0";
        db.execSQL(sql,new String[]{String.valueOf(id2),String.valueOf(id1)});
    }
    public void block(String u1,String u2)
    {   SQLiteDatabase db = this.getWritableDatabase();
        int id1=getId(u1);
        int id2=getId(u2);
        String sql="update relation set status =3 where id2=? and id3=? and status=1";
        db.execSQL(sql,new String[]{String.valueOf(id2),String.valueOf(id1)});
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        String query ="select * from relation";
        Cursor result=db.rawQuery(query,null);
        System.out.println("you have" + result.getCount());
        return result;
    }
    public ArrayList<String> getDetails(String user)
    {   ArrayList<String> id = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from person where email=?";
        Cursor cursor =db.rawQuery(query,new String[]{user});
        System.out.println("check here"+cursor.getCount());
        if(cursor.moveToFirst())
        {
            do {
                String id1 =cursor.getString(cursor.getColumnIndex("fname"));
                String id2 =cursor.getString(cursor.getColumnIndex("lname"));
                String id3 =cursor.getString(cursor.getColumnIndex("address"));
                String id4 =cursor.getString(cursor.getColumnIndex("email"));
                String id5 =cursor.getString(cursor.getColumnIndex("mobile"));
                id.add(id1);
                id.add(id2);
                id.add(id3);
                id.add(id4);
                id.add(id5);
                System.out.println("finaly"+id);
            }while(cursor.moveToNext());
            }
        return id;
        }
    }


