package com.geekbrains.githubclient.di.component.subcomponent

import com.geekbrains.githubclient.di.module.UserModule
import com.geekbrains.githubclient.di.scope.UserScope
import com.geekbrains.githubclient.domain.users.UsersPresenter
import com.geekbrains.githubclient.ui.adapter.UsersAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserSubcomponent {

    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersAdapter: UsersAdapter)
}