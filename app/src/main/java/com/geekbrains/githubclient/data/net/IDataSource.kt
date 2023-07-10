package com.geekbrains.githubclient.data.net

import com.geekbrains.githubclient.data.GithubRepository
import com.geekbrains.githubclient.data.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

private const val END_POINT = "/users"

interface IDataSource {
    @GET(END_POINT)
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<GithubUser>

    @GET
    fun getRepositories(@Url url: String): Single<List<GithubRepository>>
}