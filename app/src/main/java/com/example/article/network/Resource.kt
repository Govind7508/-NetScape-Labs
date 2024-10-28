package com.example.article.network

import okhttp3.ResponseBody


sealed class Resource<out T> {
    data class Success<out T>(val data: T? = null) : Resource<T>()
    data class Loading(val nothing: Nothing? = null) : Resource<Nothing>()
    data class Error(val msg: String?) : Resource<Nothing>()
    data class Failure(
        val isNetworkError :Boolean?,
        val errorCode : Int?,
        val errorBody : ResponseBody?
    ) : Resource<Nothing>()
}