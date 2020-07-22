package com.example.gitprs.ui.main

import androidx.lifecycle.*
import com.example.gitprs.model.FailureResponse
import com.example.gitprs.model.PullRequest
import com.example.gitprs.service.GitApiContract

class MainViewModel(private val service: GitApiContract) : ViewModel() {


    private val pullRequestMediatorLiveData = MediatorLiveData<List<PullRequest>>()
    private val failureResponse = MutableLiveData<FailureResponse>()

    fun observePullRequestLiveData(): LiveData<List<PullRequest>> {
        return pullRequestMediatorLiveData
    }

    fun observeFailure(): LiveData<FailureResponse> {
        return failureResponse
    }

    fun getOpenPullRequest(owner: String, repo: String) {
        val liveData = service.getOpenPullRequest(owner, repo)
        pullRequestMediatorLiveData.addSource(liveData) {
            it.obj?.let {
                pullRequestMediatorLiveData.postValue(it)
            } ?: run {
                failureResponse.postValue(it.failure)
            }
            pullRequestMediatorLiveData.removeSource(liveData)
        }
    }


    class AppViewModelFactory(private val service: GitApiContract) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(service) as T
        }
    }
}
