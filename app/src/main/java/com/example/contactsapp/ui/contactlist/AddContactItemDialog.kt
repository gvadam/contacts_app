package com.example.contactsapp.ui.contactlist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.contactsapp.R
import com.example.contactsapp.databinding.AddItemBinding
import com.example.contactsapp.model.Contact


class AddContactItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    private lateinit var binding: AddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_item)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etNumber.text.toString()
            if(name.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = Contact(name, amount)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}