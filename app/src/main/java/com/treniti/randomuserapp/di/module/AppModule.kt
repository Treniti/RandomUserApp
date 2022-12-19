package com.treniti.randomuserapp.di.module


import com.treniti.randomuserapp.di.module.db.DatabaseModule
import com.treniti.randomuserapp.di.module.network.NetworkModule
import dagger.Module

@Module(includes = [ViewModelModule::class, NetworkModule::class, DatabaseModule::class])
class AppModule