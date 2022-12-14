package com.example.login_ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.login_ui.data.api.RetrofitInstance
import com.example.login_ui.data.api.UserApi
import com.example.login_ui.data.model.UserLoginEntity
import com.example.login_ui.data.repository.UserLoginReponsitory

class UserLoginViewModel(): ViewModel() {
    private val reponsitory: UserLoginReponsitory
    init {
        reponsitory = UserLoginReponsitory()
    }
    fun getUserData(email: Int, Password: Int): LiveData<List<UserLoginEntity>>{
        return reponsitory.getUserData(email,Password)
    }
}