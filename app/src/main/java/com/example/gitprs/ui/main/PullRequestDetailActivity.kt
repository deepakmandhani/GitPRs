package com.example.gitprs.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.gitprs.R
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */

class PullRequestDetailActivity : AppCompatActivity() {

    companion object {
        const val PR_URL = "pr_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val newsUrl = intent.getStringExtra(PR_URL)
        val settings = wv_details.settings
        settings.javaScriptEnabled = true
        wv_details.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress_bar.visibility = View.GONE
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress_bar.visibility = View.VISIBLE
            }
        }

        newsUrl?.let {
            wv_details.loadUrl(newsUrl)
        } ?: super.onBackPressed()
    }
}