package me.brisson.note_app.presentation.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.note_app.NoteDestinationArgs
import me.brisson.note_app.domain.model.Note
import me.brisson.note_app.domain.model.Result
import me.brisson.note_app.domain.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val noteId: String? = savedStateHandle[NoteDestinationArgs.NOTE_ID_ARG]

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        noteId?.let { id ->
            if (id.isEmpty()) {
                changeMode(true)
            } else {
                getNoteById(id)
            }
        }
    }

    private fun getNoteById(noteId: String) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            when (val result = noteRepository.getNoteById(noteId)) {
                is Result.Success -> {
                    val note = result.value
                    _uiState.update {
                        it.copy(
                            loading = false,
                            note = note,
                            title = note.title,
                            content = note.content
                        )
                    }
                }
                is Result.Failure -> {
                    _uiState.update { it.copy(loading = false, failure = result) }
                }
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            val note = uiState.value.note ?: uiState.value.title?.let { title ->
                uiState.value.content?.let { content ->
                    Note(
                        title = title,
                        content = content
                    )
                }
            }

            if (note == null) {
                _uiState.update { it.copy(saveSuccess = false) }
            } else {
                when (val result = noteRepository.addNote(note)) {
                    is Result.Success -> {
                        _uiState.update { it.copy(saveSuccess = true) }
                    }
                    is Result.Failure -> {
                        _uiState.update {
                            it.copy(saveSuccess = false, failure = result)
                        }
                    }
                }
            }

        }
    }

    fun updateNoteTitle(title: String) {
        _uiState.update { it.copy(title = title) }
        uiState.value.note?.let { note ->
            note.title = title
            _uiState.update { it.copy(note = note) }
        }
    }

    fun updateNoteContent(content: String) {
        _uiState.update { it.copy(content = content) }
        uiState.value.note?.let { note ->
            note.content = content
            _uiState.update { it.copy(note = note) }
        }
    }

    fun changeMode(editMode: Boolean) {
        _uiState.update { it.copy(editMode = editMode) }
        if (!editMode) saveNote()
    }
}