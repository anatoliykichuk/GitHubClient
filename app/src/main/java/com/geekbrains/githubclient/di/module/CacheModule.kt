package com.geekbrains.githubclient.di.module

import androidx.room.Room
import com.geekbrains.githubclient.data.IGithubRepositoriesCache
import com.geekbrains.githubclient.data.IGithubUsersCache
import com.geekbrains.githubclient.data.db.Database
import com.geekbrains.githubclient.data.db.RoomGithubRepositoriesCache
import com.geekbrains.githubclient.data.db.RoomGithubUsersCache
import com.geekbrains.githubclient.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room
        .databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun usersCache(db: Database): IGithubUsersCache = RoomGithubUsersCache(db)

    @Singleton
    @Provides
    fun repositoryCache(db: Database): IGithubRepositoriesCache = RoomGithubRepositoriesCache(db)
}