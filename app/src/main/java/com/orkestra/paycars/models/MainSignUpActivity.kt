package com.orkestra.paycars.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.orkestra.paycars.MainStartActivity
import com.orkestra.paycars.R

class MainSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_up)

        val backToLogin = findViewById<TextView>(R.id.buttonArrowLeft)

        backToLogin.setOnClickListener {
            val intent = Intent(this@MainSignUpActivity, MainStartActivity::class.java)
            startActivity(intent)
        }
    }
}