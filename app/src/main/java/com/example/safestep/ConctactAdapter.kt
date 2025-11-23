package com.example.safestep

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.safestep.databinding.DialogAddContactBinding
import com.example.safestep.databinding.ItemContactBinding
import android.content.Context


class ContactAdapter(
    private val contacts: MutableList<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    var selectedPosition: Int = -1


    inner class ContactViewHolder(val b: ItemContactBinding)
        : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val c = contacts[position]

        holder.b.tvName.text = c.name
        holder.b.tvPhone.text = c.phone

        holder.itemView.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
        }
        // Highlight selected item
        holder.itemView.alpha = if (position == selectedPosition) 0.6f else 1f


        // Three-dot menu
        holder.b.btnMenu.setOnClickListener {
            val popup = PopupMenu(holder.itemView.context, holder.b.btnMenu)
            popup.menu.add("Edit")
            popup.menu.add("Delete")

            popup.setOnMenuItemClickListener { item ->
                when (item.title) {
                    "Edit" -> editContact(holder.adapterPosition, holder)
                    "Delete" -> deleteContact(holder.adapterPosition)
                }
                true
            }
            popup.show()
        }
    }
    fun deleteSelected() {
        if (selectedPosition >= 0 && selectedPosition < contacts.size) {
            contacts.removeAt(selectedPosition)
            notifyItemRemoved(selectedPosition)
            selectedPosition = -1
        }
    }

    fun editSelected(context: Context) {
        if (selectedPosition < 0) return

        val contact = contacts[selectedPosition]
        val dialogBinding = DialogAddContactBinding.inflate(LayoutInflater.from(context))

        dialogBinding.etName.setText(contact.name)
        dialogBinding.etPhone.setText(contact.phone)

        AlertDialog.Builder(context)
            .setTitle("Edit Contact")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val newName = dialogBinding.etName.text.toString().trim()
                val newPhone = dialogBinding.etPhone.text.toString().trim()

                contacts[selectedPosition] = Contact(newName, newPhone)
                notifyItemChanged(selectedPosition)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    override fun getItemCount(): Int = contacts.size

    fun addContact(contact: Contact) {
        contacts.add(contact)
        notifyItemInserted(contacts.size - 1)
    }

    private fun deleteContact(position: Int) {
        contacts.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun editContact(position: Int, holder: ContactViewHolder) {
        val ctx = holder.itemView.context
        val dialogBinding = DialogAddContactBinding.inflate(LayoutInflater.from(ctx))

        dialogBinding.etName.setText(contacts[position].name)
        dialogBinding.etPhone.setText(contacts[position].phone)

        AlertDialog.Builder(ctx)
            .setTitle("Edit Contact")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val newName = dialogBinding.etName.text.toString().trim()
                val newPhone = dialogBinding.etPhone.text.toString().trim()

                contacts[position] = Contact(newName, newPhone)
                notifyItemChanged(position)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
