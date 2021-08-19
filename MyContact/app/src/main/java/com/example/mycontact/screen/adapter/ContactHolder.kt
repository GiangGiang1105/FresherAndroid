package com.example.mycontact.screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontact.R
import com.example.mycontact.data.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(contact: Contact) {
        itemView.apply {
            textName.text = contact.name
            textPhone.text = contact.phone
            imagePerson.setImageResource(R.drawable.person)
        }
    }
}