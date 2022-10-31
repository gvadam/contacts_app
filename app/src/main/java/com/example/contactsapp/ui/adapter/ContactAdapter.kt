package com.example.contactsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.ListItemBinding
import com.example.contactsapp.model.Contact

class ContactAdapter(
    val context: Context
): RecyclerView.Adapter<ContactAdapter.contactViewHolder>() {

    private val allContacts = ArrayList<Contact>()

    class contactViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(contact: Contact) {
            binding.name.text = contact.name
            binding.number.text = contact.number
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
