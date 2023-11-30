package com.orkestra.paycars.models

import android.content.Intent
import android.os.AsyncTask
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

        val signInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
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

        // Handler untuk tombol Signup with JSON
        val signUpWithJsonButton = findViewById<Button>(R.id.buttonSignUp)
        signUpWithJsonButton.setOnClickListener {
            signUpWithJson()
        }
    }

    // ...

    private fun signUpWithJson() {
        // Gantilah URL_JSON_SIGNUP dengan URL sumber data JSON signup
        val jsonSignUpUrl = "URL_JSON_SIGNUP"

        // Gunakan AsyncTask untuk mengambil data JSON secara asynchronous
        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void): String {
                var response = ""
                try {
                    val url = URL(jsonSignUpUrl)
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
                handleJsonSignUpResult(result)
            }
        }.execute()
    }

    private fun handleJsonSignUpResult(jsonResult: String) {
        try {
            // Parsing data JSON untuk mendapatkan informasi hasil pendaftaran
            val jsonObject = JSONObject(jsonResult)
            val success = jsonObject.getBoolean("success")

            if (success) {
                // Pendaftaran berhasil, lakukan tindakan lain atau pindah ke halaman lain
                Toast.makeText(this@MainSignUpActivity, "Signup successful", Toast.LENGTH_SHORT).show()
            } else {
                // Pendaftaran gagal, tampilkan pesan kesalahan atau lakukan tindakan lain
                Toast.makeText(this@MainSignUpActivity, "Signup failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
