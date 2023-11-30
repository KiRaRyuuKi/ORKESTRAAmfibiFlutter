package com.orkestra.paycars.controllers.model
data class SignUpRequest(
    val idToken: String,
    val username: String,
    val email: String
)