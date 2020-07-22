package com.example.gitprs.common

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager

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

    fun hideKeyboard(activity: Activity) {
        try {
            val imm =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val ib = activity.currentFocus!!.windowToken
            imm.hideSoftInputFromWindow(ib, 0)
        } catch (e: Exception) {
        }

    }
}