package com.geekbrains.githubclient.domain.repository

import com.geekbrains.githubclient.domain.common.IItemView

interface IRepositoryItemView : IItemView {
    fun setName(name: String)
}