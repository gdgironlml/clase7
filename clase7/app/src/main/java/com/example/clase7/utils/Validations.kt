package com.example.clase7.utils

import android.util.Patterns
import android.content.Context

import com.example.clase7.R

fun validateEmail (context: Context, value: String): Pair<Boolean, String> {
        return when {
            value.isEmpty() -> Pair(false, context.getString(R.string.email_required))
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> Pair(false, context.getString(R.string.invalid_email))
            else -> Pair(true, "")
        }
    }

fun validatePassword(context: Context,value: String): Pair<Boolean, String> {
    return when {
        value.isEmpty() -> Pair(false, context.getString(R.string.password_required))
        value.length < 6 -> Pair(false, context.getString(R.string.invalid_password))
        else -> Pair(true, "")
    }
}