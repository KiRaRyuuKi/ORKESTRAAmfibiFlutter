package com.orkestra.paycars.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.orkestra.paycars.MainStartActivity
import com.orkestra.paycars.R
import com.orkestra.paycars.models.ui.menu.MainHomeActivity

class MainSignUpActivity : AppCompatActivity() {
    lateinit var buttonSignUpGoogle: Button
    lateinit var googleSignUpClient: GoogleSignInClient

    var firebaseAuth = FirebaseAuth.getInstance()

    companion object {
        private const val RC_SIGN_IN = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_up)

        buttonSignUpGoogle = findViewById(R.id.buttonSignUpGoogle)

        val signInOption = GoogleSignInOptions. Builder (GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignUpClient = GoogleSignIn.getClient(this, signInOption)

        buttonSignUpGoogle.setOnClickListener {
            val signInIntent = googleSignUpClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        val backToLogin = findViewById<TextView>(R.id.buttonArrowLeft)

        backToLogin.setOnClickListener {
            val intent = Intent(this@MainSignUpActivity, MainStartActivity::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (apiExcept: ApiException) {
                apiExcept.printStackTrace()
                Toast.makeText(applicationContext, apiExcept.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle (idToken: String) {
//        progressDialog.show()
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                startActivity(Intent(this, MainHomeActivity::class.java))
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener {
//                progressDialog.dismiss()
            }
    }
}