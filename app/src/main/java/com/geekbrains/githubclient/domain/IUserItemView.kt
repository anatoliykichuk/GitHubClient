package com.geekbrains.githubclient.domain

interface IUserItemView : IItemView {
    fun setLogin(login: String)
    fun loadAvatar(url: String)
}