package com.example.safestep
/**
 * Represents the user data model for the SafeStep application.
 *
 * This class defines the structure of a user, including essential fields such as
 * name, email, and password. Instances of this model are stored, retrieved, and
 * updated through UserRepository. The User class acts as the central data object
 * for all user-related operations within the app, including login, profile
 * display, and profile editing.
 */
data class User(
    var name: String = "User",
    var address: String = "1234 st name Ave",
    var phone: String = "+1 123-456-7890"
)
