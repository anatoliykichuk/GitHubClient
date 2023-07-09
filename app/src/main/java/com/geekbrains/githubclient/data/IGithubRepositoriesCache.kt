package com.geekbrains.githubclient.data

import com.geekbrains.githubclient.data.net.IDataSource
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {

    fun putRepositories(api: IDataSource, user: GithubUser): Single<List<GithubRepository>>
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}