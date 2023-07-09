package com.geekbrains.githubclient.di.module

import com.geekbrains.githubclient.ui.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}