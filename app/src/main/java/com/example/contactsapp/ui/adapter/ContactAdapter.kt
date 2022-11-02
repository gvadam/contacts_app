package com.example.contactsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ListItemBinding
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.viewmodel.ContactViewModel


class ContactAdapter(
    var items: List<Contact>,
    private val viewModel: ContactViewModel
): RecyclerView.Adapter<ContactAdapter.contactViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return contactViewHolder(view)
    }

    override fun onBindViewHolder(holder: contactViewHolder, position: Int) {
        val contact = items[position]
        holder.itemView.tvName.text = contact.name
        holder.itemView.tvNumber.text = "${contact.number}"
    }

    inner class contactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}
