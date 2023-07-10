package com.geekbrains.githubclient.data.net

import com.geekbrains.githubclient.data.IGithubUsersCache
import com.geekbrains.githubclient.data.IGithubUsersRepo
import com.geekbrains.githubclient.data.db.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IGithubUsersCache
) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->

        if (isOnline) {
            cache.putUsers(api)
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}