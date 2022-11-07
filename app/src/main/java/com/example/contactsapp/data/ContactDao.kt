package com.example.contactsapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contactsapp.model.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("SELECT * FROM contactsTable")
    fun getAllItems(): LiveData<List<Contact>>

    @Insert
    fun insertItems(contacts: List<Contact>)

}