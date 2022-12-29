package me.brisson.note_app.data.data_source.local

import me.brisson.note_app.data.data_source.EmptyResultThrowable
import me.brisson.note_app.data.data_source.local.entity.asEntity
import me.brisson.note_app.data.data_source.local.entity.asNote
import me.brisson.note_app.data.data_source.local.entity.asNotes
import me.brisson.note_app.domain.data_source.NoteDataSource
import me.brisson.note_app.domain.model.Note
import me.brisson.note_app.domain.model.Result
import javax.inject.Inject

class LocalNoteDataSource @Inject constructor(
    private val noteDao: NoteDao
) : NoteDataSource {

    override suspend fun getAllNotes(): Result<List<Note>> {
        return try {
            val notes = noteDao.getAll().asNotes()
            if (notes.isEmpty()) {
                Result.Failure(throwable = EmptyResultThrowable())
            } else {
                Result.Success(notes)
            }
        } catch (e: Exception) {
            Result.Failure(message = e.message)
        }
    }

    override suspend fun getNoteById(noteId: String): Result<Note> {
        return try {
            val note = noteDao.getById(noteId)?.asNote()
            if (note == null) {
                Result.Failure(throwable = EmptyResultThrowable())
            } else {
                Result.Success(note)
            }
        } catch (e: Exception) {
            Result.Failure(message = e.message)
        }
    }

    override suspend fun filterNoteTitle(title: String): Result<List<Note>> {
        return try {
            val notes = noteDao.findByTitle(title).asNotes()
            if (notes.isEmpty()) {
                Result.Failure(throwable = EmptyResultThrowable())
            } else {
                Result.Success(notes)
            }
        } catch (e: Exception) {
            Result.Failure(message = e.message)
        }
    }

    override suspend fun addNote(note: Note): Result<Nothing?> {
        return try {
            noteDao.insertAll(note.asEntity())
            Result.Success(null)
        } catch (e: Exception) {
            Result.Failure(message = e.message)
        }
    }

    override suspend fun deleteNote(note: Note): Result<Nothing?> {
        return try {
            noteDao.delete(note.asEntity())
            Result.Success(null)
        } catch (e: Exception) {
            Result.Failure(message = e.message)
        }
    }
}