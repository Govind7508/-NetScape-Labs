package com.example.article.common

import com.example.article.network.ArticleApi
import com.example.article.network.RetrofitData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideArticleApi(
        retrofitData: RetrofitData
    ): ArticleApi {
        return retrofitData.buildApi(ArticleApi::class.java)
    }
}