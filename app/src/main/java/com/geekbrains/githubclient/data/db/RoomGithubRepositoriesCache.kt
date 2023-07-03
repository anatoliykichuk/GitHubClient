package com.geekbrains.githubclient.data.db

import com.geekbrains.githubclient.data.GithubRepository
import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.data.net.IDataSource
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache(
    val api: IDataSource,
    val db: Database,
    val user: GithubUser
) {

    fun putRepositories() = user.reposUrl?.let { url ->

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

    fun getRepositories() = Single.fromCallable {

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