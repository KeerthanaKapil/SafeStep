package com.example.safestep

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivitySecurityBinding

/**
 * Provides a hub for security-related user settings.
 * From here, the user can navigate to manage their password, check-in times,
 * and initiate account deletion.
 *
 * @author  Jovanni Maya
 * @version 1.0
 */
class SecurityActivity : AppCompatActivity() {

    private lateinit var b: ActivitySecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(b.root)

        loadUserData()

        // Navigate to the Check-in screen (Feature not yet implemented)
//        b.btnCheckIn.setOnClickListener {
//            startActivity(Intent(this, CheckInActivity::class.java))
//        }

        // Navigate to the Password management screen
        b.btnPassword.setOnClickListener {
            startActivity(Intent(this, PasswordActivity::class.java))
        }

        // Navigate to the account deletion screen (Feature not yet implemented)
//        b.btnDeleteAccount.setOnClickListener {
//            startActivity(Intent(this, DeleteAccountActivity::class.java))
//        }

        // Navigate to the Safety Tips screen
        b.btnTips.setOnClickListener {
            startActivity(Intent(this, TipListActivity::class.java))
        }

        // Go back to the previous screen (ProfileActivity)
        b.btnBack.setOnClickListener {
            finish()
        }

        // --- Bottom Navigation Listeners ---
        b.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        b.bottomNav.navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        b.bottomNav.navProfile.setOnClickListener {
            finish()  // Already on a sub-screen of Profile, so just go back
        }
    }

    /**
     * Loads the current user's name from the [UserRepository] and displays it.
     */
    private fun loadUserData() {
        val user = UserRepository.currentUser
        if (user != null) {
            b.tvUserName.text = user.name
        } else {
            b.tvUserName.text = "User"
        }
    }
}
