package com.example.contactsapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import com.example.contactsapp.BaseApplication
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.databinding.ActivityMainBinding
import com.example.contactsapp.databinding.AddItemBinding
import com.example.contactsapp.databinding.ListItemBinding
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.adapter.ContactAdapter
import com.example.contactsapp.ui.viewmodel.ContactViewModel
import com.example.contactsapp.ui.viewmodel.ContactViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ListItemBinding
    private lateinit var binding3: AddItemBinding
    lateinit var viewModel: ContactViewModel
    lateinit var contactAdapter: ContactAdapter
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        contactAdapter = ContactAdapter(this)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        //binding.recycler.adapter = contactAdapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(ContactViewModel::class.java)

        //viewModel.getContacts().observe(viewLifecycleOwner) {
            //contactAdapter.submitList(it)
        //}
        viewModel.getContacts().observe(this) {
            contactAdapter.submitList(it)
        }
        binding.apply {
            recycler.adapter = contactAdapter
            binding.addFAB.setOnClickListener {
                addInfo()
            }
        }
        binding.addFAB.setOnClickListener { addInfo() }
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)
        val contactName = binding3.nameEdit.text.toString()
        val contactNumber = binding3.numberEdit.text.toString()
        /**
        viewModel.getContact(contactNumber).observe(viewLifecycleOwner){
            contact = it
            bindContact(contact)
        }*/
        viewModel.getContact(contactNumber).observe(this) {
            contact = it
            bindContact(contact)
        }
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") {
            dialog,_ ->
            viewModel.addContact(contactName, contactNumber)
            Toast.makeText(this, "Successfully Added Contact!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") {
            dialog,_ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

    private fun deleteContact(contact: Contact) {
        viewModel.deleteContact(contact)
    }

    private fun updateContact() {
        if (isValidEntry()) {
            viewModel.updateContact(
                name = binding3.nameEdit.text.toString(),
                number = binding3.numberEdit.text.toString()
            )
        }
    }

    private fun bindContact(contact: Contact) {
        binding2.apply{
            name.setText(contact.name, TextView.BufferType.SPANNABLE)
            number.setText(contact.number, TextView.BufferType.SPANNABLE)


        }

    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding2.name.text.toString(),
        binding2.number.text.toString()
    )




}