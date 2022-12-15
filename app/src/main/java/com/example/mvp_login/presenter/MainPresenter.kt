package com.example.mvp_login.presenter

import android.annotation.SuppressLint
import com.example.mvp_login.data.api.RetrofitInstance
import com.example.mvp_login.view.`interface`.MainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val mainView: MainView) {
    @SuppressLint("CheckResult")
    fun login(email: Int, password: Int){
        RetrofitInstance.api.getDataLoginUser()
            .flatMap {item -> Observable.fromIterable(item)}
            .filter { item -> return@filter (item.id == email && item.userId == password)}
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    mainView.LoginSuccessful(list)

                },
                { throwable ->
                    mainView.LoginError(throwable)
                }
            )
    }
}