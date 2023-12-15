package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class YoutubelinkAdd extends AppCompatActivity {

    String subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubelink_add);
        Button kbtn=(Button) findViewById(R.id.kanbtn);
        kbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=kbtn.getText().toString();
                Intent intent=new Intent(YoutubelinkAdd.this,uploadytlinks.class);
                intent.putExtra("sub",subject);
                startActivity(intent);
            }
        });
        Button Ebtn=(Button) findViewById(R.id.Engbtn);
        Ebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=Ebtn.getText().toString();
                Intent intent=new Intent(YoutubelinkAdd.this,uploadytlinks.class);
                intent.putExtra("sub",subject);
                startActivity(intent);
            }
        });
        Button hbtn=(Button) findViewById(R.id.Hindibtn);
        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=hbtn.getText().toString();
                Intent intent=new Intent(YoutubelinkAdd.this,uploadytlinks.class);
                intent.putExtra("sub",subject);
                startActivity(intent);
            }
        });
        Button Socbtn=(Button) findViewById(R.id.socialbtn);
        Socbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=Socbtn.getText().toString();
                Intent intent=new Intent(YoutubelinkAdd.this,uploadytlinks.class);
                intent.putExtra("sub",subject);
                startActivity(intent);
            }
        });

        Button Scibtn=(Button) findViewById(R.id.sciencebtn);
        Scibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=Scibtn.getText().toString();
                Intent intent=new Intent(YoutubelinkAdd.this,uploadytlinks.class);
                intent.putExtra("sub",subject);
                startActivity(intent);
            }
        });

        Button Mathbtn=(Button) findViewById(R.id.mathsbtn);
        Mathbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=Mathbtn.getText().toString();
                Intent intent=new Intent(YoutubelinkAdd.this,uploadytlinks.class);
                intent.putExtra("sub",subject);
                startActivity(intent);
            }
        });


    }
}