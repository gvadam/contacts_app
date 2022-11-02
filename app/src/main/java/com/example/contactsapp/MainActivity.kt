package com.example.contactsapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
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
import com.example.contactsapp.ui.contactlist.AddContactItemDialog
import com.example.contactsapp.ui.contactlist.AddDialogListener
import com.example.contactsapp.ui.viewmodel.ContactViewModel
import com.example.contactsapp.ui.viewmodel.ContactViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: ActivityMainBinding
    private val factory: ContactViewModelFactory by instance()
    lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        val adapter = ContactAdapter(listOf(), viewModel)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        viewModel.getContacts().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.addFAB.setOnClickListener {
            AddContactItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: Contact) {
                        viewModel.insert(item)
                    }
                }).show()
        }
    }


}