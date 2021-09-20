package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthResponse(
    @RequestCode var requestCode: Int,
    @ResponseCode var responseCode: Int,
    var user_id: Int,
    var name: String
) : Parcelable