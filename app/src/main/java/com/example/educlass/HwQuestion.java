package com.example.educlass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class HwQuestion extends AppCompatActivity {
String kan;
EditText subject;
Spinner cls;
EditText quest;
Button upload,view;
    FirebaseAuth mAuth;
    DatabaseReference userDatabaseRef;
    public static final String TAG="TAG";
String subjects,stdcls,question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw_question);
        upload=findViewById(R.id.btnup);
        cls=findViewById(R.id.spinnercls);
        quest=findViewById(R.id.editquestion);
        view=findViewById(R.id.btnview);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            kan=extras.getString("sub");
        }

        subject=findViewById(R.id.editSubject);
        subject.setText(kan);
        subjects=subject.getText().toString();
        stdcls=cls.getSelectedItem().toString();
        question=quest.getText().toString();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quest.getText().toString().equals("")) {
                    Toast.makeText(HwQuestion.this, "Please enter Question", Toast.LENGTH_SHORT).show();

                }else
                {
                    subjects=subject.getText().toString();
                    stdcls=cls.getSelectedItem().toString();
                    question=quest.getText().toString();
                    HashMap userinfo = new HashMap<>();
                    userinfo.put("class",stdcls);
                    userinfo.put("subject",subjects);
                    userinfo.put("Question",question);
                    myRef.child("HWQuestion").child(subjects).child(stdcls).setValue(userinfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(HwQuestion.this, "Question is uploaded ", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(HwQuestion.this, "Unable to upload questions", Toast.LENGTH_SHORT).show();


                        }
                    });
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjects=subject.getText().toString();
                stdcls=cls.getSelectedItem().toString();
                question=quest.getText().toString();
                Intent i=new Intent(HwQuestion.this,ShowHW.class);
                i.putExtra("sub",subjects);
                i.putExtra("cls",stdcls);
                i.putExtra("qst",question);
                startActivity(i);
            }
        });
    }
}