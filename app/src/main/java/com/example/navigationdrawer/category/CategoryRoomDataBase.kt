package com.example.navigationdrawer.category

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.navigationdrawer.contact.Contact
import com.example.navigationdrawer.contact.ContactDAO
import com.example.navigationdrawer.contact.ContactRoomDatabase


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.category
 * Class_Name :
 * Description :
 */
@Database(entities = [Category::class],version = 1)
abstract class CategoryRoomDatabase : RoomDatabase()
{
    abstract fun categoryDAO() : CategoryDAO

    /**
     * Create Singleton class for multiple instances of database opening at the same time.
     */
    companion object
    {
        private var instance : CategoryRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : CategoryRoomDatabase
        {
            if(instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CategoryRoomDatabase::class.java,
                    "Category_Database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as CategoryRoomDatabase
        }
    }

}