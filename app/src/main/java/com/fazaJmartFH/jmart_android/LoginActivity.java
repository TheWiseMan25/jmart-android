package com.fazaJmartFH.jmart_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fazaJmartFH.jmart_android.model.Account;
import com.fazaJmartFH.jmart_android.request.LoginRequest;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity
{
    private static final Gson gson = new Gson();
    public static Account loggedAccount = null;

    public static Account getLoggedAccount(){
        return loggedAccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText textEmail = findViewById(R.id.login_email);
        EditText textPassword = findViewById(R.id.login_password);
        Button logInButton = findViewById(R.id.Login);

        logInButton.setOnClickListener(v ->
        {
            Response.Listener<String> listener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    try
                    {
                        JSONObject object = new JSONObject(response);
                        if(object != null)
                        {
                            Toast.makeText(LoginActivity.this, "Log in berhasil!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            loggedAccount = gson.fromJson(object.toString(), Account.class);
                            startActivity(intent);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Log in gagal!", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            LoginRequest loginRequest = new LoginRequest(textEmail.getText().toString(), textPassword.getText().toString(), listener, null);
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(loginRequest);
        });
        TextView register = findViewById(R.id.register);
        register.setOnClickListener(v ->
        {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}