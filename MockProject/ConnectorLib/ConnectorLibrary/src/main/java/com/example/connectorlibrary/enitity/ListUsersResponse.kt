package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ListUsersResponse(
    @ResponseCode var responseCode: Int,
    var listUsers: List<User>
) : Parcelable