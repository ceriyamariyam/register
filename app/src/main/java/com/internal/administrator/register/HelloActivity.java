package com.internal.administrator.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {
    Button b1,b2,b3;

TextView tv;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        tv=(TextView)findViewById(R.id.t);
        final SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String data= sharedPreferences.getString("name",null);
        tv.setText("HELLO "+data);
        b1=(Button)findViewById(R.id.pedit);
        b2=(Button)findViewById(R.id.raccount);
        b3=(Button)findViewById(R.id.logout);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ProfileeditActivity.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=getSharedPreferences("login",MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
