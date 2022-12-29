package me.brisson.note_app.data.repository

import me.brisson.note_app.di.NoteModule.NoteLocalDataSource
import me.brisson.note_app.domain.data_source.NoteDataSource
import me.brisson.note_app.domain.model.Note
import me.brisson.note_app.domain.model.Result
import me.brisson.note_app.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    @NoteLocalDataSource private val localDataSource: NoteDataSource
) : NoteRepository {

    override suspend fun getAllNotes(): Result<List<Note>> = localDataSource.getAllNotes()

    override suspend fun getNoteById(noteId: String): Result<Note> = localDataSource.getNoteById(noteId)

    override suspend fun filterNoteTitle(title: String): Result<List<Note>> = localDataSource.filterNoteTitle(title)

    override suspend fun addNote(note: Note): Result<Nothing?> = localDataSource.addNote(note)

    override suspend fun deleteNote(note: Note): Result<Nothing?> = localDataSource.deleteNote(note)
}