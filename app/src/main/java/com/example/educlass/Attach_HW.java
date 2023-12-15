package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educlass.model.GetQst;
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
import android.os.Bundle;
import android.widget.EditText;

public class Attach_HW<firebaseFirestore, ref> extends AppCompatActivity {
    EditText subject,rno;
    String kan;
    Button attach;
    String subjects;
    String scls;
    String question,rnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach_hw);
        Spinner classpinner = findViewById(R.id.clsspin);
        Button show=findViewById(R.id.showbtn);
        rno=findViewById(R.id.rollno);
        attach=findViewById(R.id.btnattach);
       EditText qst=findViewById(R.id.Hwquestiondisplay);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            kan=extras.getString("sub");
        }

        subject=findViewById(R.id.edsub);
        subject.setText(kan);


show.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String subjects=subject.getText().toString();
        String classstd = classpinner.getSelectedItem().toString();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("HWQuestion").child(subjects).child(classstd);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    Log.d("HWQuestion", snapshot.getValue().toString());
                    GetQst qstion = snapshot.getValue(GetQst.class);
                    qst.setText(qstion.getQuestion());
                }else{
                    Toast.makeText(Attach_HW.this, "Question is not available", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
});
        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjects=subject.getText().toString();
                scls=classpinner.getSelectedItem().toString();
                question=qst.getText().toString();
                 rnum=rno.getText().toString();
                 if(question.isEmpty()){
                     Toast.makeText(Attach_HW.this, "Question was not available", Toast.LENGTH_SHORT).show();
                 }else if(classpinner.getSelectedItem().toString().isEmpty()){
                     Toast.makeText(Attach_HW.this, "Please select class", Toast.LENGTH_SHORT).show();
                }
                 else{
                     Intent i=new Intent(Attach_HW.this,UploadImage.class);
                     i.putExtra("sub",subjects);
                     i.putExtra("cls",scls);
                     i.putExtra("question",question);
                     i.putExtra("rno",rnum);

                     startActivity(i);
                 }


            }
        });

    }
}