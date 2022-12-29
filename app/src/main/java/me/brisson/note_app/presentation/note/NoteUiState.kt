package me.brisson.note_app.presentation.note

import me.brisson.note_app.domain.model.Note
import me.brisson.note_app.domain.model.Result

data class NoteUiState(
    val loading: Boolean = false,
    val note: Note? = null,
    val title: String? = null,
    val content: String? = null,
    val failure: Result.Failure? = null,
    val editMode: Boolean = false,
    val saveSuccess: Boolean? = null
)
