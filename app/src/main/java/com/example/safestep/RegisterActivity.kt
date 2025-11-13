package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityRegisterBinding

/**
 * Register screen: validates inputs, then navigates to LoginActivity.
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var b: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnRegister.setOnClickListener {
            val name = b.etName.text?.toString()?.trim().orEmpty()
            val email = b.etEmail.text?.toString()?.trim().orEmpty()
            val pass = b.etPassword.text?.toString()?.trim().orEmpty()
            val confirm = b.etPassword.text?.toString()?.trim().orEmpty()

            when {
                name.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty() ->
                    toast("Please fill out all fields")

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                    toast("Invalid email")

                pass.length < 6 -> toast("Password must be at least 6 characters")

                pass != confirm -> toast("Passwords do not match")

                else -> {
                    toast("Registered successfully!")
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
