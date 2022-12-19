package com.treniti.randomuserapp.data


sealed class ResultWrapper<out T : Any> {
    class Success<out T : Any>(
        val data: T,
        val message: String? = null,
        val responseCode: Int? = 200
    ) : ResultWrapper<T>()

    class Error(val message: String? = null, val responseCode: Int? = null) :
        ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()
}
