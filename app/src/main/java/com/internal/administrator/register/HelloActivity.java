package com.internal.administrator.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HelloActivity extends AppCompatActivity {
    Button b1,b2,b3;
dbhelper db;
    String id;
TextView tv;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        db=new dbhelper(this);
        db.getWritableDatabase();
        builder=new AlertDialog.Builder(this);
        builder.setTitle("confirm");
        builder.setMessage("are you sure want to delete");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean status=db.deletedata(id);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(),"DELETE SUCCESSFULLY",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                }
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        tv=(TextView)findViewById(R.id.t);
        final SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String data= sharedPreferences.getString("name",null);
        id= sharedPreferences.getString("id",null);

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
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog alert=builder.create();
               alert.show();
           }
       });


    }
}
