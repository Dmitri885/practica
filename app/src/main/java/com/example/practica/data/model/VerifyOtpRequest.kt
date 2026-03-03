package com.example.practica.data.model

data class VerifyOtpRequest(
    val type: String = "signup",
    val email: String,
    val token: String
)
