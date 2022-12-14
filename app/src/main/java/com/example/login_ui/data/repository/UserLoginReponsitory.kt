package com.example.login_ui.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.login_ui.data.api.RetrofitInstance
import com.example.login_ui.data.model.UserLoginEntity
import com.example.login_ui.data.api.UserApi
import com.example.login_ui.utils.Constant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserLoginReponsitory() {
    private var userData = MutableLiveData<List<UserLoginEntity>>()

    @SuppressLint("CheckResult")
    fun getUserData(email: Int, password: Int) :MutableLiveData<List<UserLoginEntity>>{
        RetrofitInstance.api.getDataLoginUser()
            .flatMap {item -> Observable.fromIterable(item)}
            .filter { item -> return@filter (item.id == email && item.userId == password)}
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    userData.postValue(list)
                },
                { throwable ->
                    Log.d(Constant.TAG, throwable.toString())
                }
            )
        return userData
    }
}