package com.example.safestep

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single user in the Room database.
 * Each instance of this class corresponds to a row in the user table.
 *
 * @property id Identifier for the user.
 * @property name User's name.
 * @property email User's email address
 * @property phone User's phone number.
 * @property password User's password.
 *
 * @author  Itzayana Aguilar
 * @version 1.0
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "full_name")
    val name: String,

    @ColumnInfo(name = "email_address")
    val email: String,

    @ColumnInfo(name = "phone_number")
    val phone: String,

    @ColumnInfo(name = "user_password")
    val password: String
) {
    /**
     * Overridden to prevent leaking the password if the object is logged.
     */
    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email', phone='$phone')"
    }
}
