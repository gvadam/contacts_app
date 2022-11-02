package com.example.contactsapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.contactsapp.ContactRepository
import com.example.contactsapp.data.ContactDao
import com.example.contactsapp.data.ContactDatabase
import com.example.contactsapp.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContactViewModel(private val repository: ContactRepository): ViewModel()  {
    fun getContacts() = repository.getContacts()
    fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }
}

class ContactViewModelFactory (
    private val repository: ContactRepository
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
/*
class ContactViewModel(
    private val contactDao: ContactDao
): ViewModel() {

    fun getContacts(): LiveData<List<Contact>> = contactDao.getContacts().asLiveData()

    fun getContact(number: String) : LiveData<Contact> = contactDao.getContact(number).asLiveData()

    fun addContact(
        name: String,
        number: String
    ) {
        val contact = Contact(
            name = name,
            number = number
        )

        viewModelScope.launch(Dispatchers.IO) {
            contactDao.insert(contact)
        }
    }

    fun updateContact(
        name: String,
        number: String
    ) {
        val contact = Contact(
            name = name,
            number = number
        )
        viewModelScope.launch(Dispatchers.IO) {
            contactDao.update(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactDao.delete(contact)
        }
    }

    fun isValidEntry(name: String, number: String): Boolean {
        return name.isNotBlank() && number.isNotBlank()
    }
}

class ContactViewModelFactory (
    private val contactDao: ContactDao
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(contactDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}*/