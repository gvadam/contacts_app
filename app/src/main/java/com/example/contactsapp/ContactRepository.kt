package com.example.contactsapp


import com.example.contactsapp.data.ContactDao
import com.example.contactsapp.model.Contact
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao) {

    fun getAllItem() = contactDao.getAllItems()

    //suspend fun updateItem(contact: Contact) = contactDao.upsert(contact)
     fun insert(contact: Contact) = contactDao.upsert(contact)
     fun delete(contact: Contact) = contactDao.delete(contact)
    //suspend fun insertItems(contact: List<Contact>) = contactDao.insertItems(contact)
}