package me.brisson.note_app.presentation.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.brisson.note_app.NoteDestinationArgs
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val noteId: String? = savedStateHandle[NoteDestinationArgs.NOTE_ID_ARG]

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()
}