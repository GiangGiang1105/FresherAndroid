package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @ResponseCode var responseCode: Int,
    @RequestCode var requestCode: Int,
    var user: User?,
    var user_id: Int?
) : Parcelable