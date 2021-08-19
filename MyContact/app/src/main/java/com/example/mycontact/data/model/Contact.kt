package com.example.mycontact.data.model

import android.net.Uri

data class Contact(
    val uriImage: Uri?,
    val name: String?,
    val phone: String?
)