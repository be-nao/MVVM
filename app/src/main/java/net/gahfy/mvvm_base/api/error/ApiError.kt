package net.gahfy.mvvm_base.api.error

import net.gahfy.mvvm_base.api.error.http.HttpConflictError
import net.gahfy.mvvm_base.api.error.http.HttpForbiddenError
import net.gahfy.mvvm_base.api.error.http.HttpNotFoundError
import net.gahfy.mvvm_base.api.error.http.HttpUnauthorizedError
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
//                        AuthenticationError.MESSAGE_CODE ->
//                            AuthenticationError.create(httpException, response)
//                        TokenExpiredError.MESSAGE_CODE ->
//                            TokenExpiredError.create(httpException, response)
                        else ->
                            HttpUnauthorizedError(httpException, response)
                    }
                }
                HttpForbiddenError.HTTP_CODE -> {
                    when (response.statusCode) {
//                        AuthorizationError.MESSAGE_CODE ->
//                            AuthorizationError.create(httpException, response)
                        else ->
                            HttpForbiddenError(httpException, response)
                    }
                }
                HttpNotFoundError.HTTP_CODE -> {
                    when (response.statusCode) {
//                        NovelNotFoundError.MESSAGE_CODE ->
//                            NovelNotFoundError.create(httpException, response)
//                        EpisodeNotFoundError.MESSAGE_CODE ->
//                            EpisodeNotFoundError.create(httpException, response)
//                        FeatureNotFoundError.MESSAGE_CODE ->
//                            FeatureNotFoundError.create(httpException, response)
//                        NextEpisodeNotFoundError.MESSAGE_CODE ->
//                            NextEpisodeNotFoundError.create(httpException, response)
//                        PreviousEpisodeNotFoundError.MESSAGE_CODE ->
//                            PreviousEpisodeNotFoundError.create(httpException, response)
//                        GenreNotFoundError.MESSAGE_CODE ->
//                            GenreNotFoundError.create(httpException, response)
//                        EmailAccountNotRegisteredError.MESSAGE_CODE ->
//                            EmailAccountNotRegisteredError.create(httpException, response)
//                        ReviewNotFoundError.MESSAGE_CODE ->
//                            ReviewNotFoundError.create(httpException, response)
                        else ->
                            HttpNotFoundError(httpException, response)
                    }
                }
                HttpConflictError.HTTP_CODE -> {
                    when (response.statusCode) {
//                        LineAccountNotRegisteredError.MESSAGE_CODE ->
//                            LineAccountNotRegisteredError.create(httpException, response)
//                        ExceedEmailQuotaError.MESSAGE_CODE ->
//                            ExceedEmailQuotaError.create(httpException, response)
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
