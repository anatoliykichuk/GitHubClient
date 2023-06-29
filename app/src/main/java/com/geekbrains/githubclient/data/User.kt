package com.geekbrains.githubclient.data

import com.google.gson.annotations.Expose

data class User(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null
)
