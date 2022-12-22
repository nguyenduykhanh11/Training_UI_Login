package com.example.login_ui.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.login_ui.data.api.UserApi
import com.example.login_ui.data.model.UserLoginEntity
import com.example.login_ui.di.RetrofitModule
import com.example.login_ui.utils.Constant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

class UserLoginReponsitory @Inject constructor(private val userApi: UserApi) {

    private var userData = MutableLiveData<List<UserLoginEntity>>()
    @SuppressLint("CheckResult")
    fun getUserData(email: Int, password: Int) :MutableLiveData<List<UserLoginEntity>>{
        userApi.getDataLoginUser()
            .flatMap {item -> Observable.fromIterable(item)}
            .filter { item -> (item.id == email && item.userId == password)}
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { item ->
                    userData.postValue(item)
                },
                { throwable ->
                    Log.d(Constant.TAG, throwable.toString())
                }
            )
        Log.d("this", userApi.toString()) // kiểm tra @Singleton có tạo bản sao không
        return userData
    }
}