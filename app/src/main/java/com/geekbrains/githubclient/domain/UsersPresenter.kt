package com.geekbrains.githubclient.domain

import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.data.GithubUserRepo
import com.geekbrains.githubclient.ui.IScreens
import com.geekbrains.githubclient.ui.adapter.IUserItemView
import com.geekbrains.githubclient.ui.adapter.IUserListPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    val usersRepo: GithubUserRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<UsersView>() {

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
            val user = userListPresenter.users[itemView.itemPosition]
            router.navigateTo(screens.user(user))
        }
    }

    fun loadData() {
        val usersObserver = object : Observer<List<GithubUser>> {
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: List<GithubUser>) {
                userListPresenter.users.addAll(t)
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {}
        }

        usersRepo.getUsersAsync().subscribe(usersObserver)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}