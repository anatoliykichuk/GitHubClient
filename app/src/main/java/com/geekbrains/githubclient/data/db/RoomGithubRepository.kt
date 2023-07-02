package com.geekbrains.githubclient.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey
    var id: String,
    var name: String,
    var description: String,
    var url: String,
    var private: Boolean,
    var fork: Boolean,
    var forksUrl: String,
    var forksCount: Int,
    var userId: String
)