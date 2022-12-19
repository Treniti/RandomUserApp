package com.treniti.randomuserapp.data.network.dto

import com.google.gson.annotations.SerializedName
import com.treniti.randomuserapp.data.internal.UserDateOfBirthday
import com.treniti.randomuserapp.data.internal.UserPicture
import com.treniti.randomuserapp.data.internal.Username

data class UserDTO(
    val gender: String? = null,
    val name: Username? = null,
    val city: String? = null,
    val country: String? = null,
    val email: String? = null,
    @SerializedName("dob")
    val dateOfBirthday: UserDateOfBirthday? = null,
    val phone: String? = null,
    val picture: UserPicture? = null
)
