package com.fazaJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fazaJmartFH.jmart_android.model.Account;
import com.fazaJmartFH.jmart_android.request.RegisterRequest;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity
{
    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       final EditText name = findViewById(R.id.name_register);
       final EditText email = findViewById(R.id.email_register);
       final EditText password = findViewById(R.id.password_register);
       final Button button = findViewById(R.id.Register);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Response.Listener<String> listener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject!=null)
                            {
                                Toast.makeText(RegisterActivity.this, "Registration is successful!", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                loggedAccount = gson.fromJson(jsonObject.toString(),Account.class);
                                startActivity(intent);
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Login error", Toast.LENGTH_SHORT);
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(name.getText().toString(), email.getText().toString(), password.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });
    }
}