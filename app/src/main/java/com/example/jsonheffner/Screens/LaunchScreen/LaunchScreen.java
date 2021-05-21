package com.example.jsonheffner.Screens.LaunchScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.jsonheffner.R;
import com.example.jsonheffner.Screens.SignInScreen;

public class LaunchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchScreen.this, SignInScreen.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}