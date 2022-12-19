package com.treniti.randomuserapp.data.network.dto.response

data class ResponseInfo(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)