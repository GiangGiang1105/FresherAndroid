package com.example.day7exercise1.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.day7exercise1.data.SMSData
import com.example.day7exercise1.databinding.ItemSmsBinding

class SmsAdapter : RecyclerView.Adapter<SmsAdapter.SmsHolder>() {

    private var _listSmsData = mutableListOf<SMSData>()

    fun updateData(list: List<SMSData>?) {
        list?.let {
            _listSmsData.clear()
            _listSmsData.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SmsHolder(ItemSmsBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)

    override fun onBindViewHolder(holder: SmsHolder, position: Int) {
        holder.bindData(_listSmsData[position])
    }

    override fun getItemCount() = _listSmsData.size

    class SmsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val holderBinding = ItemSmsBinding.bind(itemView)

        fun bindData(sms: SMSData) {
            holderBinding.apply {
                textBody.text = sms.body
                textCreator.text = sms.creator
            }
        }
    }
}