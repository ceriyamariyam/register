package com.internal.administrator.register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2/4/2019.
 */
public class dbhelper extends SQLiteOpenHelper {
    public static final String dbname="mydb.db";
    public static final String tablename="register";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="email";
    public static final String col4="mobno";
    public static final String col5="username";
    public static final String col6="password";


    public dbhelper(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table "+tablename+"("+col1+ "  integer primary key autoincrement, "+col2+ " text, "+col3+ " text, "+col4+ " text, "+col5+ " text, "+col6+ " text )";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query="drop table if exists"+tablename;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }
    public boolean insertdata (String name,String email,String mobno,String username,String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, name);
        contentValues.put(col3, email);
        contentValues.put(col4, mobno);
        contentValues.put(col5, username);
        contentValues.put(col6, password);

        long status = sqLiteDatabase.insert(tablename, null, contentValues);
        if (status == -1) {
            return false;
        } else {
            return true;


        }
    }
//        retrieve
        public Cursor search(String username ) {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor cur=sqLiteDatabase.rawQuery("SELECT * FROM "+tablename+ " WHERE "+col5+ "='"+username+"'",null);
            return cur;


        }
    public Cursor searchid(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cur=sqLiteDatabase.rawQuery("SELECT * FROM "+tablename+ " WHERE "+col1+ "="+id,null);
        return cur;
    }
    public boolean updatedata (String id,String name,String email,String mobno,String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,email);
        contentValues.put(col4,mobno);
        contentValues.put(col5,username);
        contentValues.put(col6,password);
      long status=db.update(tablename,contentValues,col1+"=" +id,null);
        if (status==-1)
        {
            return false;

        }
else
        {
            return true;
        }
    }
    public boolean deletedata(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long status=db.delete(tablename,col1+"="+id,null);
        if (status==-1)
        {
            return false;

        }
        else
        {
            return true;
        }
    }
}
