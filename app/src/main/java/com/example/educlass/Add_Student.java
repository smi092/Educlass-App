package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
//import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educlass.utils.DBCollections;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;

public class Add_Student<firebaseFirestore, ref> extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    DocumentReference ref;
    String MobilePattern = "[0-9]{10}";
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[<>/+-@#$])(?=\\S+$).{8,}$";
    EditText stdname,stdmail,stdpassword,stdroll,stdclass,stdpno;
    Button addstdudents;
    RadioGroup stdgender;
    ProgressDialog loader;
    FirebaseAuth mAuth;
    Spinner gender;
    DatabaseReference userDatabaseRef;
    public static final String TAG="TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
       stdname=findViewById(R.id.stName);
       stdmail=findViewById(R.id.stEmail);
       stdpassword=findViewById(R.id.stPassword);
       stdroll=findViewById(R.id.stdrollno);
       stdclass=findViewById(R.id.clsName);
       stdpno=findViewById(R.id.stdParent);
       loader=new ProgressDialog(this);
       gender=findViewById(R.id.spingender);
       addstdudents=findViewById(R.id.addButton);
        mAuth= FirebaseAuth.getInstance();

        /*firebaseFirestore=FirebaseFirestore.getInstance();
        ref=firebaseFirestore.collection("std_register").document();
*/
        addstdudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Stdname=stdname.getText().toString();
                final String Stdmail=stdmail.getText().toString();
                final String rnum=stdroll.getText().toString();
                final  String password=stdpassword.getText().toString();
               final  String parentno=stdpno.getText().toString();
              final String sclass=stdclass.getText().toString();
              String Gen=gender.getSelectedItem().toString();

                if (stdname.getText().toString().equals("")) {
                    Toast.makeText(Add_Student.this, "Please type a username", Toast.LENGTH_SHORT).show();

                } else if (stdmail.getText().toString().equals("")) {
                    Toast.makeText(Add_Student.this, "Please type a email id", Toast.LENGTH_SHORT).show();
                }else if(!stdmail.getText().toString().matches(emailPattern)) {

                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                }

                else if (stdpassword.getText().toString().equals("")) {
                    Toast.makeText(Add_Student.this, "Please type a password", Toast.LENGTH_SHORT).show();
                } else if(!(stdpassword.getText().toString().length()>=8||stdpassword.getText().toString().matches(passwordPattern))){
                    Toast.makeText(Add_Student.this,"Password is invalid",Toast.LENGTH_SHORT).show();
                }else if (stdroll.getText().toString().equals("")) {
                    Toast.makeText(Add_Student.this, "Please type a rollno", Toast.LENGTH_SHORT).show();
               /* }else if(!stdphone.getText().toString().matches(MobilePattern)) {

                    Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
*/
                }/*else if (stdgender.equals("")) {
                    Toast.makeText(Add_Student.this, "Please select gender", Toast.LENGTH_SHORT).show();

                }*/
                else if (stdclass.getText().toString().equals("")) {
                    Toast.makeText(Add_Student.this, "Please type  class", Toast.LENGTH_SHORT).show();
                }else if (stdpno.getText().toString().equals(""))
                {
                    Toast.makeText(Add_Student.this, "Please type a parent phone no", Toast.LENGTH_SHORT).show();
                } else if(!stdpno.getText().toString().matches(MobilePattern)) {

                    Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();

                }
                else {
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    mAuth.createUserWithEmailAndPassword(Stdmail,password).addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            String error = task.getException().toString();
                            Toast.makeText(Add_Student.this, "ERROR" + error, Toast.LENGTH_LONG).show();
                            loader.dismiss();
                        }
                        else{
                            String currentUserId = mAuth.getCurrentUser().getUid();
                            userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                                    .child(new DBCollections().StdRegister);
                            HashMap userinfo = new HashMap<>();
                            //userinfo.put("id",currentUserId);
                            userinfo.put("Name",Stdname);
                            userinfo.put("Email",Stdmail);
                            userinfo.put("password",password);
                            userinfo.put("Roll",rnum);
                            userinfo.put("gender",Gen);
                            userinfo.put("class",sclass);
                            userinfo.put("parent no",parentno);
                            userDatabaseRef.child(rnum).updateChildren(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Add_Student.this, "Data set successfully", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(Add_Student.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                                    }
                                    finish();
                                    loader.dismiss();
                                }
                            });

                        }
                    });
                }


                         }
        });



    }
}