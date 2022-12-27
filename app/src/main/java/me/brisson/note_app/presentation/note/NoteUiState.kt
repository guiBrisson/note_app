package me.brisson.note_app.presentation.note

import me.brisson.note_app.domain.model.Note

data class NoteUiState(
    val loading: Boolean = false,
    val note: Note? = null,
    val error: String? = null
)
