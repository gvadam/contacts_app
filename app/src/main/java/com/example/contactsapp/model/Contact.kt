package com.example.contactsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contactsTable")
data class Contact (
    var name: String,
    var number: String
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
}