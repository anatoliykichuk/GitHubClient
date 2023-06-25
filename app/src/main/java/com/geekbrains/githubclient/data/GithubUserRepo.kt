package com.geekbrains.githubclient.data

import io.reactivex.rxjava3.core.Observable

class GithubUserRepo(
    private val repositories: List<GithubUser> = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )
) {
    fun getUsers(): List<GithubUser> {
        return repositories
    }

    fun getUsersAsync(): Observable<GithubUser> {
        return Observable.fromIterable(repositories)
    }
}