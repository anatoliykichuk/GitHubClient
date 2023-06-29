package com.geekbrains.githubclient.ui

import com.geekbrains.githubclient.data.GithubRepository
import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.ui.pages.RepositoryFragment
import com.geekbrains.githubclient.ui.pages.UserFragment
import com.geekbrains.githubclient.ui.pages.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens(private val user: GithubUser? = null) : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser?): Screen {
        return FragmentScreen { UserFragment.newInstance(user) }
    }

    override fun repository(repository: GithubRepository?): Screen {
        return FragmentScreen { RepositoryFragment.newInstance(repository) }
    }
}