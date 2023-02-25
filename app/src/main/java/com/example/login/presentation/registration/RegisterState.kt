package com.example.login.presentation.registration

import androidx.annotation.StringRes

data class RegisterState(
    val successRegister:Boolean=false,
    val displayProgressBar: Boolean=false,
    @StringRes val errorMessages:Int?=null
)
