package com.fanirahmat.newsapp.pages.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fanirahmat.newsapp.R
import com.fanirahmat.newsapp.adapters.NewsItemAdapter
import com.fanirahmat.newsapp.api.ApiConfig
import com.fanirahmat.newsapp.models.ArticlesItem
import com.fanirahmat.newsapp.models.NewsBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var myContext: Context
    private lateinit var rvNews: RecyclerView
    private lateinit var adapter: NewsItemAdapter

    private var listNews = arrayListOf<ArticlesItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myContext = container?.context!!
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivBanner = view.findViewById<ImageView>(R.id.iv_banner_home)
        rvNews = view.findViewById<RecyclerView>(R.id.rv_top_news)

        adapter = NewsItemAdapter(listNews)
        rvNews.adapter = adapter
        rvNews.layoutManager = LinearLayoutManager(myContext, RecyclerView.VERTICAL , false)


        ApiConfig.getService().getAllTopHeadlineNews(
            ApiConfig.apiKey,10,1, "us"
        ).enqueue(object : Callback<NewsBase> {
            override fun onResponse(call: Call<NewsBase>, response: Response<NewsBase>) {
                if (response.isSuccessful) {
                    val data = response.body()

                    Glide.with(ivBanner)
                        .load(data?.articles?.get(0)?.urlToImage)
                        .error(R.drawable.ic_launcher_background)
                        .into(ivBanner)

                    listNews.clear()
                    for (a in data?.articles!!) {
                        a?.let { listNews.add(it) }
                    }

                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<NewsBase>, t: Throwable) {

            }

        })
    }

}