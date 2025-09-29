package com.example.practice

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewmodel: ViewModel() {
    var username=mutableStateOf("")
        private set

    var password= mutableStateOf("")
        private set

    fun UsernameChange(newvalue:String){
        username.value=newvalue
    }

    fun PasswordChange(newvalue: String){
        password.value=newvalue
    }

    fun onClick(){

    }
}