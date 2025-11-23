package com.example.safestep

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safestep.databinding.ActivityContactsBinding
import com.example.safestep.databinding.DialogAddContactBinding

class ContactsActivity : AppCompatActivity() {

    private lateinit var b: ActivityContactsBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnDeleteContact.setOnClickListener {
            adapter.deleteSelected()
        }

        b.btnEditContact.setOnClickListener {
            adapter.editSelected(this)
        }

        adapter = ContactAdapter(mutableListOf())

        b.recyclerContacts.layoutManager = LinearLayoutManager(this)
        b.recyclerContacts.adapter = adapter

        b.btnAddContact.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogBinding = DialogAddContactBinding.inflate(layoutInflater)

        // Create dialog
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialog.show()

        // Handle SAVE button inside the dialog
        dialogBinding.btnSave.setOnClickListener {
            val name = dialogBinding.etName.text.toString().trim()
            val phone = dialogBinding.etPhone.text.toString().trim()

            when {
                name.isEmpty() -> toast("Name required")
                phone.isEmpty() -> toast("Phone required")
                !phone.all { it.isDigit() } -> toast("Phone must be digits only")
                else -> {
                    adapter.addContact(Contact(name, phone))
                    dialog.dismiss()
                }
            }
        }
    }



    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
