package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class joinclass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinclass);
        Button kjoinbtn=(Button) findViewById(R.id.kanjoin);
        kjoinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(joinclass.this,Viewclasslink.class);
                startActivity(intent);
            }
        });
        Button Ejoinbtn=(Button) findViewById(R.id.Englishjoin);
       Ejoinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(joinclass.this,Viewclasslink.class);
                startActivity(intent);
            }
        });
        Button Hjoinbtn=(Button) findViewById(R.id.hindijoin);
        Hjoinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(joinclass.this,Viewclasslink.class);
                startActivity(intent);
            }
        });

        Button Socjoinbtn=(Button) findViewById(R.id.socialjoin);
        Socjoinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(joinclass.this,Viewclasslink.class);
                startActivity(intent);
            }
        });
        Button Scinjoinbtn=(Button) findViewById(R.id.sciencejoin);
      Scinjoinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(joinclass.this,Viewclasslink.class);
                startActivity(intent);
            }
        });
        Button mathjoinbtn=(Button) findViewById(R.id.Mathsjoin);
        mathjoinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(joinclass.this,Viewclasslink.class);
                startActivity(intent);
            }
        });



    }
}