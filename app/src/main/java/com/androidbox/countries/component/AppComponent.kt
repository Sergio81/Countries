package com.androidbox.countries.component

import com.androidbox.countries.module.AppModule
import com.androidbox.countries.module.CountriesModule
import com.androidbox.countries.module.NetworkModule
import com.androidbox.countries.module.RoomModule
import com.androidbox.countries.view.DetailsActivity
import com.androidbox.countries.view.MainActivity
import com.androidbox.countries.view.MainActivityModule
import com.androidbox.countries.viewmodel.MainViewModel
import com.androidbox.countries.viewmodel.ViewModelFactoryModule
import com.androidbox.countries.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, CountriesModule::class, ViewModelFactoryModule::class, ViewModelModule::class, MainActivityModule::class, RoomModule::class])
interface AppComponent {
    //fun inject(application: CountriesApp)
    fun inject(mainActivity: MainActivity)
    fun inject(detailsActivity: DetailsActivity)
    fun inject(mainViewModel: MainViewModel)
}

