package com.geekbrains.githubclient.data.net

import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.data.IGithubRepositoriesCache
import com.geekbrains.githubclient.data.IGithubRepositoriesRepo
import com.geekbrains.githubclient.data.db.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IGithubRepositoriesCache
) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->

            if (isOnline) {
                cache.putRepositories(api, user)
            } else {
                cache.getRepositories(user)
            }
        }.subscribeOn(Schedulers.io())
}