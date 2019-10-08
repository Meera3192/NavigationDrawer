package com.example.navigationdrawer.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.RowCategoryBinding
import kotlinx.android.synthetic.main.row_category.view.*


/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.category
 * Class_Name :
 * Description :
 */


class CategoryAdapter(CategoryList: List<Category>?) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var CategoryList: List<Category>?

    init {
        this.CategoryList = CategoryList
    }

    override fun getItemCount(): Int = CategoryList!!.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (holder != null) {
            holder.xmlbinding?.category = CategoryList?.get(position)
            holder.itemView.imgEdit.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    itemEditClickListNer?.OnEditClickListner(CategoryList?.get(position)!!)
                }
            })
            holder.itemView.imgDelete.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    itemDeleteClickListNer?.OnDeleteClickListner(CategoryList?.get(position)!!)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_category, parent, false)
        return CategoryViewHolder(view)
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var xmlbinding: RowCategoryBinding?

        init {
            xmlbinding = DataBindingUtil.bind(itemView.rootView)
        }
    }

    var itemEditClickListNer: ItemEditClickListNer? = null

    interface ItemEditClickListNer
    {
        fun OnEditClickListner(category : Category)
    }

    fun setOnEditClickListner(itemEditClickListNer: ItemEditClickListNer?)
    {
        this.itemEditClickListNer = itemEditClickListNer
    }

    var itemDeleteClickListNer: ItemDeleteClickListNer? = null

    interface ItemDeleteClickListNer
    {
        fun OnDeleteClickListner(category : Category)
    }

    fun setOnDeleteClickListner(itemDeleteClickListNer: ItemDeleteClickListNer?)
    {
        this.itemDeleteClickListNer = itemDeleteClickListNer
    }


}