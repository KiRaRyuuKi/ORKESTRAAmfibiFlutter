import android.content.Intent
import android.os.AsyncTask
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
import com.orkestra.paycars.models.MainSignUpActivity
import com.orkestra.paycars.models.fragment.ApiService
import com.orkestra.paycars.models.fragment.DataPayload
import com.orkestra.paycars.models.ui.MainHomeActivity
import com.orkestra.paycars.models.ui.menu.MainHomeActivity
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainSignInActivity : AppCompatActivity() {
    private lateinit var buttonSignInGoogle: Button
    private lateinit var googleSignInClient: GoogleSignInClient
    private var firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var authListener: FirebaseAuth.AuthStateListener

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sign_in)

        buttonSignInGoogle = findViewById(R.id.buttonSignInGoogle)
        val signInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, signInOption)

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

        val signInWithJsonButton = findViewById<Button>(R.id.buttonSignIn)
        signInWithJsonButton.setOnClickListener {
            loginWithJson()
        }

        // Inisialisasi Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Tempatkan kode Retrofit di sini atau dalam metode lain yang sesuai
        sendDataToApi()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let { firebaseAuthWithGoogle(it) }
                val userName = account?.displayName
                val userEmail = account?.email
                var userPhoto = account?.photoUrl.toString()
                userPhoto = "$userPhoto?type=large"
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

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainSignInActivity, "User authentication failed", Toast.LENGTH_SHORT).show()
                }
            })
    }

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

    private fun handleJsonLoginResult(jsonResult: String) {
        try {
            val jsonObject = JSONObject(jsonResult)
            val success = jsonObject.getBoolean("success")

            if (success) {
                val intent = Intent(this@MainSignInActivity, MainHomeActivity::class.java)
                startActivity(intent)
            } else {
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

    private fun sendDataToApi() {
        // Periksa apakah pengguna telah masuk
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Pengguna masuk, Anda dapat mengakses informasi pengguna, seperti email atau UID
            val userEmail = currentUser.email

            // Tempatkan kode Retrofit di sini atau dalam metode lain yang sesuai
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.example.com/") // Ganti dengan base URL API Anda
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            // Ganti dengan informasi yang ingin Anda kirim ke API
            val dataPayload = DataPayload(userEmail ?: "", /*data lainnya*/)

            val call = apiService.postDataToApi(dataPayload)

            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        // Berhasil mengirim data, Anda dapat menanggapi di sini
                    } else {
                        // Tanggapan tidak sukses
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    // Gagal mengirim permintaan
                    Toast.makeText(
                        this@MainSignInActivity,
                        "Gagal mengirim data ke server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } else {
            // Pengguna belum masuk, lakukan sesuatu, misalnya, tampilkan pesan atau alihkan ke halaman masuk
            // ...
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
    }
}
