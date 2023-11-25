package com.orkestra.paycars.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.orkestra.paycars.R
import com.orkestra.paycars.models.ui.MainHomeActivity

class MainSignInActivity : AppCompatActivity() {
    private lateinit var buttonSignInGoogle: Button
    private lateinit var googleSignInClient: GoogleSignInClient

    private var firebaseAuth = FirebaseAuth.getInstance()

    companion object {
        private const val RC_SIGN_IN = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_in)

        buttonSignInGoogle = findViewById(R.id.buttonSignInGoogle)

        val signInOption = GoogleSignInOptions. Builder (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, signInOption)

        buttonSignInGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        val goToRegister = findViewById<TextView>(R.id.signInRegister)

        goToRegister.setOnClickListener {
            val intent = Intent(this, MainSignUpActivity::class.java)
            startActivity(intent)
        }

        val goToHome = findViewById<TextView>(R.id.buttonSignIn)

        goToHome.setOnClickListener {
            val intent = Intent(this, MainHomeActivity::class.java)
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
                Toast.makeText(applicationContext, apiExcept.localizedMessage, LENGTH_SHORT).show()
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
                Toast.makeText(this, error.localizedMessage, LENGTH_SHORT).show()
            }
            .addOnCompleteListener {
//                progressDialog.dismiss()
            }
    }
}