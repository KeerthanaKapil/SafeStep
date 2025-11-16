package com.example.safestep

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivitySecurityBinding
/**
 * Displays general safety and security information for the user.
 *
 * This class provides educational guidance related to personal safety,
 * cybersecurity practices, or awareness tips. It functions as an informational
 * screen within the SafeStep app and helps users learn how to stay safe both
 * physically and digitally. The Activity also includes the shared bottom
 * navigation bar so users can easily move back to the Home, Profile, or
 * Contacts screens.
 */
class SecurityActivity : AppCompatActivity() {

    private lateinit var b: ActivitySecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(b.root)

        loadUserData()

        //  Manage Check-in Time
        b.btnCheckIn.setOnClickListener {
            startActivity(Intent(this, CheckInActivity::class.java))
        }

        //  Manage Password
        b.btnPassword.setOnClickListener {
            startActivity(Intent(this, PasswordActivity::class.java))
        }

        //  Delete Account (show confirmation for now)
        b.btnDeleteAccount.setOnClickListener {
            // You can replace this with a real delete flow later
            android.widget.Toast.makeText(this, "Account deletion disabled for demo", android.widget.Toast.LENGTH_SHORT).show()
        }

        //  Back to Profile
        b.btnBack.setOnClickListener {
            finish()
        }

        //  Bottom Navigation
        b.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        b.bottomNav.navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        b.bottomNav.navProfile.setOnClickListener {
            finish()  // returns to ProfileActivity
        }

        b.btnDeleteAccount.setOnClickListener {
            startActivity(Intent(this, DeleteAccountActivity::class.java))
        }

    }

    private fun loadUserData() {
        val prefs = getSharedPreferences("user_profile", MODE_PRIVATE)
        val name = prefs.getString("name", "User")
        b.tvUserName.text = name
    }
}
