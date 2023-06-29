package com.geekbrains.githubclient.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val id: Long? = null,
    @Expose val name: String? = null,
    @Expose val description: String? = null,
    @Expose val url: String? = null,
    @Expose val private: Boolean = false,
    @Expose val fork: Boolean = false,
    @Expose val forksUrl: String? = null,
    @Expose val forksCount: Long = 0
) : Parcelable
