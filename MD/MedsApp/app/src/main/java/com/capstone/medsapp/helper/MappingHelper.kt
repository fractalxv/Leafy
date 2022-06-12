package com.capstone.medsapp.helper

import android.database.Cursor
import com.capstone.medsapp.db.DatabaseContract
import com.capstone.medsapp.entity.Note
import java.util.ArrayList

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<Note> {
        val notesList = ArrayList<Note>()

        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val name = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.NAME))
                val description =
                    getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val khasiat = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.KHASIAT))
                notesList.add(Note(id, name, description, khasiat))
            }
        }

        return notesList
    }
}