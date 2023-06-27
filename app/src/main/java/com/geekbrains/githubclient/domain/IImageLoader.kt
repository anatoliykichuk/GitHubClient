package com.geekbrains.githubclient.domain

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}