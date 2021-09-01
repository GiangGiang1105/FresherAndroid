package com.example.frand01_advandfinalth_giangnnh.data.model

import android.os.Parcel
import android.os.Parcelable

class TireSensor(
    var id: Int = 0,
    var id_type: String = "",
    var pressure: Double = 0.0,
    var temperature: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    fun readFromParcel(parcel: Parcel) {
        id = parcel.readInt()
        id_type = parcel.readString().toString()
        pressure = parcel.readDouble()
        temperature = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(id_type)
        parcel.writeDouble(pressure)
        parcel.writeDouble(temperature)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TireSensor> {
        override fun createFromParcel(parcel: Parcel): TireSensor {
            return TireSensor(parcel)
        }

        override fun newArray(size: Int): Array<TireSensor?> {
            return arrayOfNulls(size)
        }
    }
}