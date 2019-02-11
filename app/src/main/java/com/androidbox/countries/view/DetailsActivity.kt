package com.androidbox.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.androidbox.countries.R
import com.androidbox.countries.databinding.ActivityDetailsBinding
import com.androidbox.countries.viewmodel.MainViewModel

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        //val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        //viewModel.selectCountry(intent.getStringExtra("SelectedCountry").toInt())
        //binding.viewModel = viewModel.selectedCountry.value
    }
}
