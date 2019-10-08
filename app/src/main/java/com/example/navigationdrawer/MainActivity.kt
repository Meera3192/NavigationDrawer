package com.example.navigationdrawer

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.navigationdrawer.category.CategoryFragment
import com.example.navigationdrawer.contact.ContactFragment
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        if(savedInstanceState == null) {
            toolbar.title = getString(R.string.menu_contactlist)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ContactListFragment()).commit()
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        // Optional: if we want to expand SearchView from icon to edittext view
        //searchItem?.expandActionView()
        val searchView = searchItem?.actionView as SearchView

        // Call Api on text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchString: String?): Boolean {
                //fetchDataFromServer(searchString.toString())
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_category -> {
                toolbar.title = getString(R.string.menu_addcategory)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,CategoryFragment()).commit()
            }
            R.id.nav_contact -> {
                toolbar.title = getString(R.string.menu_addContact)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ContactFragment()).commit()
            }
            R.id.nav_contactlist-> {
                toolbar.title = getString(R.string.menu_contactlist)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,ContactListFragment()).commit()
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
