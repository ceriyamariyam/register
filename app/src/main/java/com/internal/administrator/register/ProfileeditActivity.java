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
    String getname,getemail,getmobno,getuname,getpasw,s1,s2,s3,s4,s5;
    String dbid;

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
         dbid= sharedPreferences.getString("id",null);
        Cursor cur=db.searchid(dbid);
        if (cur.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cur.moveToNext())
            {

                 getname=cur.getString(1);
                 getemail=cur.getString(2);
                 getmobno=cur.getString(3);
                 getuname=cur.getString(4);
                 getpasw=cur.getString(5);
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
             s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();
                s5=e5.getText().toString();
boolean status=db.updatedata(dbid,s1,s2,s3,s4,s5);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(),"UPDATE SUCCESSFULLY",Toast.LENGTH_LONG).show();
                }
else {
                    Toast.makeText(getApplicationContext(),"ERROR IN UPDATE",Toast.LENGTH_LONG).show();
                }

            }
        });




    }
}
