package com.example.safestep

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * DAO for the User entity.
 * This interface defines all the database interactions for the `users` table
 *
 * @author  Itzayana Aguilar
 * @version 1.0
 */
@Dao
interface UserDao {
    /**
     * Inserts a new user into the database.
     *
     * @param user  object to be inserted.
     */
    @Insert
    suspend fun insert(user: User)

    /**
     * Finds a user by email
     * This is a suspend function and must be called from a coroutine.
     *
     * @param email The email address to search for.
     * @return The found [User] object, or null if no user with that email exists.
     */
    @Query("SELECT * FROM users WHERE email_address = :email LIMIT 1")
    suspend fun findByEmail(email: String): User?
}
