package com.geekbrains.githubclient.data

import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource, val url: String
) : IGithubRepositoriesRepo {

    override fun getRepositories() = api.getRepositories(url).subscribeOn(Schedulers.io())
}