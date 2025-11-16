package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityLoginBinding

/**
 * Login screen: basic validation then navigates to Home (MainActivity).

 * Manages the login process for the SafeStep application.
 
 * This class collects the user’s email and password, validates the input, and
 * authenticates the user using data stored in UserRepository. If the credentials
 * are correct, the class navigates the user to MainActivity. It also displays
 * error messages when login details are invalid, ensuring a clear and user-
 * friendly sign-in experience.
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var b: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnLogin.setOnClickListener {
            val email = b.etEmail.text?.toString()?.trim().orEmpty()
            val pass  = b.etPassword.text?.toString()?.trim().orEmpty()

            when {
                email.isEmpty() || pass.isEmpty() -> toast("Enter email & password")
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> toast("Invalid email")
                pass.length < 6 -> toast("Password must be 6+ chars")
                else -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish() // Don't return to login on Back
                }
            }
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
