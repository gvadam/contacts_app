package com.example.contactsapp

import android.content.Context
import com.example.contactsapp.data.ContactDao
import com.example.contactsapp.data.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideContactDao(database: ContactDatabase): ContactDao {
        return database.contactDao
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ContactDatabase {
        return ContactDatabase(context)
    }

    @Provides
    fun provideRepository(dao: ContactDao): ContactRepository {
        return ContactRepository(dao)
    }
}