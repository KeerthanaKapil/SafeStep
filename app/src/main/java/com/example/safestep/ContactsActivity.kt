package com.example.safestep

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityContactsBinding

/**
 * Provides access to the user's trusted contacts.
 *
 * This Activity displays emergency contact options and serves as a dedicated
 * screen for managing and accessing communication details. While the current
 * version uses static content, it establishes the UI and navigation patterns
 * for future integration.
 *
 * ContactsActivity includes the bottom navigation bar for consistent movement
 * within the application.
 */
class ContactsActivity : AppCompatActivity() {
    private lateinit var b: ActivityContactsBinding
    private val contacts = mutableListOf<Pair<String, String>>() // (name, phone)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnAdd.setOnClickListener {
            val name = b.etName.text.toString().trim()
            val phone = b.etPhone.text.toString().trim()

            when {
                name.isEmpty() || phone.isEmpty() -> {
                    toast("Enter name & phone"); return@setOnClickListener
                }
                !phone.all { it.isDigit() } -> {
                    toast("Phone must be digits only"); return@setOnClickListener
                }
                else -> {
                    contacts += name to phone
                    b.tvList.text = contacts.joinToString("\n") { "• ${it.first}: ${it.second}" }
                    b.etName.text?.clear()
                    b.etPhone.text?.clear()
                }
            }
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
