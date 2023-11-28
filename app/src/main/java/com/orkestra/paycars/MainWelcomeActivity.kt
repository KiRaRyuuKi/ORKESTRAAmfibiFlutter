package com.orkestra.paycars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.orkestra.paycars.models.MainSignInActivity

class MainWelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_welcome)

        val goToRegister = findViewById<TextView>(R.id.buttonWelcome)

        goToRegister.setOnClickListener {
            val intent = Intent(this@MainWelcomeActivity, MainSignInActivity::class.java)
            startActivity(intent)
        }
    }
}z