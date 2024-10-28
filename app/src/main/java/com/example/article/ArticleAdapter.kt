package com.example.article

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.article.databinding.ArticleCardlayoutBinding
import com.example.article.model.ArticleData


class ArticleAdapter(private val articleList: MutableList<ArticleData>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(var binding: ArticleCardlayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArticleCardlayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listArtice = articleList[position]
        holder.apply {
            binding.apply {
                var image = "https://diatapp.s3.amazonaws.com/imgs/articles/" + listArtice.thumbnail
                if (listArtice.thumbnail != null) {
                    Glide.with(itemView.context).load(image).into(binding.image);
                } else {
                    Glide.with(itemView.context)
                        .load(itemView.context.getDrawable(R.drawable.baseline_hide_image_24))
                        .into(binding.image);
                }
                titleTV.text = listArtice.title
                if (listArtice.body != null) {
                    subtitleTV.visibility = VISIBLE

                    subtitleTV.text = listArtice.body
                } else {
                    subtitleTV.visibility = GONE
                }
            }
        }
    }
}