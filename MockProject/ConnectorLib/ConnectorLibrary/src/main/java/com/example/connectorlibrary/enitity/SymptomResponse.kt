package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SymptomResponse(
    @ResponseCode var responseCode: Int,
    var listSymptom: List<Symptom>
) : Parcelable