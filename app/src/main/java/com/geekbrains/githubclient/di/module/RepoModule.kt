package com.geekbrains.githubclient.di.module

import com.geekbrains.githubclient.data.IGithubRepositoriesCache
import com.geekbrains.githubclient.data.IGithubRepositoriesRepo
import com.geekbrains.githubclient.data.db.Database
import com.geekbrains.githubclient.data.db.INetworkStatus
import com.geekbrains.githubclient.data.db.RoomGithubRepositoriesCache
import com.geekbrains.githubclient.data.net.IDataSource
import com.geekbrains.githubclient.data.net.RetrofitGithubRepositoriesRepo
import com.geekbrains.githubclient.di.scope.RepositoryScope
import com.geekbrains.githubclient.di.scope.scopecontainer.IRepositoryScopeContainer
import com.geekbrains.githubclient.ui.App
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun repositoriesCache(db: Database): IGithubRepositoriesCache = RoomGithubRepositoriesCache(db)

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}