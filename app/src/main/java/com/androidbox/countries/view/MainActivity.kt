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
import android.view.Menu
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val countriesAdapter = CountriesAdapter(this)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //viewModel = DaggerAppComponent.inject()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countriesAdapter

        viewModel.countriesLiveData.observe(this, Observer { r -> countriesAdapter.setData(r) })

        searchView.queryHint = "Type something..."
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
