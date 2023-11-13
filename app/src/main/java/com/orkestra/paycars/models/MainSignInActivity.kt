package com.orkestra.paycars.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orkestra.paycars.R
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainSignInActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_in)

        etUsername = findViewById(R.id.userSignIn)
        etPassword = findViewById(R.id.passwordSignIn)
        btnLogin = findViewById(R.id.buttonSignIn)
        btnRegister = findViewById(R.id.signInRegister)

        btnRegister.setOnClickListener {
            startActivity(Intent(applicationContext, MainSignUpActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (!(username.isEmpty() || password.isEmpty())) {
                val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)

                val stringRequest = StringRequest(
                    Request.Method.GET,
                    "${Db_Contract.urlLogin}?username=$username&password=$password",
                    Response.Listener<String> { response ->
                        if (response == "Selamat Datang") {
                            Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, MainSignInActivity::class.java))
                        } else {
                            Toast.makeText(applicationContext, "Login Gagal", Toast.LENGTH_SHORT).show()
                        }
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    })

                requestQueue.add(stringRequest)
            } else {
                Toast.makeText(applicationContext, "Password Atau Username Salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
    }
