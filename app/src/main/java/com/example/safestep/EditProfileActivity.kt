package com.example.safestep

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityEditProfileBinding

/**
 * Allows the user to edit their profile information.
 * Provides fields to update the user's name, phone number, and address.
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

    /**
     * Loads the current user's data from the UserRepository and populates EditText fields.
     */
    private fun loadExistingData() {
        val user = UserRepository.currentUser
        if (user != null) {
            b.etName.setText(user.name)
            b.etPhone.setText(user.phone)
            // Address is not part of the User entity, so we load it from SharedPreferences as a fallback.
            val prefs = getSharedPreferences("user_profile_extras", MODE_PRIVATE)
            b.etAddress.setText(prefs.getString("address", ""))
        }
    }

    /**
     * Saves the updated profile information.
     */
    private fun saveProfile() {
        val name = b.etName.text.toString().trim()
        val phone = b.etPhone.text.toString().trim()
        val address = b.etAddress.text.toString().trim()

        //  Update the in-memory user object
        UserRepository.currentUser?.let {
            // Create a new User object with updated fields
            val updatedUser = it.copy(name = name, phone = phone)
            UserRepository.currentUser = updatedUser
        }

        // Save extra information to SharedPreferences
        val prefs = getSharedPreferences("user_profile_extras", MODE_PRIVATE)
        prefs.edit().putString("address", address).apply()

        Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show()

         setResult(Activity.RESULT_OK)
        finish()
    }
}
