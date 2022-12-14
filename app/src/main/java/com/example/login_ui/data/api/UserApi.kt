package com.example.login_ui.data.api

import com.example.login_ui.data.model.UserLoginEntity
import com.example.login_ui.utils.Constant
import io.reactivex.Observable
import retrofit2.http.GET

interface UserApi {
    @GET(Constant.END_POST)
    fun getDataLoginUser(): Observable<List<UserLoginEntity>>
}