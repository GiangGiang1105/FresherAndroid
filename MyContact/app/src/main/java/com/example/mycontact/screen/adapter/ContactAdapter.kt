package com.example.mycontact.screen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontact.R
import com.example.mycontact.data.model.Contact

class ContactAdapter : RecyclerView.Adapter<ContactHolder>() {

    private var listContacts = mutableListOf<Contact>()

    fun bindData(danhBas: MutableList<Contact>){
        Log.e("data", danhBas.size.toString())
        danhBas.let {
            this.listContacts.clear()
            this.listContacts.addAll(danhBas)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bindData(listContacts[position])
    }

    override fun getItemCount() = listContacts.size
}