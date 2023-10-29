package com.orkestra.paycars.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orkestra.paycars.MainRegister;
import com.orkestra.paycars.R;

public class MainLogin extends AppCompatActivity {
//    Button btnLogin, btnRegister;
//    EditText inputUser, inputPassword;
//    String strUsername, strPassword;
//    MainViewsLogin loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView goToRegister = findViewById(R.id.goToRegister);

//        // Initialize layout elements
//        inputUser = findViewById(R.id.editTextText12);
//        inputPassword = findViewById(R.id.editTextTextPassword);
//        btnLogin = findViewById(R.id.lanjutLogin);
//
//        loginViewModel = ViewModelProviders.of(this).get(MainViewsLogin.class);
//
//        // Set click listeners
//        btnRegister = findViewById(R.id.goToRegister);
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainLogin.this, MainRegister.class);
//                startActivity(intent);
//            }
//        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                strUsername = inputUser.getText().toString();
//                strPassword = inputPassword.getText().toString();
//
//                loginViewModel.getDataUser(strUsername, strPassword);
//
//                if (strUsername.isEmpty() || strPassword.isEmpty()) {
//                    Toast.makeText(MainLogin.this, "Ups, Form harus diisi semua!", Toast.LENGTH_LONG).show();
//                } else {
//                    loginViewModel.getDataUser(strUsername, strPassword).observe(MainLogin.this, modelDatabases -> {
//                        if (modelDatabases.size() != 0) {
//                            Intent intent = new Intent(MainLogin.this, MainActivity.class);
//                            startActivity(intent);
//                        } else {
//                            Toast.makeText(MainLogin.this, "Ups, Username atau Password Anda salah!", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//            }
//        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View klik) {
                Intent intent = new Intent(MainLogin.this, MainRegister.class);
                startActivity(intent);
            }
        });
    }
}
