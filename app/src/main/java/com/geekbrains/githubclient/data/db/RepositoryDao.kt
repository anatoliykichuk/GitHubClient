package com.geekbrains.githubclient.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomGithubRepository>)

    @Update
    fun update(repository: RoomGithubRepository)

    @Update
    fun update(vararg repositories: RoomGithubRepository)

    @Update
    fun update(repositories: List<RoomGithubRepository>)

    @Delete
    fun delete(repository: RoomGithubRepository)

    @Delete
    fun delete(vararg repositories: RoomGithubRepository)

    @Delete
    fun delete(repositories: List<RoomGithubRepository>)

    @Query("SELECT * FROM RoomGithubRepository")
    fun getAll(): List<RoomGithubRepository>

    @Query("SELECT * FROM RoomGithubRepository WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGithubRepository>
}