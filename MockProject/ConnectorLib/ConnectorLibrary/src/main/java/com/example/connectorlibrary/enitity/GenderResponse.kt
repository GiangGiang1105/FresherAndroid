package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenderResponse(
    @ResponseCode var responseCode: Int,
    var listGender: List<Gender>
) : Parcelable