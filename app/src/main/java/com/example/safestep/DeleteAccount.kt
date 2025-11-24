package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityDeleteAccountBinding

/**
 * Handles the user account deletion process.
 * Provides confirmation screen and, upon confirmation, clears
 * all user data stored in SharedPreferences before navigating away.
 */
class DeleteAccountActivity : AppCompatActivity() {

    private lateinit var b: ActivityDeleteAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDeleteAccountBinding.inflate(layoutInflater)
        setContentView(b.root)

        loadUserData()

        // Set listener for delete confirmation button
        b.btnConfirmDelete.setOnClickListener {
            deleteUser()
        }

        // Set listener to go back to the previous screen
        b.btnBack.setOnClickListener {
            finish()
        }

        // Bottom Navigation
        b.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        b.bottomNav.navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }
        b.bottomNav.navProfile.setOnClickListener {
            finish()
        }
    }

    /**
     * Loads user data from SharedPreferences to show user's name for confirmation.
     */
    private fun loadUserData() {
        val prefs = getSharedPreferences("user_profile", MODE_PRIVATE)
        val name = prefs.getString("name", "User")
        b.tvUserName.text = name
    }

    /**
     * Performs account deletion by clearing all data from SharedPreferences.
     * After deletion,navigate user back to the main screen and clears the activity stack.
     */
    private fun deleteUser() {
        // Remove profile info
        val profilePrefs = getSharedPreferences("user_profile", MODE_PRIVATE)
        profilePrefs.edit().clear().apply()

        // Remove settings
        val settingsPrefs = getSharedPreferences("user_settings", MODE_PRIVATE)
        settingsPrefs.edit().clear().apply()

        Toast.makeText(this, "Account data has been cleared", Toast.LENGTH_LONG).show()

        // Navigate back to the Home screen
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
