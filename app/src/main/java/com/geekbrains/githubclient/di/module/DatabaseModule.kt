package com.geekbrains.githubclient.di.module

import androidx.room.Room
import com.geekbrains.githubclient.data.db.Database
import com.geekbrains.githubclient.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room
        .databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()
}