package com.example.navigationdrawer.contact

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.navigationdrawer.category.Category


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.contact
 * Class_Name :
 * Description :
 */
@Entity(indices = arrayOf(Index(value = ["mobilenumber"], unique = true)))
data class Contact(
    val name : String?,
    val mobilenumber : String?,
    val category : String?
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}