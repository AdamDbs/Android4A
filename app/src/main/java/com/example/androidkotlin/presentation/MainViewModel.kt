package com.example.androidkotlin.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidkotlin.domain.entity.User
import com.example.androidkotlin.domain.usecase.CreateUserUseCase
import com.example.androidkotlin.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel(){

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()


    fun onClickedLogin(emailUser: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser)
        //    val user: User = getUserUseCase.invoke("test")
            val loginStatus: LoginStatus = if(user != null){
                LoginSuccess(user.email)
            }else {
                LoginError
            }
            withContext(Dispatchers.Main) {
                loginLiveData.value = loginStatus
            }
        }
    }
}