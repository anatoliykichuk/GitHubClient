package com.geekbrains.githubclient.domain.common

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}