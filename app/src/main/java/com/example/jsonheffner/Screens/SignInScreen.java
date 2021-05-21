package com.example.jsonheffner.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jsonheffner.R;
import com.example.jsonheffner.Screens.MainScreen.MainScreen;

import static com.example.jsonheffner.common.DataChecker.checkMail;
import static com.example.jsonheffner.common.DataChecker.makeMessage;

public class SignInScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        CreateUser();
    }
    EditText jobET,nameET;
    Button crebutt;

    private void CreateUser() {
        nameET = findViewById(R.id.MailET);
        jobET = findViewById(R.id.passET);

// Instantiate the RequestQueue.

    }

    public void crebutt_Click(View view) {
//        CreateUser();
        boolean check = checkMail(nameET.getText().toString());
        if (check==true){
            makeMessage("Меф",this);
            Intent intent = new Intent(SignInScreen.this,MainScreen.class);
            startActivity(intent);
        }
        else{
            makeMessage("Loh", this);
        }
    }

    public void regbutt_Click(View view) {
        Intent intent = new Intent(SignInScreen.this, SignUpScreen.class);
        startActivity(intent);

    }
}