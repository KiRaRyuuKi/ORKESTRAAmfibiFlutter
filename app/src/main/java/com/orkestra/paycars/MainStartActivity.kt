package com.orkestra.paycars

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.orkestra.paycars.models.MainSignUpActivity
import com.orkestra.paycars.models.ui.menu.MainHomeActivity


class MainStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_in)

        val goToRegister = findViewById<TextView>(R.id.signInRegister)

        goToRegister.setOnClickListener {
            val intent = Intent(this@MainStartActivity, MainSignUpActivity::class.java)
            startActivity(intent)
        }

        val goToHome = findViewById<TextView>(R.id.buttonSignIn)

        goToHome.setOnClickListener {
            val intent = Intent(this@MainStartActivity, MainHomeActivity::class.java)
            startActivity(intent)
        }
    }
}