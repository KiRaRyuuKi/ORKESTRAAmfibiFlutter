package com.orkestra.paycars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button lanjutLogin = findViewById(R.id.lanjutLogin);

        lanjutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View klik) {
                Intent intent = new Intent(MainRegister.this, MainGetCode.class);
                startActivity(intent);
            }
        });
    }
}