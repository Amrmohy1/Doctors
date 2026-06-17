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
             email.isBlank()->"البريد الالكتروني مطلوب"
             !isEmailValid(email)-> "البريد الإلكتروني غير صالح"
             else -> null
         }
    }

    fun getPasswordError(password: String): String?{
        return when {
            password.isBlank()-> "كلمة المرور مطلوب"
            !isValidPassword(password)-> "يجب أن تكون كلمة المرور 8 أحرف على الأقل"
            else -> null
        }
    }

    fun getConfirmPasswordError(password: String, confirmPassword: String): String?{
        return when {
            confirmPassword.isBlank()-> "اكيد كلمة المرور مطلوب"
            password != confirmPassword-> "كلمات المرور غير متطابقة"
            else -> null
        }
    }
}