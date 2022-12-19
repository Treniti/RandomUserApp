package com.treniti.randomuserapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.treniti.randomuserapp.di.AppComponent
import com.treniti.randomuserapp.di.DaggerAppComponent
import com.treniti.randomuserapp.di.module.AppModule

class App() : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }

val Fragment.appComponent: AppComponent
    get() = this.requireContext().appComponent