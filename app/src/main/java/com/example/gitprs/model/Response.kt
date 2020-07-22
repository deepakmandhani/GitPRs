package com.example.gitprs.model

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */

data class Response<T, U>(
    var obj: T? = null,
    var failure: U? = null
)