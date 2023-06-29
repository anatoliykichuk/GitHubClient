package com.geekbrains.githubclient.domain

import com.geekbrains.githubclient.domain.common.IItemView

interface IUserItemView : IItemView {
    fun setLogin(login: String)
    fun loadAvatar(url: String)
}