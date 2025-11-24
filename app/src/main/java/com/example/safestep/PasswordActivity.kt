package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityPasswordBinding

/**
 * Manages a user's password.
 * Provides fields for current password, a new password, and confirmation.
 */
class PasswordActivity : AppCompatActivity() {

    private lateinit var b: ActivityPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnSave.setOnClickListener {
            updatePassword()
        }

        b.btnBack.setOnClickListener { finish() }

        // Bottom Navigation
        b.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        b.bottomNav.navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }
        b.bottomNav.navProfile.setOnClickListener {
            // This screen is part of the Profile flow, so finish() returns to the previous screen.
            finish()
        }
    }

    /**
     * Validates the new password and confirmation, then simulates a password update.
     */
    private fun updatePassword() {
        val current = b.etCurrent.text.toString()
        val newPass = b.etNew.text.toString()
        val confirm = b.etConfirm.text.toString()

        // Input Validation
        if (newPass != confirm) {
            Toast.makeText(this, "New passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }
        if (newPass.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
            return
        }


        Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
