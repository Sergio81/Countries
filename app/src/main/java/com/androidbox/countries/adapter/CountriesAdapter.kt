package com.androidbox.countries.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.androidbox.countries.view.DetailsActivity
import com.androidbox.countries.R
import com.androidbox.countries.model.api.Country
import com.androidbox.countries.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_country.view.*

// TODO - https://android.jlelse.eu/how-to-bind-a-list-of-items-to-a-recyclerview-with-android-data-binding-1bd08b4796b4
class CountriesAdapter(private val context: Activity, private val viewModel: MainViewModel) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    var countriesList = emptyList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount() = countriesList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countriesList[position], context, position, viewModel)
    }

    fun setData(items: List<Country>){
        countriesList = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country:Country, context: Activity,position:Int, viewModel: MainViewModel){
            itemView.txtName.text = country.name
            SvgLoader.pluck()
                .with(context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(country.flag, itemView.imageView)
            itemView.cardView.setOnClickListener{
                var intent = Intent(context, DetailsActivity::class.java)

                intent.putExtra("SelectedCountry", position)
                viewModel.selectCountry(position)
                context.startActivity(intent)
            }
        }
    }
}