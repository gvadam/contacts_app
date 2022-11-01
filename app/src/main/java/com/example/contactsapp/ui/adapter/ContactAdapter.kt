package com.example.contactsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.ListItemBinding
import com.example.contactsapp.model.Contact

class ContactAdapter(
    val context: Context
): ListAdapter<Contact, ContactAdapter.contactViewHolder>(DiffCallback) {

    private val allContacts = ArrayList<Contact>()

    class contactViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(contact: Contact) {
            binding.name.text = contact.name
            binding.number.text = contact.number
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return contactViewHolder(
            ListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: contactViewHolder, position: Int) {
        holder.bind(allContacts.get(position))
    }

    fun updateList(newList: List<Contact>){
        allContacts.clear()
        allContacts.addAll(newList)
        notifyDataSetChanged()
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return allContacts.size
    }

}
