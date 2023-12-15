package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class studentHW extends AppCompatActivity {
    String subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_hw);
        Button attachKanHw=findViewById(R.id.stdkanHW);
        attachKanHw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=attachKanHw.getText().toString();

                Intent i=new Intent(studentHW.this,Attach_HW.class);
                i.putExtra("sub",subject);

                startActivity(i);
            }
        });
        Button attachEngHw=findViewById(R.id.stdEngHW);
        attachEngHw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=attachEngHw.getText().toString();

                Intent i=new Intent(studentHW.this,Attach_HW.class);
                i.putExtra("sub",subject);
                startActivity(i);
            }
        });
        Button attachHindiHw=findViewById(R.id.stdHindiHW);
        attachHindiHw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=attachHindiHw.getText().toString();

                Intent i=new Intent(studentHW.this,Attach_HW.class);
                i.putExtra("sub",subject);
                startActivity(i);
            }
        });
        Button attachsocialHw=findViewById(R.id.stdsocialHW);
        attachsocialHw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=attachsocialHw.getText().toString();

                Intent i=new Intent(studentHW.this,Attach_HW.class);

                i.putExtra("sub",subject);
                startActivity(i);
            }
        });
        Button attachscienceHw=findViewById(R.id.stdscienceHW);
        attachscienceHw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=attachscienceHw.getText().toString();

                Intent i=new Intent(studentHW.this,Attach_HW.class);
                i.putExtra("sub",subject);
                startActivity(i);
            }
        });
        Button attachMathsHw=findViewById(R.id.stdmathHW);
        attachMathsHw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject=attachMathsHw.getText().toString();

                Intent i=new Intent(studentHW.this,Attach_HW.class);
                i.putExtra("sub",subject);
                startActivity(i);
            }
        });
    }
}