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
import androidx.lifecycle.ViewModelProvider
import com.androidbox.countries.app.CountriesApp
import com.androidbox.countries.component.DaggerAppComponent
import com.androidbox.countries.viewmodel.ViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.create().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        val countriesAdapter = CountriesAdapter(this)
        val owner = this

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countriesAdapter

        searchView.queryHint = "Type something..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchCountry(newText)
                    .observe(owner, Observer { r -> if(r != null) countriesAdapter.setData(r) })
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchCountry(query)
                    .observe(owner, Observer { r -> if(r != null) countriesAdapter.setData(r) })
                return false
            }

        })
    }
}
