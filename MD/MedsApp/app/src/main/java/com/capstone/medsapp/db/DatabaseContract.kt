package com.capstone.medsapp.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class NoteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "plant"
            const val _ID = "_id"
            const val NAME = "name"
            const val DESCRIPTION = "description"
            const val KHASIAT = "khasiat"
        }
    }
}