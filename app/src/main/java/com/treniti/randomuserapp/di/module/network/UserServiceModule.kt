package com.treniti.randomuserapp.di.module.network

import com.treniti.randomuserapp.data.network.service.UserService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UserServiceModule {
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}