package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusResponse(
    @ResponseCode var responseCode: Int,
    var listStatuses: List<Status>
) : Parcelable