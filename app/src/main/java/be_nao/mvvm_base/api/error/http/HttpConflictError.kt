package be_nao.mvvm_base.api.error.http


import be_nao.mvvm_base.api.error.ApiError
import be_nao.mvvm_base.api.error.ApiErrorResponse
import retrofit2.HttpException

class HttpConflictError(
    httpException: HttpException,
    response: ApiErrorResponse
) : ApiError(httpException, response) {

    companion object {
        const val HTTP_CODE = 409
    }
}
