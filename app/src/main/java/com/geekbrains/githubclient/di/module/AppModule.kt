package com.geekbrains.githubclient.di.module

import com.geekbrains.githubclient.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}