package com.example.contactsapp

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.contactsapp.data.ContactDao
import com.example.contactsapp.model.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    fun getContacts(): LiveData<List<Contact>> = contactDao.getContacts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }
}