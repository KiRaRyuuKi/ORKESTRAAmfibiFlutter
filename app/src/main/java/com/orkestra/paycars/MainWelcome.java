package com.orkestra.paycars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginOrRegister = findViewById(R.id.loginOrRegister);

        loginOrRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View klik) {
                Intent intent = new Intent(MainWelcome.this, MainLogin.class);
                startActivity(intent);
            }
        });
    }
}