package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.educlass.activites.Loginmain;

public class student_mainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mainpage);
        ImageButton takenote=findViewById(R.id.noteid);
        takenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_mainpage.this,MainActivity.class);
                startActivity(i);
            }
        });

        ImageButton  viewytlink=findViewById(R.id.vecVideo1);
        viewytlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_mainpage.this,StdYoutubelink.class);
                startActivity(i);
            }
        });


        ImageButton  viewlink=findViewById(R.id.linkid);
        viewlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_mainpage.this,Viewclasslink.class);
                startActivity(i);
            }
        });

        ImageButton  vlink=findViewById(R.id.logout);
        vlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_mainpage.this,uploadclass.class);
                startActivity(i);
            }
        });

        ImageButton addHW=findViewById(R.id.hwid);
        addHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_mainpage.this,studentHW.class );
                startActivity(i);
            }
        });
        ImageButton takepic=findViewById(R.id.veccamera);
      takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_mainpage.this,captureimg.class );
                startActivity(i);
            }
        });
        ImageButton logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_mainpage.this, Loginmain.class );
                startActivity(i);
            }
        });
        ImageButton msg=findViewById(R.id.vecmsg1);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_mainpage.this, MyChat.class );
                startActivity(i);
            }
        });

    }
}