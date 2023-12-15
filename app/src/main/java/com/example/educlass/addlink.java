package com.example.educlass;

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



public class addlink <firebaseFirestore, ref>extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    DocumentReference ref;
    EditText link;
    Button upload;
    Spinner subspinner,clsspinner;
    FirebaseAuth mAuth;
    DatabaseReference userDatabaseRef;
    public static final String TAG="TAG";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlink);

/*

        firebaseFirestore=FirebaseFirestore.getInstance();
        ref=firebaseFirestore.collection("onlineclass").document();
*/


         subspinner=findViewById(R.id.spinnersub);
  clsspinner=findViewById(R.id.spinnerclass);
  link=findViewById(R.id.uploadlink);
  upload=findViewById(R.id.uplink);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //myRef.setValue("Hello, World!");
  upload.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          final String subject=subspinner.getSelectedItem().toString();
          final String stdcls=clsspinner.getSelectedItem().toString();
          final String links=link.getText().toString();
          if (link.getText().toString().equals("")) {
              Toast.makeText(addlink.this, "Please enter link", Toast.LENGTH_SHORT).show();

          }else{

                          HashMap userinfo = new HashMap<>();
                          userinfo.put("class",stdcls);
                          userinfo.put("subject",subject);
                          userinfo.put("link",links);
                          myRef.child("onlineclass").child(subject).child(stdcls).setValue(userinfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void unused) {

                                  Toast.makeText(addlink.this, "link is uploaded ", Toast.LENGTH_SHORT).show();
                              }
                          }).addOnFailureListener(new OnFailureListener() {
                              @Override
                              public void onFailure(@NonNull Exception e) {
                                  Toast.makeText(addlink.this, "Unable to upload link", Toast.LENGTH_SHORT).show();


                              }
                          });

                      }

/*
              ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                  @Override
                  public void onSuccess(DocumentSnapshot documentSnapshot) {
                      if (documentSnapshot.exists()) {
                          Toast.makeText(addlink.this, "Sorry,this user exists", Toast.LENGTH_SHORT).show();

                      }
                     else{
                          Map<String, Object> reg_entry = new HashMap<>();
                          reg_entry.put("link",link.getText().toString());
                          reg_entry.put("subject",subject.toString());
                          reg_entry.put("class",stdcls.toString());
                          firebaseFirestore.collection("onlineclass")
                                  .add(reg_entry)
                                  .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                      @Override
                                      public void onSuccess(DocumentReference documentReference) {
                                          Toast.makeText(addlink.this, "Successfully added", Toast.LENGTH_SHORT).show();

                                      }
                                  })
                                  .addOnFailureListener(new OnFailureListener() {
                                      @Override
                                      public void onFailure(@NonNull Exception e) {
                                          Log.d("Error", e.getMessage());
                                      }
                                  });

                      }

                  }
              });*/



      }
  });


    }
}