package com.example.educlass;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;

public class Forgotpswrd extends AppCompatActivity {
    EditText emailForgot;
    Button sbmtButton;
    FirebaseAuth fAuth;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpswrd);

        emailForgot =findViewById(R.id.emailForgot);
        sbmtButton =findViewById(R.id.sbmtButton);

        fAuth=FirebaseAuth.getInstance();

        sbmtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }
    private void validateData(){
        email=emailForgot.getText().toString();
        if(email.isEmpty()){
            emailForgot.setError("Required");
        }else{
            forgotpass();
        }
    }
    private void forgotpass(){
        fAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Forgotpswrd.this, "Check your Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Forgotpswrd.this,lecturelogin.class));
                            finish();
                        }else{
                            Toast.makeText(Forgotpswrd.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}