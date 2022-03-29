package com.example.cinemaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
    }

    public void logIn(View view) {
        Intent intent = new Intent(RegActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {

        String email = ((TextView)findViewById(R.id.editTextTextEmailAddress2)).getText().toString();
        String passwordFirst = ((TextView)findViewById(R.id.editTextTextPassword2)).getText().toString();
        String passwordSecond = ((TextView)findViewById(R.id.editTextTextPassword3)).getText().toString();
        String firstName = ((TextView)findViewById(R.id.editTextTextPersonName)).getText().toString();
        String lastName = ((TextView)findViewById(R.id.editTextTextPersonName2)).getText().toString();

        if (email.isEmpty() || passwordFirst.isEmpty() || passwordSecond.isEmpty() || firstName.isEmpty() || lastName.isEmpty())
        {
            Toast.makeText(RegActivity.this, "Не все поля заполнены", Toast.LENGTH_LONG).show();
            return;
        }

        if(!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$"))
        {
            Toast.makeText(RegActivity.this, "Некорректный адрес электронной почты", Toast.LENGTH_LONG).show();
            return;
        }

        if(!passwordFirst.equals(passwordSecond))
        {
            Toast.makeText(RegActivity.this, "Введенные пароли не совпадают", Toast.LENGTH_LONG).show();
            return;
        }

        String url = "http://cinema.areas.su/auth/register";

        RequestQueue requestQueue = Volley.newRequestQueue(RegActivity.this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegActivity.this, "Регистрация не удалась", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", passwordFirst);
                params.put("firstName", firstName);
                params.put("lastName", lastName);

                return params;
            }
        };

        requestQueue.add(request);
    }
}