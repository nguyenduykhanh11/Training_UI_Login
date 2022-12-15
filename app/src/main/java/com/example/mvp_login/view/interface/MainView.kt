package com.example.mvp_login.view.`interface`

import com.example.mvp_login.data.model.UserEntity

interface MainView {
    fun LoginSuccessful(list: List<UserEntity>)
    fun LoginError(e :Throwable)
}