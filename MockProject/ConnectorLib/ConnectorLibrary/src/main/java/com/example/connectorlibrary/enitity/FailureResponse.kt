package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FailureResponse(
    @RequestCode var requestCode: Int,
    @ResponseCode var responseCode: Int
) : Parcelable