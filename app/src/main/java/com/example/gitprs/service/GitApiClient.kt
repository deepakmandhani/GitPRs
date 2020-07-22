package com.example.gitprs.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */

object GitApiClient {

    private const val BASE_URL = "https://api.github.com/"
    private const val TIME_OUT = 30L
    private var retrofit: Retrofit? = null

    val apiClient: Retrofit
        get() {
            return retrofit ?: run {
                return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .baseUrl(BASE_URL)
                    .build()
                    .also { retrofit = it }
            }
        }

    private fun getHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        return httpClient.build()
    }

}