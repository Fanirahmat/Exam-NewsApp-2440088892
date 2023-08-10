package com.fanirahmat.newsapp.api

import com.fanirahmat.newsapp.models.NewsBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getAllNews(
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int?,
        @Query("page") page: Int?,
        @Query("q") q: String,
    ): Call<NewsBase>
}