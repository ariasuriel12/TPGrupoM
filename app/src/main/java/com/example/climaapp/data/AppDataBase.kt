package com.example.climaapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.climaapp.data.dao.UserDao
import com.example.climaapp.data.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
