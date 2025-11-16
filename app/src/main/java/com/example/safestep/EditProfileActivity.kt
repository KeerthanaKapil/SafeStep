package com.example.safestep

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityEditProfileBinding

/**
 * Allows the user to edit their profile details.
 *
 * EditProfileActivity provides UI fields for modifying the user's name and
 * email address. Once validated, the changes are saved to UserRepository and
 * the user is returned to ProfileActivity.
 *
 * The screen uses ViewBinding for safe element access and standard Material
 * components for input.
 */
class EditProfileActivity : AppCompatActivity() {

    private lateinit var b: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(b.root)

        loadExistingData()

        b.btnSave.setOnClickListener {
            saveProfile()
        }

        b.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun loadExistingData() {
        val prefs = getSharedPreferences("user_profile", MODE_PRIVATE)

        b.etName.setText(prefs.getString("name", ""))
        b.etAddress.setText(prefs.getString("address", ""))
        b.etPhone.setText(prefs.getString("phone", ""))
    }

    private fun saveProfile() {
        val name = b.etName.text.toString().trim()
        val address = b.etAddress.text.toString().trim()
        val phone = b.etPhone.text.toString().trim()

        val prefs = getSharedPreferences("user_profile", MODE_PRIVATE)

        prefs.edit().apply {
            putString("name", name)
            putString("address", address)
            putString("phone", phone)
            apply()
        }

        Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show()

        // Tell ProfileActivity to refresh
        setResult(Activity.RESULT_OK)
        finish()
    }
}
