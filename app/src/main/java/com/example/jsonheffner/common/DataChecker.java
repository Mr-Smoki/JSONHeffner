package com.example.jsonheffner.common;

import android.app.AlertDialog;
import android.content.Context;
import android.icu.text.CaseMap;
import android.os.Message;
import android.widget.EditText;

import androidx.appcompat.widget.DialogTitle;

public class DataChecker {
    public static void makeMessage(String message, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Ashipka");
        builder.setMessage(message);
        builder.show();
    }
    public static boolean checkMail(String mail){
        return mail.matches("[a-z0-9]+@[a-z0-9]+.[a-z]{1,3}");
    }
    public static boolean isNoNullField(EditText editText){
        return !editText.getText().toString().trim().equals("");
    }
}
