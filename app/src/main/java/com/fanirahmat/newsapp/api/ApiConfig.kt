package com.fanirahmat.newsapp.api

import com.fanirahmat.newsapp.models.NewsBase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    const val baseUrl = "https://newsapi.org/v2/"
    const val apiKey = "bb8aa3fdcade467fa738653f3ed284fb"

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() : ApiService {
        return getRetrofit().create(ApiService::class.java)
    }

}