package com.geekbrains.githubclient.data

import com.geekbrains.githubclient.data.net.IDataSource
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun putUsers(api: IDataSource): Single<List<GithubUser>>
    fun getUsers(): Single<List<GithubUser>>
}