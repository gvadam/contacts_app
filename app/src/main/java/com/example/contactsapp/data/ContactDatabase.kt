package com.example.contactsapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapp.model.Contact
import com.example.contactsapp.data.ContactDao

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {

    abstract val contactDao: ContactDao

    companion object {
        private val DATABASE_NAME = "contact_database.db"
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        operator fun invoke(context: Context): ContactDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    DATABASE_NAME
                ).build().also {
                    INSTANCE = it
                }
            }

    }
}