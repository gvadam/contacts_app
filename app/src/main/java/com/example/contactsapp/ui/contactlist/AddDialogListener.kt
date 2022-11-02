package com.example.contactsapp.ui.contactlist

import com.example.contactsapp.model.Contact

interface AddDialogListener {
    fun onAddButtonClicked(contact: Contact)
}