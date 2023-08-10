package com.fanirahmat.newsapp.models

import com.google.gson.annotations.SerializedName

data class NewsBase(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = ""
)

data class ArticlesItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = "",

	@field:SerializedName("author")
	val author: String? = "",

	@field:SerializedName("urlToImage")
	val urlToImage: String? = "",

	@field:SerializedName("description")
	val description: String? = "",

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("title")
	val title: String? = "",

	@field:SerializedName("url")
	val url: String? = "",

	@field:SerializedName("content")
	val content: String? = ""
)

data class Source(

	@field:SerializedName("name")
	val name: String? = "",

	@field:SerializedName("id")
	val id: String? = ""
)
