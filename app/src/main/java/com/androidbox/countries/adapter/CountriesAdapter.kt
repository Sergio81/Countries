package com.androidbox.countries.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidbox.countries.view.DetailsActivity
import com.androidbox.countries.databinding.ItemCountryBinding
import com.androidbox.countries.model.api.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    private var countriesList = emptyList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(countriesList[position])

    fun setData(items: List<Country>){
        countriesList = items
        notifyDataSetChanged()
    }

    /***
     * ViewHolder with data binding
     */
    class ViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(country:Country){
            binding.item = country
            binding.executePendingBindings()

            itemView.cardView.setOnClickListener(this)
        }

        /***
         * Open the Details of the Country after click on the CardView
         */
        override fun onClick(v: View) {
            val intent = Intent(v.context, DetailsActivity::class.java)

            intent.putExtra("SelectedCountry", adapterPosition)
            v.context.startActivity(intent)
        }
    }
}