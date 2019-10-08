package com.example.navigationdrawer.contact

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.contact
 * Class_Name :
 * Description :
 */
@Database(entities = [Contact::class],version = 1)
abstract class ContactRoomDatabase : RoomDatabase()
{
    abstract fun contactDAO() : ContactDAO

    /**
     * Create Singleton class for multiple instances of database opening at the same time.
     */
    companion object
    {
        private var instance : ContactRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : ContactRoomDatabase
        {
            if(instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactRoomDatabase::class.java,
                    "Contact_Database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as ContactRoomDatabase
        }
    }

}