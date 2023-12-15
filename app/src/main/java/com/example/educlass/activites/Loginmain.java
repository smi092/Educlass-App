package com.example.educlass.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.educlass.R;
import com.example.educlass.lecturelogin;
import com.example.educlass.studentlogin;

public class Loginmain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);

        Button btn=(Button) findViewById(R.id.ll_button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(Loginmain.this, lecturelogin.class);
                startActivity(i);
            }
        });
        Button btn1=(Button) findViewById(R.id.sl_button);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(Loginmain.this, studentlogin.class);
                startActivity(in);
            }
        });
    }
}