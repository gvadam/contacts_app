package com.example.contactsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contactsTable")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String?,
    val number: String?
)