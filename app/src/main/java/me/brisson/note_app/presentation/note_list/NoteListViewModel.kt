package me.brisson.note_app.presentation.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.note_app.domain.repository.NoteRepository
import me.brisson.note_app.domain.model.Result
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteListUiState())
    val uiState: StateFlow<NoteListUiState> = _uiState.asStateFlow()

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            when (val result = noteRepository.getAllNotes()) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(loading = false, notes = result.value)
                    }
                }
                is Result.Failure -> {
                    _uiState.update {
                        it.copy(loading = false, failure = result)
                    }
                }
            }
        }
    }

    fun searchNote(input: String) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            when (val result = noteRepository.filterNoteTitle(input)) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(loading = false, notes = result.value)
                    }
                }
                is Result.Failure -> {
                    _uiState.update {
                        it.copy(loading = false, failure = result)
                    }
                }
            }
        }
    }
}