package com.internal.administrator.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistersActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button b1,b2;
    String s1,s2,s3,s4,s5;

dbhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registers);
        db=new dbhelper(this);
        db.getWritableDatabase();
        e1 = (EditText)findViewById(R.id.name);
        e2 = (EditText)findViewById(R.id.email);
        e3 = (EditText)findViewById(R.id.mobno);
        e4 = (EditText)findViewById(R.id.username);
        e5 = (EditText)findViewById(R.id.pass);
        b1 = (Button) findViewById(R.id.reg1);
        b2 = (Button)findViewById(R.id.back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();
                s5=e5.getText().toString();
                boolean result =db.insertdata(s1,s2,s3,s4,s5);
                if(result==true)
                {
                    Toast.makeText(getApplicationContext(),"INSERTED",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });






    }
}
