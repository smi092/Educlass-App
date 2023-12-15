package com.example.educlass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educlass.model.OnlineModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Viewclasslink<firebaseFirestore, ref> extends AppCompatActivity {
    FirebaseFirestore db;
   // DocumentReference ref;
    TextView viewlink1;
    public static final String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewclasslink);
        viewlink1=findViewById(R.id.vlink);
        Spinner subspinner = findViewById(R.id.subspin);
        Spinner classpinner = findViewById(R.id.classspin);
        db = FirebaseFirestore.getInstance();
               Button viewclass = (Button) findViewById(R.id.viewlink);

        viewclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjects = subspinner.getSelectedItem().toString();

                String classstd = classpinner.getSelectedItem().toString();

                DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("onlineclass").child(subjects).child(classstd);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                         if(snapshot.getValue()!=null){
                             Log.d("onlineclass",snapshot.getValue().toString());

                             OnlineModel link=snapshot.getValue(OnlineModel.class);

                             viewlink1.setText(link.getLink());
                         }else {
                             Toast.makeText(Viewclasslink.this,"Link is not available",Toast.LENGTH_SHORT).show();
                             viewlink1.setText("Link is not available");
                         }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Viewclasslink.this,"Link is not available",Toast.LENGTH_SHORT).show();

                    }
                });
               /* db.collection("onlineclass")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String z = document.getString("link");
                                        String y=document.getString("subject");
                                      //  Toast.makeText(Viewclasslink.this, y, Toast.LENGTH_SHORT).show();
                                        if(y.equals("Social".toString()))
                                       // viewlink1.setText(y);
                                            Toast.makeText(Viewclasslink.this, y, Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }
                        });*/
            }
        });


 /*
                      Toast.makeText(Viewclasslink.this, "clicked//", Toast.LENGTH_SHORT).show();
     }
        });*/
       /* viewclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("onlineclass")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                String subject = subspinner.getSelectedItem().toString();
                                String classstd = classpinner.getSelectedItem().toString();
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String z = document.getString("subject");
                                        String y = document.getString("class");
                                        String x = document.getString("link");
                                        if (z.equals("English")) {
                                            viewlink = findViewById(R.id.vlink);
                                            //ink.setText(x);
                                            Toast.makeText(Viewclasslink.this, "link is available", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                    *//*  else
                     {
                          Log.w(TAG,"Error getting doc",task.getException());
                      }*//*

                            }
                        });
*/
           // }
       // });

    }
}