package com.example.contactsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.databinding.ActivityMainBinding
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.adapter.ContactAdapter
import com.example.contactsapp.ui.adapter.ItemListener
import com.example.contactsapp.ui.contactlist.AddContactItem
import com.example.contactsapp.ui.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ContactViewModel by viewModels()
    private val adapter = ContactAdapter()

    private val addBottomSheet = AddContactItem.INSTANCE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initViews()
        initDatabase()
    }

    private fun initDatabase() {
        viewModel.getAllItems().observe(this) {
            adapter.allItems = it
        }
    }

    private fun initViews() {
        binding.apply {
            recycler.adapter = this@MainActivity.adapter
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            addFAB.setOnClickListener {
                fabClicked()
            }

        }
        adapter.listener = this
    }

    private fun fabClicked() {
        addBottomSheet.show(supportFragmentManager) {
            viewModel.addItem(it)
        }
    }

    override fun onAddClicked(contact: Contact) {
         Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show()
    }

    override fun onSubtractClicked(contact: Contact) {
         Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClicked(contact: Contact) {
         viewModel.deleteItem(contact)
    }


}