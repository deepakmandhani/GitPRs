package com.example.gitprs.service

import androidx.lifecycle.LiveData
import com.example.gitprs.model.FailureResponse
import com.example.gitprs.model.PullRequestResponse
import com.example.gitprs.model.Response

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */
interface GitApiContract {
    fun getOpenPullRequest(owner: String, repo: String): LiveData<Response<PullRequestResponse, FailureResponse>>
}