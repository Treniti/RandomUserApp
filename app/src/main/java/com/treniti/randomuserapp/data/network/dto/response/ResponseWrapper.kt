package com.treniti.randomuserapp.data.network.dto.response

data class ResponseWrapper<out T>(
    val results: List<T>,
    val info: ResponseInfo
)