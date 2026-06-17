package com.example.doctors.doctors

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Specialty(
    val id : Int,
    @StringRes
    val name : Int,
    @DrawableRes
    val image : Int
)
