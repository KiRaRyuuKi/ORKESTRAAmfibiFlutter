package com.orkestra.paycars.models

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.orkestra.paycars.R
import com.orkestra.paycars.models.ui.menu.MainHomeActivity

class MainSignInActivity : AppCompatActivity() {
    private lateinit var buttonSignInGoogle: Button
    private lateinit var googleSignInClient: GoogleSignInClient
    private var firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var authListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_in)
        buttonSignInGoogle = findViewById(R.id.buttonSignInGoogle)

        val signInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, signInOption)

        // Initialize authListener here
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                startActivity(Intent(applicationContext, MainHomeActivity::class.java))
            }
        }

        buttonSignInGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        val goToRegister = findViewById<TextView>(R.id.signInRegister)
        goToRegister.setOnClickListener {
            val intent = Intent(this@MainSignInActivity, MainSignUpActivity::class.java)
            startActivity(intent)
        }

//        val goToHome = findViewById<TextView>(R.id.buttonSignIn)
//        goToHome.setOnClickListener {
//            val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        // Getting user credentials with the help of AuthCredential method and also passing user Token Id.
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        // Trying to sign in user using signInWithCredential and passing above credentials of user.
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")

                    // Sign in success, navigate user to Profile Activity
                    val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@MainSignInActivity, "User authentication failed", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                // Authenticating user with Firebase using received token id
                account?.idToken?.let { firebaseAuthWithGoogle(it) }
                // Assigning user information to variables
                val userName = account?.displayName
                val userEmail = account?.email
                var userPhoto = account?.photoUrl.toString()
                userPhoto = "$userPhoto?type=large"
                // Create SharedPreferences to store user data when the user signs in successfully
                val editor = applicationContext.getSharedPreferences("MyPrefs", MODE_PRIVATE).edit()
                editor.putString("username", userName)
                editor.putString("useremail", userEmail)
                editor.putString("userPhoto", userPhoto)
                editor.apply()
                Log.i(TAG, "onActivityResult: Success")
            } catch (e: ApiException) {
                Log.e(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(authListener)
    }

    companion object {
        private const val RC_SIGN_IN = 1000
        private const val TAG = "MainSignInActivity"
        private const val USER_TYPE_DEFAULT_VALUE = "default"
    }
}

