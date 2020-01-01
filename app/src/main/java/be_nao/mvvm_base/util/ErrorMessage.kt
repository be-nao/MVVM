package be_nao.mvvm_base.util

import android.content.Context
import be_nao.mvvm_base.R
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
