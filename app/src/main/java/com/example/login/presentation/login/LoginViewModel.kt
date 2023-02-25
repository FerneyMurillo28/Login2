package com.example.login.presentation.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val state:MutableState<LoginState> = mutableStateOf(LoginState())
    fun login(email:String, password:String){
        val errorMessages=
        if (email.isBlank()||password.isBlank()){
            R.string.error_input_empty
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            R.string.error_not_e_valid_email
        }else if(email!="ferney@gmail.com" || password!="1234"){
            R.string.error_invalid_credentials
        }else null

        errorMessages?.let{
            state.value=state.value.copy(errorMessages=it)
            return
        }
        viewModelScope.launch {
            state.value=state.value.copy(displayProgressBar = true)
            delay(3000)
            state.value=state.value.copy(email=email, pass = password)
            state.value=state.value.copy(displayProgressBar = true)
            state.value=state.value.copy(successLogin = true)
        }
    }//fin fun login
    fun hideErrorDialog(){
        state.value=state.value.copy(errorMessages = null)
    }
}