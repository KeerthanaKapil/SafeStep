package com.example.safestep

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityProfileBinding

/**
 * Displays the user's profile information.
 * Helps with managing user specific data and settings,
 * providing navigation to edit profile details and manage security settings.
 *
 * @author  Jovanni Maya
 * @version 1.0
 */
class ProfileActivity : AppCompatActivity() {

    private lateinit var b: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(b.root)

        loadUserData()

        // Navigate to the Edit Profile screen
        b.btnManageInformation.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE)
        }

        // Navigate to the Security screen
        b.btnManageSecurity.setOnClickListener {
            startActivity(Intent(this, SecurityActivity::class.java))
        }

        // Bottom Navigation --
        b.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        b.bottomNav.navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        b.bottomNav.navProfile.setOnClickListener {
             }
    }

    /**
     * Loads user data from SharedPreferences and updates it.
     *
     */
    private fun loadUserData() {
        val user = UserRepository.currentUser
        if (user != null) {
            b.tvUserName.text = user.name
        } else {
            // Fallback for when no user is logged in, though this screen
            // should not typically be accessible in that state.
            b.tvUserName.text = "User"
        }
    }

    /**
     * Handles the result from the EditProfileActivity.
     * If the profile was successfully updated, it reloads the user data to reflect the changes.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadUserData() // Refresh user data after editing
        }
    }

    companion object {
        private const val EDIT_PROFILE_REQUEST_CODE = 1001
    }
}
