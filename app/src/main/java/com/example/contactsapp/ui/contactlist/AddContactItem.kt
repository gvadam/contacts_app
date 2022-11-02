package com.example.contactsapp.ui.contactlist

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.contactsapp.databinding.AddItemBinding
import com.example.contactsapp.model.Contact
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.NumberFormatException

typealias OnOkayClickListener = (contact: Contact) -> Unit

class AddContactItem: BottomSheetDialogFragment() {

    companion object {
        const val TAG = "AddContactItem"
        val INSTANCE: AddContactItem by lazy { AddContactItem() }
    }

    var onOkayClickListener: OnOkayClickListener? = null
    private lateinit var contact: Contact

    private lateinit var binding: AddItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
    }

    private fun initActions() {
        binding.apply {

            addButton.setOnClickListener {

                val name = editName.text.toString()
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val number: String = try {
                    editNumber.text.toString()
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Please enter a number", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                    return@setOnClickListener
                }

                contact = Contact(
                    name = name,
                    number = number
                )
                onOkayClickListener?.let {
                    it(contact)
                }

                if (isVisible) dismiss()
            }

        }
    }

    fun show(fragmentManager: FragmentManager, onOkayClickListener: OnOkayClickListener) {
        this.onOkayClickListener = onOkayClickListener
        super.show(fragmentManager, TAG)
    }

}