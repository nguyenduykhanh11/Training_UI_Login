package com.example.login_ui.di

import com.example.login_ui.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun inject (retrofitModule: RetrofitModule)
    fun inject (activity: MainActivity)
}