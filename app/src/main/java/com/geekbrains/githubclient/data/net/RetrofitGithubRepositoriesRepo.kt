package com.geekbrains.githubclient.data.net

import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.data.IGithubRepositoriesRepo
import com.geekbrains.githubclient.data.db.Database
import com.geekbrains.githubclient.data.db.INetworkStatus
import com.geekbrains.githubclient.data.db.RoomGithubRepositoriesCache
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->

            val cache = RoomGithubRepositoriesCache(api, db, user)

            if (isOnline) {
                cache.putRepositories()
            } else {
                cache.getRepositories()
            }
        }.subscribeOn(Schedulers.io())
}