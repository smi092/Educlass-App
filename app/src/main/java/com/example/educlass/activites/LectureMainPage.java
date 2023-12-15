package com.example.educlass.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.educlass.Add_Student;
import com.example.educlass.MyChat;
import com.example.educlass.R;
import com.example.educlass.UploadHW;
import com.example.educlass.YoutubelinkAdd;

public class LectureMainPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_mainpage);
        ImageButton addlink=findViewById(R.id.linkid);
        addlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LectureMainPage.this, com.example.educlass.addlink.class);
                startActivity(i);
            }
        });
        ImageButton lechwupload=findViewById(R.id.hwid);
        lechwupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LectureMainPage.this, UploadHW.class);
                startActivity(i);
            }
        });
        ImageButton addstudent=findViewById(R.id.addstd);
        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LectureMainPage.this, Add_Student.class);
                startActivity(i);
            }
        });
        ImageButton addyoutube=findViewById(R.id.vecVideo);
        addyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LectureMainPage.this, YoutubelinkAdd.class);
                startActivity(i);
            }
        });
        ImageButton logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LectureMainPage.this,Loginmain .class);
                startActivity(i);
            }
        });
        ImageButton msg=findViewById(R.id.vecmsg);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LectureMainPage.this, MyChat.class);
                startActivity(i);
            }
        });

    }
}