package net.gahfy.mvvm_base.api

import NullOnEmptyConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import net.gahfy.mvvm_base.App
import net.gahfy.mvvm_base.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    companion object {
        const val TIME_OUT_SECOND: Long = 30
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(app: App): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(TimberLogger()).apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            } else {
                level = HttpLoggingInterceptor.Level.NONE
//                redactHeader(HeaderInterceptor.AUTHORIZATION)
            }
        }
        return OkHttpClient.Builder()
//            .addInterceptor(HeaderInterceptor())
            .connectTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor) // Always call before `build()` method
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(app: App, gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(gson))
//            .baseUrl(app.getString(R.string.base_api_url))
            .client(okHttpClient)
            .build()
    }

//    @Provides
//    @Singleton
//    fun providesLineApiClient(app: App): LineApiClient {
//        return LineApiClientBuilder(app, app.getString(R.string.line_channel_id)).build()
//    }
}
