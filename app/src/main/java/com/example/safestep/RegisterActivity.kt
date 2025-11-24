package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.safestep.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

/**
 * Class for handling new user registration.
 * Provides input fields for user details, validates the input, and then saves the new user to the Room database.
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var b: ActivityRegisterBinding
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(b.root)

        userDao = AppDatabase.getDatabase(applicationContext).userDao()

        b.btnRegister.setOnClickListener {
            registerUser()
        }

        b.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    /**
     * Gathers user input, validates it, and if successful, creates a new User
     * and inserts it into the database.
     */
    private fun registerUser() {
        val name = b.etName.text.toString().trim()
        val email = b.etEmail.text.toString().trim()
        val phone = b.etPhone.text.toString().trim()
        val pass = b.etPassword.text.toString().trim()

        //Input Validation
        when {
            name.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty() -> {
                toast("Please fill out all fields")
                return
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                toast("Please enter a valid email address")
                return
            }
            pass.length < 6 -> {
                toast("Password must be at least 6 characters long")
                return
            }
        }

        // Database Insertion
        val newUser = User(name = name, email = email, phone = phone, password = pass)
        lifecycleScope.launch {
            userDao.insert(newUser)
            // Switch to the main thread to show toast and navigate
            runOnUiThread {
                toast("Registration successful!")
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish() // Prevent returning to this screen
            }
        }
    }

    /**
     * Helper function to display a short Toast message.
     * @param msg The message to display.
     */
    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
