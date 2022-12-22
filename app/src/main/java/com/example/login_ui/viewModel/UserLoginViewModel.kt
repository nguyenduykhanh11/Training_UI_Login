package com.example.login_ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.login_ui.data.model.UserLoginEntity
import com.example.login_ui.data.repository.UserLoginReponsitory
import javax.inject.Inject

class UserLoginViewModel @Inject constructor(private val reponsitory: UserLoginReponsitory): ViewModel() {
    fun getUserData(email: Int, Password: Int): LiveData<List<UserLoginEntity>>{
        Log.d("this",reponsitory.toString())
        return reponsitory.getUserData(email,Password)

    }
}