package com.example.contactsapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.ui.viewmodel.ContactViewModel


@Suppress("UNCHECKED_CAST")
class ContactViewModelFactory(private val repository: ContactRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactViewModel(repository) as T
    }
}