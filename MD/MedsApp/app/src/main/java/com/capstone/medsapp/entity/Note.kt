package com.capstone.medsapp.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    var id: Int = 0,
    var name: String? = null,
    var description: String? = null,
    var khasiat: String? = null
) : Parcelable