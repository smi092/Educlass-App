package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;

public class uploadytlinks extends AppCompatActivity {

    private EditText ytlink;
    private Spinner classSpinner;
    private Button uButton,sButton;
    private TextView subject;
    String sbjct;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadytlinks);
        ytlink=findViewById(R.id.ytLink);
        classSpinner=findViewById(R.id.classSpinner);
        subject=findViewById(R.id.textSub);
        uButton=findViewById(R.id.uButton);
        sButton=findViewById(R.id.sButton);


        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            sbjct=extras.getString("sub");
        }
        subject.setText(sbjct);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Class, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Youtubelinks");

        uButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getytlink=ytlink.getText().toString();
                String getclass=classSpinner.getSelectedItem().toString();
                String getsubject=subject.getText().toString();
                if (ytlink.getText().toString().equals("")) {
                    Toast.makeText(uploadytlinks.this, "Please enter link", Toast.LENGTH_SHORT).show();
                }else {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("ytlink", getytlink);
                    hashMap.put("Class", getclass);
                    hashMap.put("subject", getsubject);


                    databaseReference.child(subject.getText().toString()).child(classSpinner.getSelectedItem().toString()).push().setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(uploadytlinks.this, "Link uploaded successfully", Toast.LENGTH_SHORT).show();
                                    ytlink.setText("");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(uploadytlinks.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(uploadytlinks.this,listofytlinks.class);
                intent.putExtra("cls",classSpinner.getSelectedItem().toString());
                intent.putExtra("sub",subject.getText().toString());
                startActivity(intent);

            }
        });



    }
}