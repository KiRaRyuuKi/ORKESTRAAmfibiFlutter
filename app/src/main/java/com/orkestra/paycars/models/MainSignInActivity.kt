package com.orkestra.paycars.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.orkestra.paycars.R
import com.orkestra.paycars.models.ui.menu.MainHomeActivity

class MainSignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_in)

        val goToRegister = findViewById<TextView>(R.id.signInRegister)

        goToRegister.setOnClickListener {
            val intent = Intent(this@MainSignInActivity, MainSignUpActivity::class.java)
            startActivity(intent)
        }

        val goToHome = findViewById<TextView>(R.id.buttonSignIn)

        goToHome.setOnClickListener {
            val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
            startActivity(intent)
        }
    }
}