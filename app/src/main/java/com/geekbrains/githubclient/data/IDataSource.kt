package com.geekbrains.githubclient.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

private const val END_POINT = "/users"

interface IDataSource {
    @GET(END_POINT)
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<User>
}