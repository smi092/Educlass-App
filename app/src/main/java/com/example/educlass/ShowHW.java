package com.example.educlass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.educlass.model.GetQst;
import com.example.educlass.model.MyAdapter;
import com.example.educlass.model.imgModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ShowHW extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<imgModel> list;
MyAdapter adapter;
Button display;
EditText a,b,c,d;
    String subjects;
    String scls;
    String question,rnum,sub;

//StorageReference reference= FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hw);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            subjects=extras.getString("sub");
            scls=extras.getString("cls");
            question=extras.getString("qst");
            //d=extras.getString("rno");
        }
        a=findViewById(R.id.sub);
         b=findViewById(R.id.cls);
        c=findViewById(R.id.qst);
         d=findViewById(R.id.edrollno);
         a.setText(subjects);
         b.setText(scls);
     //    c.setText(question);
        rnum=d.getText().toString();
        sub=a.getText().toString();

        display=findViewById(R.id.bdisplay);
   recyclerView=findViewById(R.id.recyclerview);
   recyclerView.setHasFixedSize(true);
   recyclerView.setLayoutManager(new LinearLayoutManager(this));
  list=new ArrayList<>();
  adapter=new MyAdapter(this,list);
  recyclerView.setAdapter(adapter);

        String subjects1=a.getText().toString();
        String classstd1 = b.getText().toString();
              // String rollNo=d.getText().toString();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("HWQuestion").child(subjects1).child(classstd1);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null) {
                    Log.d("HWQuestion", snapshot.getValue().toString());
                    GetQst qstion1 = snapshot.getValue(GetQst.class);
                    c.setText(qstion1.getQuestion());
                }else{
                    Toast.makeText(ShowHW.this, "Homework question not uploaded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        display.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          String subjects1=a.getText().toString();
          String classstd1 = b.getText().toString();
           String rollNo=d.getText().toString();
          String quest=c.getText().toString();

          DatabaseReference root= FirebaseDatabase.getInstance().getReference("HWAnswer").child(subjects1).child(rollNo).child(classstd1).child(quest);

          root.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                      imgModel model=dataSnapshot.getValue(imgModel.class);
                      list.add(model);
                  }
                  adapter.notifyDataSetChanged();
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
      }
  });
    }
}