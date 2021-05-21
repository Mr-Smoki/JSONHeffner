package com.example.jsonheffner.common;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsonheffner.Screens.MainScreen.MainScreen;
import com.example.jsonheffner.common.Entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.jsonheffner.common.DataChecker.makeMessage;
import static com.example.jsonheffner.common.URLs.getSignIn;

public class AuthChecker {
    public static void AuthConfirmed(final Activity activity, String login, String pass){
        RequestQueue queue = Volley.newRequestQueue(activity);

        final JSONObject user = new JSONObject();
        try{
            user.put("email",login);
            user.put("password",pass);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getSignIn(), user, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    User.getCurrentUser().setToken(response.getLong("token"));
                    Intent intent = new Intent(activity, MainScreen.class);
                    activity.startActivity(intent);
                    activity.finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                makeMessage(error.getMessage(),activity);
            }
        });

// Request a string response from the provided URL.


// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }
}
