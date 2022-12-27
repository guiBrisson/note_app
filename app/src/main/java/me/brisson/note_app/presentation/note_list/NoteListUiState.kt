package me.brisson.note_app.presentation.note_list

import me.brisson.note_app.domain.model.Note

data class NoteListUiState(
    val loading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val error: String? = null
)
