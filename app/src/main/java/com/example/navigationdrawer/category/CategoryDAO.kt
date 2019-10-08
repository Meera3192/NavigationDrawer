package com.example.navigationdrawer.category

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.category
 * Class_Name :
 * Description :
 */
@Dao
interface CategoryDAO
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun InsertCategory(category : Category)

    @Update
    fun UpdateCategory(category : Category)

    @Delete
    fun DeleteCategory(category : Category)

    @Query("SELECT * FROM Category ORDER BY name DESC")
    fun getAllData(): LiveData<List<Category>>
}