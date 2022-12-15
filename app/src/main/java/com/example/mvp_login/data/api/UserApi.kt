package com.example.mvp_login.data.api

import com.example.mvp_login.data.model.UserEntity
import com.example.mvp_login.utils.Constant
import io.reactivex.Observable
import retrofit2.http.GET


interface UserApi {
    @GET(Constant.END_POST)
    fun getDataLoginUser(): Observable<List<UserEntity>>
}