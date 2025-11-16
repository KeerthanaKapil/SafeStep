/**
 * ProfileActivity
 * ----------------
 * Displays the currently authenticated user's profile details including
 * name and email address. The Activity also provides access to functional
 * sub-screens such as Edit Profile, Password Update, and Account Deletion.
 */
package com.example.safestep

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityProfileBinding

/**
 * Displays the user's profile information and account controls.
 *
 * This Activity retrieves user information from UserRepository and renders
 * basic details such as name and email. It also provides actions to edit the
 * profile, update the password, or delete the account entirely.
 *
 * ProfileActivity includes the shared bottom navigation bar and acts as the
 * central screen for user account management.
 */
class ProfileActivity : AppCompatActivity() {

    private lateinit var b: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(b.root)

        loadUserData()

        // Manage Information -> Edit Profile
        b.btnManageInformation.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivityForResult(intent, 1001)
        }

        // ✅ FIX: Manage Security button working now
        b.btnManageSecurity.setOnClickListener {
            startActivity(Intent(this, SecurityActivity::class.java))
        }

        // Bottom navigation
        b.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        b.bottomNav.navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        b.bottomNav.navProfile.setOnClickListener {
            // Already on Profile → do nothing
        }
    }


    private fun loadUserData() {
        val prefs = getSharedPreferences("user_profile", MODE_PRIVATE)

        val name = prefs.getString("name", "User")
        val address = prefs.getString("address", "")
        val phone = prefs.getString("phone", "")

        b.tvUserName.text = name
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            loadUserData()   // refresh username live
        }
    }
}
