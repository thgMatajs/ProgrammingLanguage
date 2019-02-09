package com.thgcode.kotlinrv.api

import retrofit2.Call
import retrofit2.http.Query

interface GuitHubService {

    fun searchRepositories(
        @Query("q")query: String,
        @Query("sort")sort: String = "stars",
        @Query("order")order: String = "desc"
    ): Call<GithubRepositoriesResult>
}