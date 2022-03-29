package com.example.cinemaapi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logIn(View view) {
        String login = ((TextView)findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String password = ((TextView)findViewById(R.id.editTextTextPassword)).getText().toString();

        JSONObject json = new JSONObject();
        try {
            json.put("email", login);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://cinema.areas.su/auth/login";

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                response -> startActivity(new Intent(LoginActivity.this, MainActivity.class)),
                error -> Toast.makeText(this, "Неверные данные авторизации", Toast.LENGTH_LONG).show());
        requestQueue.add(request);
    }

    public void signIn(View view) {
        Intent intent = new Intent(LoginActivity.this, RegActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}
