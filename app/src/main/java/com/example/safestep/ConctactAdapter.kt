package com.example.safestep

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.safestep.databinding.DialogAddContactBinding
import com.example.safestep.databinding.ItemContactBinding

/**
 * Manages the display and interaction of a list of Contact objects
 * Handles creating views for each contact, binding data to those views,
 * and managing user interactions (like selection, editing, and deletion).
 *
 * @param contacts A mutable list of Contact objects to be displayed.
 */
class ContactAdapter(
    private val contacts: MutableList<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    // Tracks the position of the currently selected item
    var selectedPosition: Int = -1

    /**
     * ViewHolder for a contact item. Holds the view for the item layout,
     * which allows easy access to the views.
     *
     * @param b The view binding for the layout.
     */
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

    /**
     * Binds the data from a Contact object to the views in the ViewHolder.
     * Also sets up click listeners for item selection and the context menu.
     */
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val c = contacts[position]

        holder.b.tvName.text = c.name
        holder.b.tvPhone.text = c.phone

        // Handles item selection
        holder.itemView.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged() // Re-bind all views to update selection highlights
        }

        // Highlight the selected item
        holder.itemView.alpha = if (position == selectedPosition) 0.6f else 1f

        // Set up the three-dot menu for editing and deleting
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

    /**
     * Deletes the currently selected contact from the list.
     */
    fun deleteSelected() {
        if (selectedPosition >= 0 && selectedPosition < contacts.size) {
            contacts.removeAt(selectedPosition)
            notifyItemRemoved(selectedPosition)
            selectedPosition = -1 // Reset selection
        }
    }

    /**
     * Opens a dialog to edit the current selected contact.
     * @param context The context needed to create the AlertDialog.
     */
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

    /**
     * Adds a new contact to the end of the list.
     * @param contact The [Contact] to add.
     */
    fun addContact(contact: Contact) {
        contacts.add(contact)
        notifyItemInserted(contacts.size - 1)
    }

    /**
     * Helper to delete a contact.
     */
    private fun deleteContact(position: Int) {
        contacts.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Helper to open an edit for a contact.
     */
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
