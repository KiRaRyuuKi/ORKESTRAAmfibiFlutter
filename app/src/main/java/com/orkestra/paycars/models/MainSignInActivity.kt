package com.orkestra.paycars.models

import android.content.Intent
<<<<<<< Updated upstream
=======
import android.os.AsyncTask
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
import com.orkestra.paycars.models.ui.menu.MainHomeActivity
=======
import com.orkestra.paycars.models.ui.MainHomeActivity
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
>>>>>>> Stashed changes

class MainSignInActivity : AppCompatActivity() {
    // Deklarasi variabel
    private lateinit var buttonSignInGoogle: Button
    private lateinit var googleSignInClient: GoogleSignInClient
    private var firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var authListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_in)
<<<<<<< Updated upstream
        buttonSignInGoogle = findViewById(R.id.buttonSignInGoogle)

=======

        // Inisialisasi tombol Sign In with Google
        buttonSignInGoogle = findViewById(R.id.buttonSignInGoogle)

        // Konfigurasi opsi Google Sign-In
>>>>>>> Stashed changes
        val signInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, signInOption)

<<<<<<< Updated upstream
        // Initialize authListener here
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
=======
        // Inisialisasi listener untuk status otentikasi Firebase
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // Jika pengguna sudah masuk, pindah ke MainHomeActivity
>>>>>>> Stashed changes
                startActivity(Intent(applicationContext, MainHomeActivity::class.java))
            }
        }

<<<<<<< Updated upstream
=======
        // Set onClickListener untuk tombol Sign In with Google
>>>>>>> Stashed changes
        buttonSignInGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        // Set onClickListener untuk tombol Register
        val goToRegister = findViewById<TextView>(R.id.signInRegister)
        goToRegister.setOnClickListener {
            val intent = Intent(this@MainSignInActivity, MainSignUpActivity::class.java)
            startActivity(intent)
        }

<<<<<<< Updated upstream
//        val goToHome = findViewById<TextView>(R.id.buttonSignIn)
//        goToHome.setOnClickListener {
//            val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        // Getting user credentials with the help of AuthCredential method and also passing user Token Id.
=======
        // Set onClickListener untuk tombol Sign In with JSON
        val signInWithJsonButton = findViewById<Button>(R.id.buttonSignIn)
        signInWithJsonButton.setOnClickListener {
            loginWithJson()
        }
    }

    // Fungsi untuk melakukan otentikasi Firebase dengan Google
    private fun firebaseAuthWithGoogle(idToken: String) {
>>>>>>> Stashed changes
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        // Trying to sign in user using signInWithCredential and passing above credentials of user.
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
<<<<<<< Updated upstream
                    Log.d(TAG, "signInWithCredential:success")

                    // Sign in success, navigate user to Profile Activity
                    val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
=======
                    // Jika otentikasi berhasil, pindah ke MainHomeActivity
                    val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // Jika otentikasi gagal, tampilkan pesan kesalahan
>>>>>>> Stashed changes
                    Toast.makeText(this@MainSignInActivity, "User authentication failed", Toast.LENGTH_SHORT).show()
                }
            })
    }

<<<<<<< Updated upstream
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
=======
    // Override fungsi onActivityResult untuk penanganan hasil Sign In with Google
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let { firebaseAuthWithGoogle(it) }
>>>>>>> Stashed changes
                val userName = account?.displayName
                val userEmail = account?.email
                var userPhoto = account?.photoUrl.toString()
                userPhoto = "$userPhoto?type=large"
<<<<<<< Updated upstream
                // Create SharedPreferences to store user data when the user signs in successfully
=======
                // Simpan informasi pengguna ke SharedPreferences setelah Sign In berhasil
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
    // Fungsi untuk menangani Sign In with JSON
    private fun loginWithJson() {
        // Gantilah URL_JSON_LOGIN dengan URL sumber data JSON login
        val jsonLoginUrl = "URL_JSON_LOGIN"

        // Gunakan AsyncTask untuk mengambil data JSON secara asynchronous
        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void): String {
                var response = ""
                try {
                    val url = URL(jsonLoginUrl)
                    val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    try {
                        val reader = BufferedReader(InputStreamReader(urlConnection.inputStream, "UTF-8"))
                        var line: String?
                        val stringBuilder = StringBuilder()
                        while (reader.readLine().also { line = it } != null) {
                            stringBuilder.append(line).append("\n")
                        }
                        response = stringBuilder.toString()
                    } finally {
                        urlConnection.disconnect()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return response
            }

            override fun onPostExecute(result: String) {
                handleJsonLoginResult(result)
            }
        }.execute()
    }

    // Fungsi untuk menangani hasil Sign In with JSON
    private fun handleJsonLoginResult(jsonResult: String) {
        try {
            // Parsing data JSON untuk mendapatkan informasi pengguna
            val jsonObject = JSONObject(jsonResult)
            val success = jsonObject.getBoolean("success")

            if (success) {
                // Login berhasil, pindah ke activity target atau lakukan tindakan lainnya
                val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
                startActivity(intent)
            } else {
                // Login gagal, tampilkan pesan kesalahan atau lakukan tindakan lainnya
                Toast.makeText(
                    this@MainSignInActivity,
                    "Login failed. Please check your credentials.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    // Override fungsi onStart dan onStop untuk menambah dan menghapus listener otentikasi Firebase
>>>>>>> Stashed changes
    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(authListener)
    }

<<<<<<< Updated upstream
=======
    // Companion object untuk menyimpan konstanta-konstanta
>>>>>>> Stashed changes
    companion object {
        private const val RC_SIGN_IN = 1000
        private const val TAG = "MainSignInActivity"
        private const val USER_TYPE_DEFAULT_VALUE = "default"
    }
}
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
