package com.example.login.presentation.registration

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel:ViewModel(){
    val state:MutableState<RegisterState> = mutableStateOf((RegisterState()))
    fun register(
        name:String ="",
        email:String="",
        phoneNumber:String="",
        password:String="",
        confirmPassword:String=""
    ){
        val errorMessages=
        if(name.isBlank()||email.isBlank()||phoneNumber.isBlank()||password.isBlank()||confirmPassword.isBlank()){
            R.string.error_input_empty
        }else if(!Patterns.PHONE.matcher(phoneNumber).matches()){
            R.string.error_not_e_valid_phone_number
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            R.string.error_not_e_valid_email
        }else if(password!=confirmPassword){
            R.string.error_incorrectly_repeated_password
        }else null
        errorMessages?.let{
            state.value=state.value.copy(errorMessages = errorMessages)
            return
        }
        viewModelScope.launch {
            state.value=state.value.copy(displayProgressBar = true)
            delay(3000)
            state.value=state.value.copy(displayProgressBar = false)
        }
    }
    fun hideErrorDialog(){
        state.value=state.value.copy(errorMessages = null)
    }
}