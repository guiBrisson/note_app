package me.brisson.note_app.presentation.note_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.brisson.note_app.domain.model.Note
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(NoteListUiState())
    val uiState: StateFlow<NoteListUiState> = _uiState.asStateFlow()

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        val notes = listOf(
            Note(title = "lorem1lorem1lorem1lorem1lorem1lorem1lorem1lorem1lorem1lorem1", createdAt = Calendar.getInstance().timeInMillis),
            Note(title = "lorem2lorem2lorem2lorem2lorem2lorem2", createdAt = Calendar.getInstance().timeInMillis),
            Note(title = "lorem3", createdAt = Calendar.getInstance().timeInMillis)
        )

        _uiState.update { it.copy(notes = notes) }
    }

    fun searchNote(input: String) {
        /*todo*/
    }
}