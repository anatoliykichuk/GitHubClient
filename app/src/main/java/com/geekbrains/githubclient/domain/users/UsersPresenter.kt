package com.geekbrains.githubclient.domain.users

import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.data.IGithubUsersRepo
import com.geekbrains.githubclient.domain.IUserItemView
import com.geekbrains.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter: MvpPresenter<UsersView>() {

    @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject lateinit var usersRepo: IGithubUsersRepo

    //RetrofitGithubUsersRepo(ApiHolder.api, App.networkStatus, Database.getInstance())

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.itemPosition]

            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun getCount() = users.size
    }

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        userListPresenter.itemClickListener = { itemView ->
            val user = userListPresenter.users[itemView.itemPosition]

            router.navigateTo(screens.user(user))
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ githubUsers ->
                userListPresenter.users.apply {
                    clear()
                    addAll(githubUsers)
                }
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
