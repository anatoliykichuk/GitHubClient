package com.geekbrains.githubclient.data.db

import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.data.IGithubUsersCache
import com.geekbrains.githubclient.data.net.IDataSource
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(val db: Database) : IGithubUsersCache {

    override fun putUsers(api: IDataSource) = api.getUsers().flatMap { users ->
        Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id ?: "",
                    user.login ?: "",
                    user.avatarUrl ?: "",
                    user.reposUrl ?: ""
                )
            }

            db.userDao.insert(roomUsers)
            users
        }
    }

    override fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl
            )
        }
    }
}