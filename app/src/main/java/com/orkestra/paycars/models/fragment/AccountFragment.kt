package com.orkestra.paycars.models.fragment

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.orkestra.paycars.R
import com.orkestra.paycars.models.MainSignInActivity

class AccountFragment : Fragment() {

    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var userImageView: ImageView
    private lateinit var btnSignOut: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_account, container, false)

        tvUserName = rootView.findViewById(R.id.nameUserAccount)
        tvUserEmail = rootView.findViewById(R.id.usernameUserAccount)
        userImageView = rootView.findViewById(R.id.imageUserAccount)
        btnSignOut = rootView.findViewById(R.id.button2)

        val preferences = requireActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val userName = preferences.getString("username", "")
        val userEmail = preferences.getString("useremail", "")
        val userImageUrl = preferences.getString("userPhoto", "")

        tvUserName.text = userName
        tvUserEmail.text = userEmail
        Glide.with(this).load(userImageUrl).into(userImageView)

        btnSignOut.setOnClickListener {
            val message = "Apakah Anda Yakin Ingin Log Out?"
            showCustomDialogBox(message)
        }

        return rootView
    }

    private fun showCustomDialogBox(message: String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage: TextView = dialog.findViewById(R.id.tvMessage)
        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnNo)

        tvMessage.text = message
        btnYes.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            // Call the cleanup method
            onDestroyCleanup()
            goToMainActivity()
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun goToMainActivity() {
        val intent = Intent(requireActivity(), MainSignInActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun onDestroyCleanup() {
        // Add cleanup tasks here, for example, clearing SharedPreferences
        val editor = requireActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }

    override fun onDestroy() {
        // Cleanup when the fragment is destroyed
        onDestroyCleanup()
        super.onDestroy()
    }
}
