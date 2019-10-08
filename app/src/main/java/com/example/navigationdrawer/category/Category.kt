package com.example.navigationdrawer.category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.category
 * Class_Name :
 * Description :
 */
@Entity(indices = arrayOf(Index(value = ["name"], unique = true)))
data class Category(
    val name : String?
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}