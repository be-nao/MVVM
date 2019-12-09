package net.gahfy.mvvm_base

import android.content.Context
import java.io.IOException

class ErrorMessage(private val context: Context) {

    fun getErrorMessage(e: Throwable?): String {
        return when (e) {
            is IOException -> {
                context.getString(R.string.common_error_network)
            }
            else -> {
                context.getString(R.string.common_error_unknown)
            }
        }
    }
}
