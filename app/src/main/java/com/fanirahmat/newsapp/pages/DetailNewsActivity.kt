package com.fanirahmat.newsapp.pages

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fanirahmat.newsapp.R
import com.fanirahmat.newsapp.helper.DateFormatter

class DetailNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ivNews = findViewById<ImageView>(R.id.iv_detail_news)
        val tvTitle = findViewById<TextView>(R.id.tv_detail_title_news)
        val tvSource = findViewById<TextView>(R.id.tv_detail_source)
        val tvAuthor = findViewById<TextView>(R.id.tv_detail_author)
        val tvContent = findViewById<TextView>(R.id.tv_detail_content)
        val tvPublishDate = findViewById<TextView>(R.id.tv_detail_publish_date)
        val tvLearnMore = findViewById<TextView>(R.id.tv_detail_learn_more)

        // Get the Intent that started this activity
        val intent = intent

        // Retrieve the value from the Intent's extras
        val title = intent.getStringExtra("title")
        val source = intent.getStringExtra("source")
        val author = intent.getStringExtra("author")
        val publishDate = intent.getStringExtra("publishDate")
        val urlImage = intent.getStringExtra("urlImage")
        val content = intent.getStringExtra("content")
        val url = intent.getStringExtra("url")


        Glide.with(ivNews)
            .load(urlImage)
            .error(R.drawable.ic_launcher_background)
            .into(ivNews)


        tvTitle.text = title
        tvSource.text = source
        tvAuthor.text = author
        tvContent.text = content

        val publishDateFormatted = publishDate?.let { DateFormatter().convertStringToDate(it, "yyyy-MM-dd") }
        tvPublishDate.text = publishDateFormatted?.let { DateFormatter().formatDateToDayAndDate(it) }

        tvLearnMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Simulate the back button press
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}