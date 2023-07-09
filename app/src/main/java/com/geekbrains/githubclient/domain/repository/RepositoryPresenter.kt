package com.geekbrains.githubclient.domain.repository

import com.geekbrains.githubclient.data.GithubRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter(
    val repository: GithubRepository
) : MvpPresenter<RepositoryView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}