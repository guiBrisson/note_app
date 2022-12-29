package me.brisson.note_app.domain.data_source

import me.brisson.note_app.domain.model.Note
import me.brisson.note_app.domain.model.Result

interface NoteDataSource {

    suspend fun getAllNotes(): Result<List<Note>>

    suspend fun getNoteById(noteId: String) : Result<Note>

    suspend fun filterNoteTitle(title: String) : Result<List<Note>>

    suspend fun addNote(note: Note) : Result<Nothing?>

    suspend fun deleteNote(note: Note) : Result<Nothing?>

}