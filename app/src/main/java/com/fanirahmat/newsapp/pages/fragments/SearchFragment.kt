package com.fanirahmat.newsapp.pages.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fanirahmat.newsapp.R
import com.fanirahmat.newsapp.adapters.NewsItemAdapter
import com.fanirahmat.newsapp.api.ApiConfig
import com.fanirahmat.newsapp.models.ArticlesItem
import com.fanirahmat.newsapp.models.NewsBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView.VERTICAL

class SearchFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNews = view.findViewById<RecyclerView>(R.id.rv_search_news)
        val btnSearch = view.findViewById<Button>(R.id.btn_search)
        val etSearch = view.findViewById<EditText>(R.id.et_search)

        adapter = NewsItemAdapter(listNews)
        rvNews.adapter = adapter
        rvNews.layoutManager = LinearLayoutManager(myContext, VERTICAL , false)

        btnSearch.setOnClickListener {
            searchNews(etSearch.text.toString())
        }
    }

    private fun searchNews(q: String) {
        ApiConfig.getService().getAllNews(
            ApiConfig.apiKey,10,1, q
        ).enqueue(object : Callback<NewsBase> {
            override fun onResponse(call: Call<NewsBase>, response: Response<NewsBase>) {
                if (response.isSuccessful) {
                    val data = response.body()

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