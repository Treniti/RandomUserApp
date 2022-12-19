package com.treniti.randomuserapp.data.network.service

import com.treniti.randomuserapp.data.network.dto.response.ResponseWrapper
import com.treniti.randomuserapp.data.network.dto.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/")
    suspend fun getUsers(@Query("results") resultSize: Int): Response<ResponseWrapper<UserDTO>>
}