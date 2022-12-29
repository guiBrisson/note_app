package me.brisson.note_app.presentation.note_list

import me.brisson.note_app.domain.model.Note
import me.brisson.note_app.domain.model.Result

data class NoteListUiState(
    val loading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val failure: Result.Failure? = null
)
