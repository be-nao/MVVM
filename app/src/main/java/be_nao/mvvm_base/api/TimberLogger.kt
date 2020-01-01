package be_nao.mvvm_base.api

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class TimberLogger : HttpLoggingInterceptor.Logger {

    companion object {
        const val TAG = "OkHttp"
    }

    override fun log(message: String) {
        Timber.tag(TAG).d(message)
    }
}
