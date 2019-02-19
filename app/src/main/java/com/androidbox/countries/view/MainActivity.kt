package com.androidbox.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidbox.countries.R
import com.androidbox.countries.adapter.CountriesAdapter
import com.androidbox.countries.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.androidbox.countries.app.CountriesApp
import com.androidbox.countries.component.DaggerAppComponent
import com.androidbox.countries.module.RoomModule
import com.androidbox.countries.viewmodel.ViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // I inject the dependencies with application scope to share the singletons
        (application as CountriesApp).appComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        val countriesAdapter = CountriesAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = countriesAdapter
        }

        viewModel.apply {
            //Observe changes on countries list
            countries().observe(this@MainActivity, Observer {
                    r -> if (r != null) countriesAdapter.setData(r)
            })
            // Update RecyclerView with the data saved in database
            searchCountry("")
        }

        initSearchView()
    }

    private fun initSearchView() {
        searchView.queryHint = "Search by country name"
        searchView.setIconifiedByDefault(true)
        searchView.isFocusable = true
        searchView.isIconified = false
        searchView.clearFocus()
        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchCountry(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchCountry(query)
                return false
            }

        })
    }
}
