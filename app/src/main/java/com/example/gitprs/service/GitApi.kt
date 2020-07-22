package com.example.gitprs.service

import com.example.gitprs.model.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */

interface GitApi {

    @GET("repos/{owner}/{repo}/pulls?state=open")
    fun getOpenPullRequest(@Path("owner") owner: String, @Path("repo") repo: String): Call<List<PullRequest>>
}