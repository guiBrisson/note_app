package me.brisson.note_app.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.brisson.note_app.data.data_source.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE uid=:id")
    suspend fun getById(id: String): NoteEntity?

    @Query("SELECT * FROM note WHERE title IN (:title)")
    suspend fun findByTitle(title: String): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg notes: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}