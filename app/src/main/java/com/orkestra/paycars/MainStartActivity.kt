package com.orkestra.paycars

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.orkestra.paycars.models.MainSignUpActivity


class MainStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_in)

        val goToRegister = findViewById<TextView>(R.id.signInRegister)

        goToRegister.setOnClickListener {
            val intent = Intent(this@MainStartActivity, MainSignUpActivity::class.java)
            startActivity(intent)
        }
    }
}