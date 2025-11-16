package com.example.safestep
/**
 * Provides simple in-memory storage for user information.
 *
 * UserRepository handles retrieval, updates, and deletion of the User object
 * used throughout the app. While not persistent, it simulates a lightweight
 * data layer suitable for early prototypes and academic Android projects.
 *
 * The repository is used by ProfileActivity, EditProfileActivity,
 * PasswordActivity, and DeleteAccount.
 */
object UserRepository {
    var currentUser = User()

    fun updateUser(name: String, address: String, phone: String) {
        currentUser.name = name
        currentUser.address = address
        currentUser.phone = phone
    }
}



