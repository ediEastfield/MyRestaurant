package com.dicoding.myrestaurant

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.dicoding.myrestaurant.databinding.ActivityMainBinding
import com.dicoding.myrestaurant.favorite.FavoriteFragment
import com.dicoding.myrestaurant.home.HomeFragment
import com.dicoding.myrestaurant.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.contentMain.navView.setOnItemSelectedListener{item ->
            var fragment: Fragment? = null
            var title = getString(R.string.app_name)
            when (item.itemId) {
                R.id.nav_home -> {
                    fragment = HomeFragment()
                    title = getString(R.string.menu_home)
                }
                R.id.nav_favorite -> {
                    fragment = FavoriteFragment()
                    title = getString(R.string.menu_favorite)
                }
            }
            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            supportActionBar?.title = title

            true
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.menu_home)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String?): Boolean {
                Toast.makeText(this@MainActivity, search, Toast.LENGTH_SHORT).show()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, SearchFragment(search))
                    .commit()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }
}