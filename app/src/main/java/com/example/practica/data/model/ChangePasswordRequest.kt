package com.example.practica.data.model

data class ChangePasswordRequest(
    val email: String,
    val newPassword: String
)
