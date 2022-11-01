package com.example.contactsapp.data

import androidx.room.*
import com.example.contactsapp.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("Select * from contactsTable")
    fun getContacts(): Flow<List<Contact>>

    @Query("Select * from contactsTable where number = :number")
    fun getContact(number: String): Flow<Contact>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact :Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Update
    suspend fun update(contact: Contact)
}