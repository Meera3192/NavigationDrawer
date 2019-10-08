package com.example.navigationdrawer.category

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentCategoryBinding
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category.view.*
import kotlinx.android.synthetic.main.fragment_category.view.edtCategory

class CategoryFragment : Fragment() {

    lateinit var categoryFragmentBinding : FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        categoryFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_category,null,false)
        var view = categoryFragmentBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)

        initialize(view)
    }

    private fun initialize(view: View) {
        getCategory(view)
        view.btnSave.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                if(btnSave.text ==  "Update")
                {
                    categoryViewModel.updateCategory(Category(view?.edtCategory?.text.toString()))
                    edtCategory.text = null
                    btnSave.text = "Save"
                }
                else {
                    categoryViewModel.insertCategory(Category(view?.edtCategory?.text.toString()))
                    edtCategory.text = null
                }
            }
        })
    }

    private fun getCategory(view: View) {
        categoryViewModel.getAllCategoryData().observe(this!!.activity!!, object : Observer<List<Category>> {
            override fun onChanged(categoryList: List<Category>?) {
                Toast.makeText(activity,categoryList.toString(),Toast.LENGTH_LONG).show()
                bindData(view,categoryList)
            }
        })
    }

    private fun bindData(
        view: View,
        categoryList: List<Category>?
    ) {
        view.rvCategory.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        var adapter = CategoryAdapter(categoryList)
        view.rvCategory.adapter = adapter
        adapter.setOnEditClickListner(object : CategoryAdapter.ItemEditClickListNer{
            override fun OnEditClickListner(category: Category) {
                edtCategory.setText(category.name)
                btnSave.text = "Update"
            }

        })

        adapter.setOnDeleteClickListner(object : CategoryAdapter.ItemDeleteClickListNer{
            override fun OnDeleteClickListner(category: Category) {
                categoryViewModel.deleteCategory(category)
            }
        })

    }


}
