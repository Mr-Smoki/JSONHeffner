package com.example.jsonheffner.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsonheffner.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.jsonheffner.common.AuthChecker.AuthConfirmed;
import static com.example.jsonheffner.common.DataChecker.checkMail;
import static com.example.jsonheffner.common.DataChecker.isNoNullField;
import static com.example.jsonheffner.common.DataChecker.makeMessage;
import static com.example.jsonheffner.common.URLs.getSignUp;

public class SignUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

    }

    public void backToMain_Click(View view) {
        Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
        startActivity(intent);
    }

    public void signButton_Click(View view) {
        EditText surnameET = findViewById(R.id.surnameET);
        EditText nameET = findViewById(R.id.nameET);
        EditText passET = findViewById(R.id.passET);
        EditText passRepeatET = findViewById(R.id.passRepeatET);
        EditText emailET = findViewById(R.id.MailET);
        if(isNoNullField(surnameET)&&isNoNullField(nameET)&&isNoNullField(passET)&&isNoNullField(passRepeatET)&&isNoNullField(emailET)){
            if(passET.getText().toString()==passRepeatET.getText().toString()){
                final String emailValue=emailET.getText().toString();
                final String passValue=passET.getText().toString();
                String nameValue=nameET.getText().toString();
                String surnameValue=surnameET.getText().toString();
                if(checkMail(emailValue)){
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("email",emailValue);
                        jsonObject.put("password",passValue);
                        jsonObject.put("firstName",nameValue);
                        jsonObject.put("lastName",surnameValue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestQueue requestQueue = Volley.newRequestQueue(SignUpScreen.this);
                    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, getSignUp(), jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            AuthConfirmed(SignUpScreen.this,emailValue,passValue);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            makeMessage("Проблема с регистрацией ошибка", SignUpScreen.this);
                        }
                    });
                    requestQueue.add(stringRequest);
                }
                else
                {
                    makeMessage("некоректный адрес электронной почты", SignUpScreen.this);
                }

            }
            else {
                makeMessage("пароли не совпадают", SignUpScreen.this);
            }
        }
        else{
            makeMessage("пустые поля", SignUpScreen.this);
        }
    }
}