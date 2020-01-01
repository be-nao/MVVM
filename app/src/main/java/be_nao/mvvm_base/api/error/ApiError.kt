package be_nao.mvvm_base.api.error

import be_nao.mvvm_base.api.error.http.HttpConflictError
import be_nao.mvvm_base.api.error.http.HttpForbiddenError
import be_nao.mvvm_base.api.error.http.HttpNotFoundError
import be_nao.mvvm_base.api.error.http.HttpUnauthorizedError
import retrofit2.HttpException

open class ApiError(
    val httpException: HttpException,
    val response: ApiErrorResponse
) : Exception() {

    companion object {
        fun asError(httpException: HttpException, response: ApiErrorResponse): ApiError {
            return when (httpException.code()) {
                HttpUnauthorizedError.HTTP_CODE -> {
                    when (response.statusCode) {
                        // TODO read status code
                        else ->
                            HttpUnauthorizedError(httpException, response)
                    }
                }
                HttpForbiddenError.HTTP_CODE -> {
                    when (response.statusCode) {
                        // TODO read status code
                        else ->
                            HttpForbiddenError(httpException, response)
                    }
                }
                HttpNotFoundError.HTTP_CODE -> {
                    when (response.statusCode) {
                        // TODO read status code
                        else ->
                            HttpNotFoundError(httpException, response)
                    }
                }
                HttpConflictError.HTTP_CODE -> {
                    when (response.statusCode) {
                        // TODO read status code
                        else ->
                            HttpConflictError(httpException, response)
                    }
                }
                InternalServerError.HTTP_CODE -> {
                    InternalServerError.create(httpException, response)
                }
                else -> {
                    ApiError(httpException, response)
                }
            }
        }
    }
}
