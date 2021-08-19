package com.example.giangnnh_advanceandroid_day1.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giangnnh_advanceandroid_day1.data.model.Country
import com.example.giangnnh_advanceandroid_day1.databinding.ItemCountryBinding

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    private var _listCountries = mutableListOf<Country>()

    private var itemOnClickView: ((country: Country) -> Unit?)? = null

    fun setOnItemClickListener(itemClick: (country: Country) -> Unit) {
        itemOnClickView = itemClick
    }

    fun updataMusic(list: List<Country>?) {
        list?.let {
            _listCountries.clear()
            _listCountries.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CountryHolder(
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bindData(_listCountries[position])
        holder.itemView.setOnClickListener {
            itemOnClickView?.invoke(_listCountries[position])
        }
    }

    override fun getItemCount() = _listCountries.size

    class CountryHolder(
        private var itemCountryBinding: ItemCountryBinding
    ) :
        RecyclerView.ViewHolder(itemCountryBinding.root) {

        fun bindData(country: Country) {
            itemCountryBinding.country = country
        }
    }
}