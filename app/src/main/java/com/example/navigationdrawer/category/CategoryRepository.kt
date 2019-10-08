package com.example.navigationdrawer.category

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.category
 * Class_Name :
 * Description :
 */
class CategoryRepository(CategoryDAO: CategoryDAO) {

    var categoryDAO : CategoryDAO

    init {
        this.categoryDAO = CategoryDAO
    }

    // Assign All record of Category
    var allData : LiveData<List<Category>> = categoryDAO.getAllData()
    val TAG = "CategoryRepository"
    lateinit var mCategoryList : MutableLiveData<Category>


    /**
     *  Use this methode we execute AsyncTask for insert data
     */
    fun inserCategoryData(category: Category) {
        InsertCategoryData(categoryDAO).execute(category)
    }

    /**
     * Insert Data process run in background
     */
    class InsertCategoryData(categoryDAO: CategoryDAO) : AsyncTask<Category, Void, Void>() {
        var categoryDAO:CategoryDAO
        init {
            this.categoryDAO = categoryDAO
        }
        override fun doInBackground(vararg category: Category?): Void? {
            categoryDAO.InsertCategory(category[0]!!)
            return null
        }

    }

    /**
     *  Use this methode we execute AsyncTask for Update data
     */
    fun updateCategoryData(category: Category) {
        UpdateCategoryData(categoryDAO).execute(category)
    }

    /**
     * Update Data process run in background
     */
    class UpdateCategoryData(categoryDAO: CategoryDAO) : AsyncTask<Category, Void, Void>() {
        var categoryDAO:CategoryDAO
        init {
            this.categoryDAO = categoryDAO
        }

        override fun doInBackground(vararg category: Category?): Void? {
            categoryDAO.UpdateCategory(category[0]!!)
            return null
        }
    }

    /**
     *  Use this methode we execute AsyncTask for delete data
     */
    fun deleteCategoryData(category: Category) {
        InsertCategoryData(categoryDAO).execute(category)
    }

    /**
     * Delete Data process run in background
     */
    class DeleteCategoryData(categoryDAO: CategoryDAO) : AsyncTask<Category, Void, Void>() {
        var categoryDAO:CategoryDAO
        init {
            this.categoryDAO = categoryDAO
        }
        override fun doInBackground(vararg category: Category?): Void? {
            categoryDAO.DeleteCategory(category[0]!!)
            return null
        }

    }

}