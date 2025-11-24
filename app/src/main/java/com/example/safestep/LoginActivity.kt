package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.safestep.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

/**
 * Activity for handling user login.
 * It validates user info in the Room database and upon success, navigates to the main part of the application.
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var b: ActivityLoginBinding
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        userDao = AppDatabase.getDatabase(applicationContext).userDao()

        b.btnLogin.setOnClickListener {
            loginUser()
        }

        b.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    /**
     * Gathers user credentials, validates them against the database,
     * and handles the login result.
     */
    private fun loginUser() {
        val email = b.etEmail.text.toString().trim()
        val pass = b.etPassword.text.toString().trim()

        //Input Validation
        if (email.isEmpty() || pass.isEmpty()) {
            toast("Please enter both email and password")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            toast("Please enter a valid email address")
            return
        }

        // Database Lookup
        lifecycleScope.launch {
            val user = userDao.findByEmail(email)

            runOnUiThread {
                if (user != null && user.password == pass) {
                    // Login success: store user data and navigate to main screen
                    UserRepository.currentUser = user
                    toast("Login successful!")
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish() // Prevent returning to login screen
                } else {
                    // Login failure
                    toast("Invalid email or password")
                }
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
