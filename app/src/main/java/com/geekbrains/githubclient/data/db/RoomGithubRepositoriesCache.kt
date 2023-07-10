package com.geekbrains.githubclient.data.db

import com.geekbrains.githubclient.data.GithubRepository
import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.data.IGithubRepositoriesCache
import com.geekbrains.githubclient.data.net.IDataSource
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache(val db: Database) : IGithubRepositoriesCache {

    override fun putRepositories(
        api: IDataSource, user: GithubUser
    ) = user.reposUrl?.let { url ->

        api.getRepositories(url).flatMap { repositories ->
            Single.fromCallable {
                val roomUser = user.login?.let { login ->
                    db.userDao.findByLogin(login)
                } ?: throw RuntimeException("No such user in cache")

                val roomRepos = repositories.map { repository ->
                    RoomGithubRepository(
                        repository.id ?: "",
                        repository.name ?: "",
                        repository.description ?: "",
                        repository.forksCount ?: 0,
                        user.id ?: ""
                    )
                }

                db.repositoryDao.insert(roomRepos)
                repositories
            }
        }
    } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))

    override fun getRepositories(user: GithubUser) = Single.fromCallable {

        val roomUser = user.login?.let { login ->
            db.userDao.findByLogin(login)
        } ?: throw RuntimeException("No such user in cache")

        db.repositoryDao.findForUser(roomUser.id).map { repository ->
            GithubRepository(
                repository.id,
                repository.name,
                repository.description,
                repository.forksCount
            )
        }
    }
}