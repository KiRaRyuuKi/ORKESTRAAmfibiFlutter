package com.orkestra.paycars.models.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.orkestra.paycars.R
class TransactionFragment : Fragment() {

    companion object {
        const val TAG = "GoogleSignIn"

        fun newInstance(): TransactionFragment {
            return TransactionFragment()
        }
    }

    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var userImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_transaction, container, false)

        tvUserName = rootView.findViewById(R.id.nameProfileTransaction)
        tvUserEmail = rootView.findViewById(R.id.emailProfileTransaction)
        userImageView = rootView.findViewById(R.id.imageProfileTransaction)

        val preferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = preferences.getString("username", "")
        val userEmail = preferences.getString("useremail", "")
        val userImageUrl = preferences.getString("userPhoto", "")

        tvUserName.text = userName

        // Check if userEmail is not null before setting the text
        if (!userEmail.isNullOrEmpty()) {
            tvUserEmail.text = userEmail
        }

        Glide.with(this).load(userImageUrl).into(userImageView)

        val goToMessage = rootView.findViewById<TextView>(R.id.buttonMassageTransaction)

        goToMessage.setOnClickListener {
            // Add your logic here for the button click
        }

        return rootView
    }
}

