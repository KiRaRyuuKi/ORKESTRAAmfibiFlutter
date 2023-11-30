package com.orkestra.paycars.controllers.view

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class userViewModel(application: Application) : AndroidViewModel(application) {

    data class UserData(
        val userName: String,
        val userEmail: String,
        val userPhone: String,
        val userImageUrl: String
    )

    private val _userData = MutableLiveData<UserData?>()
    val userData: MutableLiveData<UserData?> get() = _userData

    fun setUserData(user: UserData) {
        _userData.value = user

        // Menyimpan data pengguna ke SharedPreferences
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("userName", user.userName)
        editor.putString("userEmail", user.userEmail)
        editor.putString("userPhone", user.userPhone)
        editor.putString("userImageUrl", user.userImageUrl)
        editor.apply()
    }

    fun clearUserData() {
        // Set nilai _userData ke null
        _userData.value = null

        // Hapus data pengguna dari SharedPreferences
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    // Fungsi untuk mendapatkan data pengguna dari SharedPreferences
    fun loadUserDataFromPrefs() {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("userName", "")
        val userEmail = sharedPreferences.getString("userEmail", "")
        val userPhone = sharedPreferences.getString("userPhone", "")
        val userImageUrl = sharedPreferences.getString("userImageUrl", "")

        if (userName != null && userEmail != null && userPhone != null && userImageUrl != null) {
            val userData = UserData(userName, userEmail, userPhone, userImageUrl)
            setUserData(userData)
        }
    }
}
