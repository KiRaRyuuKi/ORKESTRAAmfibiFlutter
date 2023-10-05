package com.orkestra.paycars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainGetCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_code);

        Button lanjutkanKeHome = findViewById(R.id.lanjutkanKeHome);

        lanjutkanKeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View klik) {
                Intent intent = new Intent(MainGetCode.this, MainHome.class);
                startActivity(intent);
            }
        });
    }
}