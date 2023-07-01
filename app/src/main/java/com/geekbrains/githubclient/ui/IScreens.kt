package com.geekbrains.githubclient.ui

import com.geekbrains.githubclient.data.GithubRepository
import com.geekbrains.githubclient.data.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser?): Screen
    fun repository(repository: GithubRepository?): Screen
}