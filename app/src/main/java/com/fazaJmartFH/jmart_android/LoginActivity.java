package com.fazaJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fazaJmartFH.jmart_android.request.LoginRequest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import com.fazaJmartFH.jmart_android.model.Account;

public class LoginActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;

    public static Account getLoggedAccount()
    {
        return loggedAccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       final EditText loginEmail = findViewById(R.id.loginEmail);
       final EditText loginPassword = findViewById(R.id.loginPassword);
       final Button login = findViewById(R.id.Login);
       final TextView register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick (View v)
            {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject!=null)
                            {
                                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                loggedAccount = gson.fromJson(jsonObject.toString(),Account.class);
                                startActivity(intent);
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(loginEmail.getText().toString(), loginPassword.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });
    }
}