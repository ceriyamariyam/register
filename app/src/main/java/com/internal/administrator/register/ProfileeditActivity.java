package com.internal.administrator.register;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileeditActivity extends AppCompatActivity {
EditText e1,e2,e3,e4,e5;
    Button b;
    dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileedit);
        db=new dbhelper(this);
        db.getWritableDatabase();
        e1=(EditText)findViewById(R.id.name1);
        e2=(EditText)findViewById(R.id.email1);
        e3=(EditText)findViewById(R.id.mobno1);
        e4=(EditText)findViewById(R.id.username1);
        e5=(EditText)findViewById(R.id.pass1);
        b=(Button)findViewById(R.id.update);
        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String dbid= sharedPreferences.getString("id",null);
        Cursor cur=db.searchid(dbid);
        if (cur.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cur.moveToNext())
            {
                String getname=cur.getString(1);
                String getemail=cur.getString(2);
                String getmobno=cur.getString(3);
                String getuname=cur.getString(4);
                String getpasw=cur.getString(5);
                e1.setText(getname);
                e2.setText(getemail);
                e3.setText(getmobno);
                e4.setText(getuname);
                e5.setText(getpasw);




            }
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }
}
