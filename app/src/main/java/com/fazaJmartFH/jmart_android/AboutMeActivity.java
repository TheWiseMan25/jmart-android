package com.fazaJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        TextView name = (TextView) findViewById(R.id.nama_aboutme);
        name.setText("" + LoginActivity.getLoggedAccount().name);

        TextView email = (TextView) findViewById(R.id.email_aboutme);
        email.setText("" + LoginActivity.getLoggedAccount().email);

        TextView balance = (TextView) findViewById(R.id.balance_aboutme);
        balance.setText("" + LoginActivity.getLoggedAccount().balance);

        Button registerStore  = findViewById(R.id.button_register_aboutme);
        ConstraintLayout formRegisterStore = findViewById(R.id.constraintLayoutRegisterStore);
        registerStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerStore.setVisibility(view.INVISIBLE);
                formRegisterStore.setVisibility(view.VISIBLE);
            }
        });

    }
}