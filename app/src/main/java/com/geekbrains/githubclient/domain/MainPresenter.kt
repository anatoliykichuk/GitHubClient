package com.geekbrains.githubclient.domain

import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.data.GithubUserRepo
import com.geekbrains.githubclient.ui.adapter.IUserItemView
import com.geekbrains.githubclient.ui.adapter.IUserListPresenter
import moxy.MvpPresenter

class MainPresenter(val usersRepo: GithubUserRepo) : MvpPresenter<MainView>() {

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.itemPosition]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        userListPresenter.itemClickListener = { itemView ->
            // TODO: переход на экран пользователя
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }
}