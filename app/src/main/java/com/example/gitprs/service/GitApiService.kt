package com.example.gitprs.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitprs.model.FailureResponse
import com.example.gitprs.model.PullRequest
import com.example.gitprs.model.Response
import retrofit2.Call
import retrofit2.Callback

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */

class GitApiService : GitApiContract {

    private val apiService by lazy {
        GitApiClient.apiClient.create(GitApi::class.java)
    }

    override fun getOpenPullRequest(
        owner: String,
        repo: String
    ): LiveData<Response<List<PullRequest>, FailureResponse>> {
        val liveData = MutableLiveData<Response<List<PullRequest>, FailureResponse>>()
        apiService.getOpenPullRequest(owner, repo).enqueue(object : Callback<List<PullRequest>> {
            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                liveData.value = Response(null, FailureResponse(t))
            }

            override fun onResponse(
                call: Call<List<PullRequest>>,
                response: retrofit2.Response<List<PullRequest>>
            ) {
                if (response.isSuccessful) {
                    liveData.value = Response(response.body(), null)
                } else {
                    response.errorBody()
                    liveData.value = Response(null, FailureResponse(response.message()))
                }
            }

        })
        return liveData
    }

}