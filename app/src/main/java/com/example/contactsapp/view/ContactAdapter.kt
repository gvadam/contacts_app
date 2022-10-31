package com.example.contactsapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R
import com.example.contactsapp.model.Contact

class ContactAdapter(
    val context: Context,
    var contactList: ArrayList<Contact>,
): RecyclerView.Adapter<ContactAdapter.contactViewHolder>() {

    inner class contactViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.name)
        val number = v.findViewById<TextView>(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item, parent, false)
        return contactViewHolder(v)
    }

    override fun onBindViewHolder(holder: contactViewHolder, position: Int) {
        val newList = contactList[position]
        holder.name.text = newList.name
        holder.number.text = newList.number
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return contactList.size
    }
}