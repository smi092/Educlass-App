package com.example.educlass;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class uploadclass<firebaseFirestore, ref> extends AppCompatActivity{
    FirebaseFirestore firebaseFirestore;
    DocumentReference ref;
    EditText kanlink;
    Button uploadkan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadclass);

        //kanlink=findViewById(R.id.edtkanlink);
        //uploadkan=findViewById(R.id.buttonkan);

       // Intent i=getIntent();

   /* Button btn=findViewById(R.id.button111);
    TextView txt=findViewById(R.id.textView111);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // txt.setText(a.toString());
            Toast.makeText(uploadclass.this,a,Toast.LENGTH_LONG).show();
        }
    });
*/
    }
}