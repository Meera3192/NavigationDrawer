package com.example.navigationdrawer.contact

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.contact
 * Class_Name :
 * Description :
 */
@Dao
interface ContactDAO
{
    // Using this method we Insert data in User table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserData(user : Contact)

    // Using this method we get all record from user table
    @Query("SELECT * from Contact")
    fun getAllData() : LiveData<List<Contact>>

}