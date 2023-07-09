package com.geekbrains.githubclient.data.db

import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() = instance ?: throw RuntimeException(
            "Database has not been created. Please  call create(context)"
        )
    }
}