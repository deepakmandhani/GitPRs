package com.example.gitprs.common

import com.example.gitprs.service.GitApiService

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */

object Instance {

    private var apiService: GitApiService? = null

    fun getApiService(): GitApiService {
        return apiService ?: run {
            return GitApiService().also {
                apiService = it
            }
        }
    }
}