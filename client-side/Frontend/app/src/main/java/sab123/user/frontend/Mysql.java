package sab123.user.frontend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 6/21/2016.
 */
public class Mysql extends SQLiteOpenHelper {
    public Mysql(Context context) {
        super(context, "Reg", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        try {


            db.execSQL("create table detail(id integer primary key autoincrement,fname text,lname text,address text,email text,mobile text,password text)");
            db.execSQL("create table register(id integer primary key autoincrement,user text,password)");
        }catch (SQLiteException e)
        {
            Log.d("message",e.getLocalizedMessage().toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists detail");
        db.execSQL("drop table if exists register");
        onCreate(db);
    }
    public int getuser(String user,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query="select user,password from register where user=? and password=?";
        Cursor cursor=db.rawQuery(query,new String[]{user,pass});
        if(cursor.getCount()!=0)
        {
            return 1;
        }
        else{
            return 0;
        }

        }
    public int insert(String fname,String lname,String address,String email,String mobile,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        ContentValues value1 = new ContentValues();
        value.put("fname",fname);
        value.put("lname",lname);
        value.put("address",address);
        value.put("email",email);
        value.put("mobile",mobile);
        value.put("password",password);
        long r=-1,s=-2;
        value1.put("user",email);
        value1.put("password",password);
        try{
           r= db.insert("detail",null,value);
            s=db.insert("register",null,value1);
        }catch (SQLiteException e)
        {
            Log.d("message",e.getLocalizedMessage().toString());
        }
        if(r==-1&&s==-2)
        {
            return 0;
        }
        else{
            return 1;
        }
    }
}
