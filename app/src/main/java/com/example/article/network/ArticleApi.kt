package com.example.article.network

import com.example.article.model.ArticleCallBack
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface ArticleApi {

    @POST("dadmin/app/getArticlesList")
    suspend fun getArticleData(): ArticleCallBack


}