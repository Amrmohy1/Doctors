package com.example.doctors.authentication

import android.util.Patterns
import androidx.annotation.StringRes
import com.example.doctors.R

object Validator {

    fun isEmailValid(email : String): Boolean{
            return Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()
    }

    fun isValidPassword(password: String): Boolean{
        if (password.isBlank())return false
        return password.length >= 8
    }


    fun getEmailError(email: String): String?{
         return when {
             email.isBlank()->"Email is required"
             !isEmailValid(email)-> "Invalid email address"
             else -> null
         }
    }

    fun getPasswordError(password: String): String?{
        return when {
            password.isBlank()-> "Password is required"
            !isValidPassword(password)-> "Password must be at least 8 characters long"
            else -> null
        }
    }

    fun getConfirmPasswordError(password: String, confirmPassword: String): String?{
        return when {
            confirmPassword.isBlank()-> "Confirm password is required"
            password != confirmPassword-> "Passwords do not match"
            else -> null
        }
    }
}