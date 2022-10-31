package com.example.contactsapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.contactsapp.data.ContactDao
import com.example.contactsapp.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(
    private val contactDao: ContactDao
): ViewModel() {

    fun getContacts(): LiveData<List<Contact>> = contactDao.getContacts().asLiveData()

    fun getContact(id: Long) : LiveData<Contact> = contactDao.getContact(id).asLiveData()

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


}