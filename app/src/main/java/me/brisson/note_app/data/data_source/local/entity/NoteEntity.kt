package me.brisson.note_app.data.data_source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.brisson.note_app.domain.model.Note
import java.util.*

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey val uid: String = UUID.randomUUID().toString(),
    val title: String,
    @ColumnInfo(name = "created_at") val createdAt: Long = Calendar.getInstance().timeInMillis,
    val content: String
)

fun NoteEntity.asNote() : Note {
    return Note(
        id = uid,
        title = title,
        createdAt = createdAt,
        content = content
    )
}

fun List<NoteEntity>.asNotes() : List<Note> {
    return map { it.asNote() }
}

fun Note.asEntity() : NoteEntity {
    return NoteEntity(
        uid = id,
        title = title,
        createdAt = createdAt,
        content = content
    )
}