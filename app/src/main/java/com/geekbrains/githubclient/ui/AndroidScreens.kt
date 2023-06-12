package com.geekbrains.githubclient.ui

import com.geekbrains.githubclient.ui.pages.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}