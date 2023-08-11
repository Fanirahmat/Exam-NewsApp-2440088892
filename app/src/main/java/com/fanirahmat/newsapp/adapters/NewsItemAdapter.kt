package com.fanirahmat.newsapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fanirahmat.newsapp.R
import com.fanirahmat.newsapp.helper.DateFormatter
import com.fanirahmat.newsapp.models.ArticlesItem
import com.fanirahmat.newsapp.pages.DetailNewsActivity

class NewsItemAdapter(
    var list: List<ArticlesItem>
) : RecyclerView.Adapter<NewsItemAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvTitle: TextView
        var tvSource: TextView
        var tvAuthor: TextView
        var tvPublishDate: TextView
        var ivNews: ImageView
        init {
            ivNews = view.findViewById(R.id.iv_news)
            tvTitle = view.findViewById(R.id.tv_title_news)
            tvAuthor = view.findViewById(R.id.tv_author_news)
            tvSource = view.findViewById(R.id.tv_source_news)
            tvPublishDate = view.findViewById(R.id.tv_publish_date_news)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsItemAdapter.ViewHolder, position: Int) {
        val dict = list[position]
        holder.tvTitle.text = dict.title
        holder.tvSource.text = dict.source?.name
        holder.tvAuthor.text = dict.author

        val publishDate = dict.publishedAt?.let { DateFormatter().convertStringToDate(it, "yyyy-MM-dd") }
        holder.tvPublishDate.text = publishDate?.let { DateFormatter().formatDateToDayAndDate(it) }

        Glide.with(holder.ivNews)
            .load(dict.urlToImage)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivNews)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailNewsActivity::class.java)
            intent.putExtra("title", dict.title)
            intent.putExtra("source", dict.source?.name)
            intent.putExtra("author", dict.author)
            intent.putExtra("publishDate", dict.publishedAt)
            intent.putExtra("urlImage", dict.urlToImage)
            intent.putExtra("content", dict.content)
            intent.putExtra("url", dict.url)
            context.startActivity(intent)
        }
    }


}