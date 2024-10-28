package com.example.article.repository

import com.example.article.network.ArticleApi
import com.example.article.network.SafeApiCall
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val api: ArticleApi) :SafeApiCall {
    suspend fun getArticle() = safeApiCall {
      api.getArticleData()
    }
}