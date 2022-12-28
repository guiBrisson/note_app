package me.brisson.note_app.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.brisson.note_app.data.data_source.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}