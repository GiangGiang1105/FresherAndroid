package com.example.mycontact.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycontact.data.database.dao.NoteDao
import com.example.mycontact.data.database.entities.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NoteDatabase {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "NOTE"
                ).fallbackToDestructiveMigration().build()

            }
            return INSTANCE!!
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }
}

