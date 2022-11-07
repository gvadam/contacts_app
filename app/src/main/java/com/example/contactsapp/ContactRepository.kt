package com.example.contactsapp


import com.example.contactsapp.data.ContactDao
import com.example.contactsapp.model.Contact
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao) {

    fun getAllItem() = contactDao.getAllItems()

     fun insert(contact: Contact) = contactDao.insert(contact)
     fun delete(contact: Contact) = contactDao.delete(contact)
}