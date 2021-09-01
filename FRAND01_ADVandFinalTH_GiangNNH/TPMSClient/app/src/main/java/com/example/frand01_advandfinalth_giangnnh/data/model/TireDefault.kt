package com.example.frand01_advandfinalth_giangnnh.data.model

import android.os.Parcel
import android.os.Parcelable

data class TireDefault(
   var id: Int = 0,
    var highPressure: Double = 0.0,
    var lowPressure: Double = 0.0,
    var highTemperature: Double = 0.0
) : Parcelable {

    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    fun readFromParcel(parcel: Parcel) {
        id = parcel.readInt()
        highPressure = parcel.readDouble()
        lowPressure = parcel.readDouble()
        highTemperature = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeDouble(highPressure)
        parcel.writeDouble(lowPressure)
        parcel.writeDouble(highTemperature)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TireDefault> {
        override fun createFromParcel(parcel: Parcel): TireDefault {
            return TireDefault(parcel)
        }

        override fun newArray(size: Int): Array<TireDefault?> {
            return arrayOfNulls(size)
        }
    }
}