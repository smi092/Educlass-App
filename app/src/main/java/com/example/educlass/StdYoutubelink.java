package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class StdYoutubelink extends AppCompatActivity {
    FirebaseFirestore db;
    Spinner cls,sub;
    Button sButton;
    String selectedSub,selectedClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_youtubelink);
        cls=findViewById(R.id.spinnerclass);
        sub=findViewById(R.id.spinnersubject);
        sButton=findViewById(R.id.sButton);


        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StdYoutubelink.this,listofytlinks.class);
                intent.putExtra("cls",cls.getSelectedItem().toString());
                intent.putExtra("sub",sub.getSelectedItem().toString());
                startActivity(intent);

            }
        });


    }
}