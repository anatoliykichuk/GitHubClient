package com.geekbrains.githubclient.ui

import android.app.Application
import com.geekbrains.githubclient.data.db.AndroidNetworkStatus
import com.geekbrains.githubclient.data.db.Database
import com.geekbrains.githubclient.data.db.INetworkStatus
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var networkStatus: INetworkStatus
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder
        get() = cicerone.getNavigatorHolder()

    val router
        get() = cicerone.router

    val screens: IScreens = AndroidScreens()

    override fun onCreate() {
        super.onCreate()

        instance = this
        networkStatus = AndroidNetworkStatus(instance)

        Database.create(this)
    }
}