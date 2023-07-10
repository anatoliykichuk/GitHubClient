package com.geekbrains.githubclient.di.component

import com.geekbrains.githubclient.di.component.subcomponent.UserSubcomponent
import com.geekbrains.githubclient.di.module.ApiModule
import com.geekbrains.githubclient.di.module.AppModule
import com.geekbrains.githubclient.di.module.CiceroneModule
import com.geekbrains.githubclient.di.module.DatabaseModule
import com.geekbrains.githubclient.di.module.ImageModule
import com.geekbrains.githubclient.domain.main.MainPresenter
import com.geekbrains.githubclient.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ImageModule::class
    ]
)
interface AppComponent {

    fun userSubcomponent(): UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}