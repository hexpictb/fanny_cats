package ru.aezhkov.funnycats.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.aezhkov.funnycats.data.list.response.CatResponse

interface CatsService {

    @GET("/v1/images/search")
    fun getCats(@Query("page") page: Int, @Query("limit") limit: Int): Single<List<CatResponse>>
}