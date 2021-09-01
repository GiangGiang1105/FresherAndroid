package com.example.serveraidl.data.model

import android.os.Parcel
import android.os.Parcelable

class Student(
    var id: Int = 0,
    var name: String = "",
    var age: Int = 0,
    var math: Float = 0f,
    var physical: Float = 0f,
    var chemistry: Float = 0f,
    var english: Float = 0f,
    var literature: Float = 0f
) : Parcelable {

    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.apply {
            writeInt(id)
            writeString(name)
            writeInt(age)
            writeFloat(math)
            writeFloat(physical)
            writeFloat(chemistry)
            writeFloat(english)
            writeFloat(literature)
        }
    }

    fun readFromParcel(parcel: Parcel) {
        id = parcel.readInt()
        name = parcel.readString().toString()
        age = parcel.readInt()
        math = parcel.readFloat()
        physical = parcel.readFloat()
        chemistry = parcel.readFloat()
        english = parcel.readFloat()
        literature = parcel.readFloat()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}