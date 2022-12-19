package com.treniti.randomuserapp.data.network.repository

import com.treniti.randomuserapp.data.network.service.UserService
import com.treniti.randomuserapp.constants.Constants
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: UserService) {

    suspend fun getUsers() = service.getUsers(Constants.API_RESULT_SIZE)
}