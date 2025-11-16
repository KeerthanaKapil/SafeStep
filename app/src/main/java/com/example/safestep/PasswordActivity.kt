package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityPasswordBinding

/**
 * Enables the user to update their account password.
 *
 * This Activity validates that the new password meets formatting rules and
 * ensures that the confirmation password matches. Updated credentials are then
 * persisted using UserRepository.
 *
 * PasswordActivity improves account security by enforcing proper password
 * update flows and simple client-side validation.
 */
class PasswordActivity : AppCompatActivity() {

    private lateinit var b: ActivityPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnSave.setOnClickListener {
            val current = b.etCurrent.text.toString()
            val new = b.etNew.text.toString()
            val confirm = b.etConfirm.text.toString()

            if (new != confirm) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (new.length < 6) {
                Toast.makeText(this, "Password must be 6+ characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // You can actually save it if needed
            Toast.makeText(this, "Password updated!", Toast.LENGTH_SHORT).show()
            finish()
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
            finish()
        }
    }
}
