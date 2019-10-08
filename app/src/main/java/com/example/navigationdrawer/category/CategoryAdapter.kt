package com.example.navigationdrawer.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.RowCategoryBinding
import kotlinx.android.synthetic.main.row_category.view.*
import java.security.AlgorithmConstraints
import com.example.navigationdrawer.MainActivity

/**
 * Created by Meera
 * Date : 08-10-2019.
 * Package_Name : com.example.navigationdrawer.category
 * Class_Name :
 * Description :
 */


class CategoryAdapter(categoryList: ArrayList<Category>?) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(),Filterable {


    var categoryList: ArrayList<Category>?
    var categoryListFull: ArrayList<Category>? = null

    init {
        this.categoryList = categoryList
        this.categoryListFull = ArrayList<Category>()
        this.categoryListFull!!.addAll(categoryList!!)

    }

    override fun getItemCount(): Int = categoryList!!.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (holder != null) {
            holder.xmlbinding?.category = categoryList?.get(position)
            holder.itemView.imgEdit.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    itemEditClickListNer?.OnEditClickListner(categoryList?.get(position)!!)
                }
            })
            holder.itemView.imgDelete.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    itemDeleteClickListNer?.OnDeleteClickListner(categoryList?.get(position)!!)
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

    // Edit
    var itemEditClickListNer: ItemEditClickListNer? = null

    interface ItemEditClickListNer
    {
        fun OnEditClickListner(category : Category)
    }

    fun setOnEditClickListner(itemEditClickListNer: ItemEditClickListNer?)
    {
        this.itemEditClickListNer = itemEditClickListNer
    }

    // Delete
    var itemDeleteClickListNer: ItemDeleteClickListNer? = null

    interface ItemDeleteClickListNer
    {
        fun OnDeleteClickListner(category : Category)
    }

    fun setOnDeleteClickListner(itemDeleteClickListNer: ItemDeleteClickListNer?)
    {
        this.itemDeleteClickListNer = itemDeleteClickListNer
    }

    //Filter
    override fun getFilter(): Filter {

        return object : Filter(){
            override fun performFiltering(constraints: CharSequence?): FilterResults {
                var filterList = ArrayList<Category>()
                if(constraints.toString().isNullOrEmpty())
                {
                    filterList.addAll(categoryListFull!!)
                }
                else
                {
                    var filterPattern = constraints.toString().toLowerCase().trim()
                    for(category in categoryListFull!!)
                    {
                        if(category.name!!.toLowerCase().contains(filterPattern))
                        {
                            filterList.add(category)
                        }
                    }
                }
                var result = FilterResults()
                result.values = filterList

                return result
            }

            override fun publishResults(charSequance: CharSequence?, filterResult: FilterResults?) {
                categoryList?.clear()
                categoryList?.addAll(filterResult!!.values as ArrayList<Category>)
                //categoryListFull =filterResult!!.values as ArrayList<Category>
                notifyDataSetChanged()
            }
        }
    }

}