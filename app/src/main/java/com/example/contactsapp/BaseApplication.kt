package com.example.contactsapp

import android.app.Application
import com.example.contactsapp.data.ContactDatabase

class BaseApplication: Application() {

    val database: ContactDatabase by lazy {
        ContactDatabase.getDatabase(this)
    }
}