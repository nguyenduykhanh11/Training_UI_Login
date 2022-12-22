package com.example.login_ui

import android.app.Application
import com.example.login_ui.di.DaggerAppComponent

class MyApplication: Application() {
    val appComponent = DaggerAppComponent.create()
}
