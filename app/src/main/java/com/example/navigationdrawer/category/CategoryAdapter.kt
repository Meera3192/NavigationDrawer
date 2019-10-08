package com.example.navigationdrawer.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.RowCategoryBinding


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.category
 * Class_Name :
 * Description :
 */


class CategoryAdapter(CategoryList: List<Category>?) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()
{
    var CategoryList: List<Category>?

    init {
        this.CategoryList = CategoryList
    }

    /**
     * Use this method for passing view in viewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_category,parent,false)
        return CategoryViewHolder(view)
    }

    /**
     * This class is used to cache the view objects in order to save memory.
     */
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var xmlbinding : RowCategoryBinding?

        init {
            xmlbinding = DataBindingUtil.bind(itemView.rootView)
        }
    }

    override fun getItemCount(): Int = CategoryList!!.size

    /**
     * This method used for bind view according position.
     */
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if(holder != null)
        {
            holder.xmlbinding?.category = CategoryList?.get(position)
        }
    }

    /**
     * Using this method we update recyclerview by new row.
     */
    fun setData(newData: List<Category>?) {
        this.CategoryList = newData
        notifyDataSetChanged()
    }

}