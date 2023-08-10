package com.fanirahmat.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.fanirahmat.newsapp.adapters.NewsItemAdapter
import com.fanirahmat.newsapp.api.ApiConfig
import com.fanirahmat.newsapp.models.ArticlesItem
import com.fanirahmat.newsapp.models.NewsBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvNews = findViewById<RecyclerView>(R.id.rv_news)

        ApiConfig.getService().getAllNews(
            ApiConfig.apiKey,10,1, "Indonesia"
        ).enqueue(object : Callback<NewsBase>{
            override fun onResponse(call: Call<NewsBase>, response: Response<NewsBase>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    val listNews = data?.articles
                    val adapter = NewsItemAdapter(listNews as List<ArticlesItem>)

                    adapter.run {
                        rvNews.adapter = this
                        rvNews.layoutManager = LinearLayoutManager(this@MainActivity, VERTICAL , false)

                        notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<NewsBase>, t: Throwable) {

            }

        })

    }
}