package com.example.contactsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.ListItemBinding
import com.example.contactsapp.model.Contact

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.contactViewHolder>() {

    var allItems: List<Contact> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var listener: ItemListener? = null

    override fun getItemCount() = allItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return contactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: contactViewHolder, position: Int) {
        holder.bind(allItems[position])
    }

    inner class contactViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact){
            binding.apply {
                tvName.text = contact.name
                tvNumber.text = contact.number

                deleteBtn.setOnClickListener {
                    listener?.onDeleteClicked(contact)
                }
            }
        }
    }

}
interface ItemListener {
    fun onAddClicked(contact: Contact)
    fun onSubtractClicked(contact: Contact)
    fun onDeleteClicked(contact: Contact)
}
