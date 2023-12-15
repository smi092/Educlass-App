package com.example.educlass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educlass.activites.LectureMainPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.graphics.Paint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.firebase.firestore.FirebaseFirestore;

public class lecturelogin extends AppCompatActivity {
    //initialize variable
    EditText emailAddress,password;
    CheckBox showPassword;
    TextView rgstr;
    Button loginBtn;
    FirebaseFirestore db;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[<>/+-@#$])(?=\\S+$).{8,}$";
    FirebaseAuth mAuth;
    ProgressDialog loader;
    FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturelogin);

        //assign variable
        emailAddress=findViewById(R.id.emailAddress);
        password=findViewById(R.id.password);
        showPassword=findViewById(R.id.showPassword);
        loginBtn=findViewById(R.id.loginBtn);
        rgstr=findViewById(R.id.register);
        loader=new ProgressDialog(this);

        mAuth= FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=mAuth.getCurrentUser();
                if(user!=null){
                    Intent intent=new Intent(lecturelogin.this, LectureMainPage.class);
                   startActivity(intent);
                   finish();
                }
            }
        };

        db=FirebaseFirestore.getInstance();
TextView forgot=(TextView)findViewById(R.id.forgotPass) ;
forgot.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(lecturelogin.this,Forgotpswrd.class);
        startActivity(i);
    }
});



        TextView registerlec=(TextView)findViewById(R.id.register);
        registerlec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(lecturelogin.this,lectureregister.class);
                startActivity(i);

            }
        });
        //underline textview
        registerlec.setPaintFlags(registerlec.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


        registerlec.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(lecturelogin.this,lectureregister.class);
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

               if (emailAddress.getText().toString().equals("")){
                   Toast.makeText(lecturelogin.this,"Please enter  email id",Toast.LENGTH_SHORT).show();

               }else if (!emailAddress.getText().toString().matches(emailPattern)) {

                   Toast.makeText(getApplicationContext(), "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
               }
               else if(password.getText().toString().equals("")){
                   Toast.makeText(lecturelogin.this,"Please enter password",Toast.LENGTH_SHORT).show();
               }
               else if (!(password.getText().toString().length() >= 8 || password.getText().toString().matches(passwordPattern))) {
                   Toast.makeText(lecturelogin.this, "Password is invalid", Toast.LENGTH_SHORT).show();
               }else{
                   loader.setMessage("Log in progress");
                   loader.setCanceledOnTouchOutside(false);
                   loader.show();
                   mAuth.signInWithEmailAndPassword(email, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Toast.makeText(lecturelogin.this, "Log in successful", Toast.LENGTH_LONG).show();
                               Intent intent = new Intent(lecturelogin.this, LectureMainPage.class);
                               startActivity(intent);
                               finish();
                           } else {
                               Toast.makeText(lecturelogin.this, task.getException().toString(), Toast.LENGTH_LONG).show();

                           }
                           loader.dismiss();
                       }
                   });



               }
               /*else{ db.collection("lec_register")
                       .get()
                       .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                           @Override
                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                               if(task.isSuccessful()){
                                   for (QueryDocumentSnapshot doc:task.getResult()) {
                                       String a= doc.getString("Email");
                                       String b=doc.getString("Password");
                                       String a1=emailAddress.getText().toString().trim();
                                       String b1=password.getText().toString().trim();
                                       if(a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                           Intent i = new Intent(lecturelogin.this, lecture_mainpage.class);
                                           startActivity(i);
                                           Toast.makeText(lecturelogin.this, "Logged In", Toast.LENGTH_SHORT).show();
                                           break;
                                       }
                                       if(a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                           Intent i = new Intent(lecturelogin.this, lecture_mainpage.class);
                                           startActivity(i);
                                           Toast.makeText(lecturelogin.this, "Logged In", Toast.LENGTH_SHORT).show();
                                           break;
                                       }
                                   }
                               }


                           }
                       });


               }*/





           }
       });
    }


}