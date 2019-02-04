package com.internal.administrator.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    String s1,s2,checkusername,dbid;
dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new dbhelper(this);
        db.getWritableDatabase();
        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        checkusername=sharedPreferences.getString("username",null);
        if (checkusername!=null)
        {
            Intent i=new Intent(getApplicationContext(),HelloActivity.class);
            startActivity(i);
        }
        e1=(EditText)findViewById(R.id.uname);
        e2=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.reg);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
                Cursor cur = db.search(s1);
                if (cur.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "INVALID USERNAME", Toast.LENGTH_LONG).show();


                } else {
                    while (cur.moveToNext()) {
                        String dbname = cur.getString(1);
                        String dbpass = cur.getString(5);
                        dbid=cur.getString(0);
                        if (dbpass.equals(s2)) {
                            SharedPreferences.Editor editor=getSharedPreferences("login",MODE_PRIVATE).edit();
                            editor.putString("name",dbname);
                            editor.putString("id",dbid);

                            editor.apply();
                            Intent i = new Intent(getApplicationContext(), HelloActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s1=e1.getText().toString();
                s2=e2.getText().toString();
                Intent i=new Intent(getApplicationContext(),RegistersActivity.class);
                startActivity(i);

            }
        });
    }
        }
