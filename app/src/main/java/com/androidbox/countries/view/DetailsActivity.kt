package com.androidbox.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ahmadrosid.svgloader.SvgLoader
import com.androidbox.countries.R
import com.androidbox.countries.component.DaggerAppComponent
import com.androidbox.countries.databinding.ActivityDetailsBinding
import com.androidbox.countries.module.RoomModule
import com.androidbox.countries.viewmodel.MainViewModel
import com.androidbox.countries.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_country.view.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        val binding: ActivityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        DaggerAppComponent
            .builder()
            .roomModule(RoomModule(application))
            .build()
            .inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        binding.lifecycleOwner = this

        val country = viewModel.getCountry(intent.getIntExtra("SelectedCountry", -1))
        binding.item = country

        fab.setOnClickListener {
            viewModel.saveCountry(country)
        }

        viewModel.status.observe(this, Observer { message ->
            Snackbar.make(fab, message, Snackbar.LENGTH_SHORT).show()
            fab.hide()
        })
    }
}
