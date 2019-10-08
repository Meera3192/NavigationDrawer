package com.example.navigationdrawer.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.category
 * Class_Name :
 * Description :
 */
class CategoryViewModel(application: Application) :  AndroidViewModel(application) {
    var mCategoryList: LiveData<List<Category>>
    var CategoryDAO: CategoryDAO
    var CategoryRepository: CategoryRepository

    /**
     *  Initialize CategoryDAO & CategoryRepository class instance.
     *  Get reference to CategoryDAO from CategoryRoomDatabase.
     *  Pass CategoryDAO instance into CategoryRepository class.
     */
    init {
        CategoryDAO = CategoryRoomDatabase.getInstance(application).categoryDAO()
        CategoryRepository = CategoryRepository(CategoryDAO)
        mCategoryList = CategoryRepository.allData
    }


    /**
     * Using CategoryRepository class method perform Insert()
     */
    fun insertCategoryData(Category: Category) {
        CategoryRepository.inserCategoryData(Category)
    }

    /**
     * Using CategoryRepository class method perform Update()
     */
    fun updateCategoryData(Category: Category) {
        CategoryRepository.updateCategoryData(Category)
    }

    /**
     * Using CategoryRepository class method perform Delete()
     */
    fun deleteCategoryData(Category: Category) {
        CategoryRepository.deleteCategoryData(Category)
    }

    /**
     * LiveData gives us updated Categorys when they change.
     */
    fun getAllCategoryData() : LiveData<List<Category>>
    {
        return mCategoryList
    }
}