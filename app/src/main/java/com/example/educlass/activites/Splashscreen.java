package com.example.educlass.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;

import com.example.educlass.R;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
       /* ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("0f9d58"));
        actionBar.setBackgroundDrawable(colorDrawable);

*/
        int secondsDelayed=1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splashscreen.this, Loginmain.class));
                finish();
            }
        },secondsDelayed*3000);
    }
}