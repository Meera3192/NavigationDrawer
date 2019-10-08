package com.example.navigationdrawer.category

import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
    lateinit var adapter : CategoryAdapter

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
                    var id = edtCategory.getTag() as Int
                    categoryViewModel.updateCategory(Category(id,edtCategory.text.toString()))
                    edtCategory.text = null
                    btnSave.text = "Save"
                }
                else {
                    categoryViewModel.insertCategory(Category(0,view?.edtCategory?.text.toString()))
                    edtCategory.text = null
                }
            }
        })
    }

    private fun getCategory(view: View) {
        categoryViewModel.getAllCategoryData().observe(this!!.activity!!, object : Observer<ArrayList<Category>> {
            override fun onChanged(categoryList: ArrayList<Category>?) {
                Toast.makeText(activity,categoryList.toString(),Toast.LENGTH_LONG).show()
                bindData(view,categoryList)
            }
        })
    }

    private fun bindData(
        view: View,
        categoryList: ArrayList<Category>?
    ) {
        view.rvCategory.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        adapter = CategoryAdapter(categoryList)
        view.rvCategory.adapter = adapter
        adapter.setOnEditClickListner(object : CategoryAdapter.ItemEditClickListNer{
            override fun OnEditClickListner(category: Category) {
                edtCategory.setText(category.name)
                btnSave.text = "Update"
                edtCategory.setTag(category.id)
            }

        })

        adapter.setOnDeleteClickListner(object : CategoryAdapter.ItemDeleteClickListNer{
            override fun OnDeleteClickListner(category: Category) {
                categoryViewModel.deleteCategory(category)
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        //visible off
        //searchItem.setVisible(false)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchString: String?): Boolean {
                adapter.filter.filter(searchString)
                return false
            }

        })
        super.onCreateOptionsMenu(menu,inflater)
    }





}
