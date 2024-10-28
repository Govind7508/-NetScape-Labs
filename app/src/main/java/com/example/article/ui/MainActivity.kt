package com.example.article.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.article.ArticleAdapter
import com.example.article.R
import com.example.article.databinding.ActivityMainBinding
import com.example.article.model.ArticleCallBack
import com.example.article.model.ArticleData
import com.example.article.model.viewmodel.ArticleViewModel
import com.example.article.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel: ArticleViewModel by viewModels()
    private var adapter: ArticleAdapter? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        viewmodel.getArticleResponseData()

        fetchData()
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun fetchData() {
        viewmodel.articleResponse.observe(this) {
            when (it) {
                is Resource.Error -> {
                    binding.noDataFound.visibility = View.VISIBLE
                    binding.articleRV.visibility = View.GONE
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    Toast.makeText(this, "loading.....", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure ->{
                    binding.noDataFound.visibility = View.VISIBLE
                    binding.articleRV.visibility = View.GONE
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    try {
                        binding.noDataFound.visibility = View.GONE
                        binding.articleRV.visibility = View.VISIBLE
                        binding.articleRV.layoutManager = LinearLayoutManager(this)
                        adapter = it.data?.data?.let { it1 -> ArticleAdapter(it1) }
                        binding.articleRV.adapter = adapter
                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    } catch (e: Error) {
                        binding.noDataFound.visibility = View.VISIBLE
                        binding.articleRV.visibility = View.GONE
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    binding.noDataFound.visibility = View.VISIBLE
                    binding.articleRV.visibility = View.GONE
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}