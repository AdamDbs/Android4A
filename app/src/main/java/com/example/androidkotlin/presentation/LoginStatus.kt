package com.example.androidkotlin.presentation

sealed class LoginStatus

data class LoginSuccess(val email: String): LoginStatus()

object LoginError : LoginStatus()