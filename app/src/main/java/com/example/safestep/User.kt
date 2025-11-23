package com.example.safestep

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
)
