package com.geekbrains.githubclient.ui

import android.app.Application
import com.geekbrains.githubclient.di.component.AppComponent
import com.geekbrains.githubclient.di.component.DaggerAppComponent
import com.geekbrains.githubclient.di.component.subcomponent.RepositorySubcomponent
import com.geekbrains.githubclient.di.component.subcomponent.UserSubcomponent
import com.geekbrains.githubclient.di.module.AppModule
import com.geekbrains.githubclient.di.scope.scopecontainer.IRepositoryScopeContainer
import com.geekbrains.githubclient.di.scope.scopecontainer.IUserScopeContainer

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }
}
