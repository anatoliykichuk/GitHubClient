package com.geekbrains.githubclient.data

import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(): Single<List<GithubRepository>>
}