package com.example.article.model.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.article.model.ArticleCallBack
import com.example.article.network.Resource
import com.example.article.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val repository: ArticleRepository) :
    ViewModel() {
    private val _articleDataResponse: MutableLiveData<Resource<ArticleCallBack>?> =
        MutableLiveData()
    val articleResponse: LiveData<Resource<ArticleCallBack>?>
        get() = _articleDataResponse
    fun getArticleResponseData() = viewModelScope.launch {
        _articleDataResponse.value = Resource.Loading()
        try{
            _articleDataResponse.value = repository.getArticle()
        }catch (e :Exception){
            _articleDataResponse.value = Resource.Error("error")
            Log.e("error", "Error")
        }
    }
}