package com.example.safestep

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safestep.databinding.ActivityContactsBinding
import com.example.safestep.databinding.DialogAddContactBinding

/**
 * Manages a list of emergency contacts.
 * This screen displays contacts  and provides functionality to add, edit, and delete them.
 *
 * @author  Jovanni Maya
 * @version 1.0
 */
class ContactsActivity : AppCompatActivity() {

    private lateinit var b: ActivityContactsBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Initialize with an empty list
        adapter = ContactAdapter(mutableListOf())

        b.recyclerContacts.layoutManager = LinearLayoutManager(this)
        b.recyclerContacts.adapter = adapter

        // Set up button listeners
        b.btnAddContact.setOnClickListener {
            showAddDialog()
        }

        b.btnDeleteContact.setOnClickListener {
            adapter.deleteSelected()
        }

        b.btnEditContact.setOnClickListener {
            adapter.editSelected(this)
        }
    }

    /**
     * Displays an AlertDialog for adding a new contact.
     * Contains fields for the contact's name and phone number.
     */
    private fun showAddDialog() {
        val dialogBinding = DialogAddContactBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add New Contact")
            .setView(dialogBinding.root)
            .create()

        dialog.show()

         dialogBinding.btnSave.setOnClickListener {
            val name = dialogBinding.etName.text.toString().trim()
            val phone = dialogBinding.etPhone.text.toString().trim()

            //  Input Validation
            when {
                name.isEmpty() -> toast("Name cannot be empty")
                phone.isEmpty() -> toast("Phone number cannot be empty")
                !phone.all { it.isDigit() } -> toast("Phone number must contain only digits")
                else -> {
                    adapter.addContact(Contact(name, phone))
                    dialog.dismiss()
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
