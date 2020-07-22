package com.example.gitprs.common

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */

object Utils {

    fun isInternetConnected(context: Context?): Boolean =
        context?.let {
            try {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                return@let cm.activeNetworkInfo?.isConnectedOrConnecting ?: false
            } catch (e: Exception) {
                return@let false
            }
        } ?: false
}