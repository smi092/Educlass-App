package com.example.educlass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.app.ProgressDialog;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;


import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class lectureregister<firebaseFirestore, ref> extends AppCompatActivity {

    //FirebaseFirestore firebaseFirestore;
   // DocumentReference ref;
    EditText reg_fullname,reg_phone,reg_password,reg_email,reg_org,reg_orgpassword;
   Button registration;
   RadioGroup reg_gender;
   RadioButton rmale,rfemale;
   Spinner gender;
     ProgressDialog loader;
    String MobilePattern = "[0-9]{10}";
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[<>/+-@#$])(?=\\S+$).{8,}$";
//String gender;
    FirebaseAuth mAuth;
   DatabaseReference userDatabaseRef;
    public static final String TAG="TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectureregister);
       // RadioButton genderbtn=(RadioButton)findViewById(reg_gender.getCheckedRadioButtonId());
       //  String gender=genderbtn.getText().toString();

        mAuth= FirebaseAuth.getInstance();

        reg_fullname=findViewById(R.id.lName);
        reg_email=findViewById(R.id.lEmail);
        reg_password=findViewById(R.id.lPassword);
        reg_phone=findViewById(R.id.lPhone);
        gender=findViewById(R.id.spingen);
        reg_org=findViewById(R.id.orgName);
        reg_orgpassword=findViewById(R.id.orgPassword);
        registration=findViewById(R.id.rgButton);

        loader=new ProgressDialog(this);
       // ProgressDialog loader = new ProgressDialog(this);
/*
reg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==R.id.radioMale)
            gender="Male";
        else if(checkedId==R.id.radioFemale)
        gender="female";

    }
});
*/

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname= reg_fullname.getText().toString().trim();
                final String email= reg_email.getText().toString().trim();
                final String password=reg_password.getText().toString().trim();
                final String phone_no=reg_phone.getText().toString().trim();
               final String Gender=gender.getSelectedItem().toString().trim();
               final  String organisation=reg_org.getText().toString().trim();

              // final String Org_password=reg_orgpassword.getText().toString().trim();
               // final String password=reg_password.getText().toString().trim();
                if (reg_fullname.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a username", Toast.LENGTH_SHORT).show();

                } else if (reg_email.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a email id", Toast.LENGTH_SHORT).show();
                }  else if(!reg_email.getText().toString().matches(emailPattern)) {

                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else if (reg_password.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a password", Toast.LENGTH_SHORT).show();
                }
                else if(!(reg_password.getText().toString().length()>=8||reg_password.getText().toString().matches(passwordPattern))){
                    Toast.makeText(lectureregister.this,"Password is invalid",Toast.LENGTH_SHORT).show();
                }else if (reg_phone.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a phone no", Toast.LENGTH_SHORT).show();
                }else if(!reg_phone.getText().toString().matches(MobilePattern)) {

                    Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();

                }else if (gender.equals("")) {
                    Toast.makeText(lectureregister.this, "Please select gender", Toast.LENGTH_SHORT).show();

                }

                else if (reg_org.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a organisation", Toast.LENGTH_SHORT).show();

                } else if (reg_orgpassword.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a organisation password", Toast.LENGTH_SHORT).show();

                }  else if (!(reg_orgpassword.getText().toString().equals("School123"))) {
                    Toast.makeText(lectureregister.this,"Wrong Organization Password", Toast.LENGTH_SHORT).show();
                } else {
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            String error = task.getException().toString();
                            Toast.makeText(lectureregister.this, "ERROR" + error, Toast.LENGTH_LONG).show();
                            loader.dismiss();
                        }
                        else{
                            String currentUserId = mAuth.getCurrentUser().getUid();
                            userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                                    .child("Lectureregister");
                            HashMap userinfo = new HashMap<>();
                            //userinfo.put("id",currentUserId);
                            userinfo.put("Name",fullname);
                            userinfo.put("Email",email);
                            userinfo.put("password",password);
                            userinfo.put("phone",phone_no);
                            userinfo.put("gender",Gender);
                            userinfo.put("organisation",organisation);
                            userDatabaseRef.child(fullname).updateChildren(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(lectureregister.this, "Data set successfully", Toast.LENGTH_LONG).show();

                                        } else {
                                            Toast.makeText(lectureregister.this, task.getException().toString(), Toast.LENGTH_LONG).show();
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
/*

    firebaseFirestore=FirebaseFirestore.getInstance();
        ref=firebaseFirestore.collection("lec_register").document();
     registration.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
              final String email=reg_email.getText().toString().trim();
                final String password=reg_password.getText().toString().trim();
                if (reg_fullname.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a username", Toast.LENGTH_SHORT).show();

                } else if (reg_email.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a email id", Toast.LENGTH_SHORT).show();
                }  else if(!reg_email.getText().toString().matches(emailPattern)) {

                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else if (reg_password.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a password", Toast.LENGTH_SHORT).show();
                }
                else if(!(reg_password.getText().toString().length()>=8||reg_password.getText().toString().matches(passwordPattern))){
                    Toast.makeText(lectureregister.this,"Password is invalid",Toast.LENGTH_SHORT).show();
                }else if (reg_phone.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a phone no", Toast.LENGTH_SHORT).show();
                }else if(!reg_phone.getText().toString().matches(MobilePattern)) {

                    Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();

                }else if (reg_gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(lectureregister.this, "Please select gender", Toast.LENGTH_SHORT).show();

                }

                else if (reg_org.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a organisation", Toast.LENGTH_SHORT).show();

                } else if (reg_orgpassword.getText().toString().equals("")) {
                    Toast.makeText(lectureregister.this, "Please type a organisation password", Toast.LENGTH_SHORT).show();

                }  else if (!(reg_orgpassword.getText().toString().equals("School123"))) {
                    Toast.makeText(lectureregister.this,"Wrong Organization Password", Toast.LENGTH_SHORT).show();
                } else {
                    ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Toast.makeText(lectureregister.this, "Sorry,this user exists", Toast.LENGTH_SHORT).show();

                            }   else{
                                Map<String, Object> reg_entry = new HashMap<>();
                                reg_entry.put("Fullname", reg_fullname.getText().toString());
                                reg_entry.put("Email", reg_email.getText().toString());
                                reg_entry.put("Password", reg_password.getText().toString());
                                reg_entry.put("phone_no", reg_phone.getText().toString());
                                reg_entry.put("Gender",gender.toString());

                                reg_entry.put("Organisation", reg_org.getText().toString());

                                reg_entry.put("Org_password", reg_orgpassword.getText().toString());

                                firebaseFirestore.collection("lec_register")
                                        .add(reg_entry)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {

                                                Toast.makeText(lectureregister.this, "Successfully added", Toast.LENGTH_SHORT).show();

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
                    });
           }
         }
     });


*/


        TextView btn=(TextView) findViewById(R.id.sign_in);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(lectureregister.this,lecturelogin.class);
                startActivity(i);
            }
        });
    }

    }