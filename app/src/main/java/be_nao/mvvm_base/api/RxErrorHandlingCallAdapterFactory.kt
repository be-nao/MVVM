package be_nao.mvvm_base.api

import com.google.gson.Gson
import io.reactivex.*
import io.reactivex.functions.Function
import be_nao.mvvm_base.api.error.ApiError
import be_nao.mvvm_base.api.error.ApiErrorResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

// https://qiita.com/FumihikoSHIROYAMA/items/65d52aea1a9f324d347e

class RxErrorHandlingCallAdapterFactory(private val gson: Gson) : CallAdapter.Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    companion object {
        fun create(gson: Gson): CallAdapter.Factory {
            return RxErrorHandlingCallAdapterFactory(gson)
        }
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        original.get(returnType, annotations, retrofit)?.let {
            return RxCallAdapterWrapper(it, gson)
        }
        return null
    }

    private class RxCallAdapterWrapper<R>(
        private val wrapped: CallAdapter<R, *>,
        private val gson: Gson
    ) : CallAdapter<R, Any> {

        override fun responseType(): Type {
            return wrapped.responseType()
        }

        override fun adapt(call: Call<R>): Any {
            return when (val adapt = wrapped.adapt(call)) {
                is Single<*> -> {
                    adapt.onErrorResumeNext(Function { t ->
                        if (t is HttpException) {
                            return@Function Single.error(asApiError(t))
                        }
                        return@Function Single.error(t)
                    })
                }
                is Observable<*> -> {
                    adapt.onErrorResumeNext(Function { t ->
                        if (t is HttpException) {
                            return@Function Observable.error(asApiError(t))
                        }
                        return@Function Observable.error(t)
                    })
                }
                is Maybe<*> -> {
                    adapt.onErrorResumeNext(Function { t ->
                        if (t is HttpException) {
                            return@Function Maybe.error(asApiError(t))
                        }
                        return@Function Maybe.error(t)
                    })
                }
                is Flowable<*> -> {
                    adapt.onErrorResumeNext(Function { t ->
                        if (t is HttpException) {
                            return@Function Flowable.error(asApiError(t))
                        }
                        return@Function Flowable.error(t)
                    })
                }
                is Completable -> {
                    adapt.onErrorResumeNext(Function { t ->
                        if (t is HttpException) {
                            return@Function Completable.error(asApiError(t))
                        }
                        return@Function Completable.error(t)
                    })
                }
                else -> {
                    throw IllegalStateException("Can not adapt observer. $adapt")
                }
            }
        }

        private fun asApiError(httpException: HttpException): Exception {
            return try {
                val response = gson.fromJson(
                    httpException.response().errorBody()?.charStream(),
                    ApiErrorResponse::class.java
                )
                ApiError.asError(httpException, response)
            } catch (e: IOException) {
                httpException
            }
        }
    }
}
