package com.example.contactsapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.contactsapp.ContactRepository
import com.example.contactsapp.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val repository: ContactRepository): ViewModel()  {

    fun getAllItems() = repository.getAllItem()

    fun deleteItem(contact: Contact) {
        viewModelScope.launch(IO) {
            repository.delete(contact)
        }
    }

    fun addItem(contact: Contact) {
        viewModelScope.launch(IO) {
            repository.insert(contact)
        }
    }
}

