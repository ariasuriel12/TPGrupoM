package com.example.climaapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.climaapp.data.entities.User

@Dao
interface UserDao {

    // Insertar un usuario
    @Insert
    suspend fun insertUser(user: User)

    // Buscar un usuario por username y password (Login)
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    // Buscar si existe un usuario por username (Registro)
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUser(username: String): User?
}
