package be_nao.mvvm_base.api.random_user

import io.reactivex.Single
import retrofit2.http.GET

interface RandomUserService {
    @GET("api")
    fun getUser(): Single<RandomUser>

}

data class RandomUser(
    var results: List<Result>,
    var info: Info
)

data class Info(var see: String)

data class Result(
    var gender: String,
    var email: String
)
