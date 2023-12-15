package com.example.educlass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class studentlogin extends AppCompatActivity {
    //initialize variable
    EditText emailAddress,password;
    CheckBox showPassword;
    Button loginBtn;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[<>/+-@#$])(?=\\S+$).{8,}$";

    FirebaseAuth mAuth;
    ProgressDialog loader;
    FirebaseAuth.AuthStateListener authStateListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
        //assign variable
        emailAddress=findViewById(R.id.emailAd);
        password=findViewById(R.id.stdpass);
        showPassword=findViewById(R.id.showPassword);
        loginBtn=findViewById(R.id.loginBt);

       // db=FirebaseFirestore.getInstance();

        loader=new ProgressDialog(this);

        mAuth= FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=mAuth.getCurrentUser();
                if(user!=null){
                    Intent intent=new Intent(studentlogin.this,student_mainpage.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        TextView forgot=(TextView)findViewById(R.id.forgotPass) ;
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(studentlogin.this,Forgotpswrd.class);
                startActivity(i);
            }
        });




        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=emailAddress.getText().toString().trim();
                final String passwords=password.getText().toString().trim();

                if (emailAddress.getText().toString().equals("")) {
                    Toast.makeText(studentlogin.this, "Please enter  email id", Toast.LENGTH_SHORT).show();

                } else if (!emailAddress.getText().toString().matches(emailPattern)) {

                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().equals("")) {
                    Toast.makeText(studentlogin.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else if (!(password.getText().toString().length() >= 8 || password.getText().toString().matches(passwordPattern))) {
                    Toast.makeText(studentlogin.this, "Password is invalid", Toast.LENGTH_SHORT).show();
                }else{
                    loader.setMessage("Log in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    mAuth.signInWithEmailAndPassword(email,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(studentlogin.this, "Log in successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(studentlogin.this, student_mainpage.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(studentlogin.this, task.getException().toString(), Toast.LENGTH_LONG).show();

                            }
                            loader.dismiss();
                        }
                    });



                }
/*
                db.collection("std_register")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot doc : task.getResult()) {
                                        String a = doc.getString("stdmail");
                                        String b = doc.getString("password");
                                        String a1 = emailAddress.getText().toString().trim();
                                        String b1 = password.getText().toString().trim();
                                        if (a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                            Intent i = new Intent(studentlogin.this, student_mainpage.class);
                                            startActivity(i);
                                            Toast.makeText(studentlogin.this, "Logged In", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        if (a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                            Intent i = new Intent(studentlogin.this, student_mainpage.class);
                                            startActivity(i);
                                            Toast.makeText(studentlogin.this, "Logged In", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }
                                }


                            }
                        });*/
            }
        });


            }
}