package com.geekbrains.githubclient.di.component.subcomponent

import com.geekbrains.githubclient.di.module.RepoModule
import com.geekbrains.githubclient.di.scope.RepositoryScope
import com.geekbrains.githubclient.domain.repository.RepositoryPresenter
import com.geekbrains.githubclient.domain.user.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(modules = [RepoModule::class])
interface RepositorySubcomponent {

    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}