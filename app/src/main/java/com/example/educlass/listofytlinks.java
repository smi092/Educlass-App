package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.Model;
import com.example.educlass.model.model1;
import com.example.educlass.model.myAdapter1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listofytlinks extends AppCompatActivity {
    String a,b;
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    myAdapter1 adapter;
    ArrayList<model1> list1;
    EditText cls;
    TextView subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofytlinks);
        cls=findViewById(R.id.editCls);
        subject=findViewById(R.id.textsubject);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            a=extras.getString("sub");
            b=extras.getString("cls");

        }
        cls.setText(b);
        subject.setText(a);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Youtubelinks").child(subject.getText().toString()).child(cls.getText().toString());

        list1=new ArrayList<>();
        adapter=new myAdapter1(this,list1);

        recyclerView.setAdapter(adapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        model1 model = dataSnapshot.getValue(model1.class);
                        list1.add(model);
                    }
                }else {
                    Toast.makeText(listofytlinks.this, "Links are not available", Toast.LENGTH_SHORT).show();
                }

                    adapter.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}