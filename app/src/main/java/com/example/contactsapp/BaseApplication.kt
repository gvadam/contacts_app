package com.example.contactsapp

import android.app.Application
import com.example.contactsapp.ContactRepository
import com.example.contactsapp.data.ContactDatabase
import com.example.contactsapp.ui.viewmodel.ContactViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class BaseApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))
        bind() from singleton { ContactDatabase(instance()) }
        bind() from singleton {
            ContactRepository(
                instance()
            )
        }
        bind() from provider {
            ContactViewModelFactory(
                instance()
            )
        }
    }
}