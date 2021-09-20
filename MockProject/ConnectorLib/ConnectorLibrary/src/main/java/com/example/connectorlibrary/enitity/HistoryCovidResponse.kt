package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryCovidResponse(
    @RequestCode var requestCode: Int,
    @ResponseCode var responseCode: Int,
    var listHistoryCovid: List<HistoryCovid>
) : Parcelable
