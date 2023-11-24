package com.orkestra.paycars

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.orkestra.paycars.models.ui.content.MainDetailContentActivity


class MainStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_start)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        Handler().postDelayed({
            startActivity(Intent(this@MainStartActivity, MainDetailContentActivity::class.java))
            finish()
        }, 2500)
    }
}