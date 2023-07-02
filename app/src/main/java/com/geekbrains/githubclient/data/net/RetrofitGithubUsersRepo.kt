package com.geekbrains.githubclient.data.net

import com.geekbrains.githubclient.data.IGithubUsersRepo
import com.geekbrains.githubclient.data.db.Database
import com.geekbrains.githubclient.data.db.INetworkStatus
import com.geekbrains.githubclient.data.db.RoomGithubUsersCache
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->

        val cache = RoomGithubUsersCache(api, db)

        if (isOnline) {
            cache.putUsers()
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}