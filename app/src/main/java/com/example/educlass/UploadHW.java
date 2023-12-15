package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UploadHW extends AppCompatActivity {
 public  Button kanHW;
String subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_hw);
        kanHW=(Button) findViewById(R.id.kanHW);
       // int kan=1;


        kanHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=kanHW.getText().toString();
                Intent i=new Intent(UploadHW.this,HwQuestion.class);
                i.putExtra("sub",subject);
                startActivity(i);
            }
        });
        Button engHW=(Button) findViewById(R.id.EngHW);
        engHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=engHW.getText().toString();
                Intent i=new Intent(UploadHW.this,HwQuestion.class);
                i.putExtra("sub",subject);
                startActivity(i);
            }
        });
        Button HindiHW=(Button) findViewById(R.id.HindiHW);
        HindiHW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=HindiHW.getText().toString();
                Intent i=new Intent(UploadHW.this,HwQuestion.class);
                i.putExtra("sub",subject);

                startActivity(i);
            }
        });
        Button socialhw=(Button) findViewById(R.id.socialHW);
       socialhw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=socialhw.getText().toString();
                Intent i=new Intent(UploadHW.this,HwQuestion.class);
                i.putExtra("sub",subject);

                startActivity(i);
            }
        });
        Button scienceHw=(Button) findViewById(R.id.scienceHW);
        scienceHw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=scienceHw.getText().toString();
                Intent i=new Intent(UploadHW.this,HwQuestion.class);
                i.putExtra("sub",subject);

                startActivity(i);
            }
        });
        Button MathsHw=(Button) findViewById(R.id.mathHW);
        MathsHw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=MathsHw.getText().toString();

                Intent i=new Intent(UploadHW.this,HwQuestion.class);
                i.putExtra("sub",subject);

                startActivity(i);
            }
        });
    }
}