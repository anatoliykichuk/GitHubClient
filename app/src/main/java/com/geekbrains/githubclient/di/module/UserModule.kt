package com.geekbrains.githubclient.di.module

import com.geekbrains.githubclient.data.IGithubUsersCache
import com.geekbrains.githubclient.data.IGithubUsersRepo
import com.geekbrains.githubclient.data.db.Database
import com.geekbrains.githubclient.data.db.INetworkStatus
import com.geekbrains.githubclient.data.db.RoomGithubUsersCache
import com.geekbrains.githubclient.data.net.IDataSource
import com.geekbrains.githubclient.data.net.RetrofitGithubUsersRepo
import com.geekbrains.githubclient.di.scope.UserScope
import com.geekbrains.githubclient.di.scope.scopecontainer.IUserScopeContainer
import com.geekbrains.githubclient.ui.App
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun usersCache(db: Database): IGithubUsersCache = RoomGithubUsersCache(db)

    @UserScope
    @Provides
    open fun usersRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}