package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.safestep.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var b: ActivityRegisterBinding
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(b.root)

        userDao = AppDatabase.getDatabase(applicationContext).userDao()

        b.btnRegister.setOnClickListener {
            val name = b.etName.text?.toString()?.trim().orEmpty()
            val email = b.etEmail.text?.toString()?.trim().orEmpty()
            val phone = b.etPhone.text?.toString()?.trim().orEmpty()
            val pass = b.etPassword.text?.toString()?.trim().orEmpty()

            when {
                name.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty() ->
                    toast("Please fill out all fields")

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                    toast("Invalid email")

                pass.length < 6 -> toast("Password must be at least 6 characters")

                else -> {
                    val newUser = User(name = name, email = email, phone = phone, password = pass)

                      lifecycleScope.launch {
                        userDao.insert(newUser)

                        runOnUiThread {
                            toast("Registered successfully!")
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
        b.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
