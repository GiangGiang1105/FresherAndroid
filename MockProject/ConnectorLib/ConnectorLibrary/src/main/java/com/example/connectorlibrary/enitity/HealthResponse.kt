package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HealthResponse(
    @RequestCode var requestCode: Int,
    @ResponseCode var responseCode: Int,
    var health_id: Int?,
    var listHealths: List<Health>,
) : Parcelable