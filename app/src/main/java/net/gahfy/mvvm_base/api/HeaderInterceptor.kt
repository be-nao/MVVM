package net.gahfy.mvvm_base.api

import android.os.Build
import net.gahfy.mvvm_base.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer"
        const val USER_AGENT = "User-Agent"
        private const val APP_NAME = "LINENovel"

        fun getUserAgentValue() =
            "$APP_NAME/${BuildConfig.VERSION_NAME} Android/${Build.VERSION.RELEASE}"

        fun getAuthorizationValue(token: String?) = "$BEARER $token"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = setAuthorisationIfNeeded(chain.request(), "")
        request = setUserAgent(request)
        return chain.proceed(request)
    }

    private fun setUserAgent(request: Request): Request {
        return request.newBuilder()
            .header(USER_AGENT, getUserAgentValue())
            .build()
    }

    private fun setAuthorisationIfNeeded(request: Request, token: String?): Request {
        return if (request.header(AUTHORIZATION) != null || token == null) {
            request
        } else {
            request.newBuilder()
                .header(AUTHORIZATION, getAuthorizationValue(token))
                .build()
        }
    }
}
