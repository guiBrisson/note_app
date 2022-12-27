package me.brisson.note_app.presentation.note_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.brisson.note_app.ui.components.StaggeredVerticalGrid
import me.brisson.note_app.ui.theme.NoteAppTheme

@ExperimentalAnimationApi
@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    onNote: (noteId: String) -> Unit,
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    rememberSystemUiController().apply {
        setSystemBarsColor(color = MaterialTheme.colorScheme.background)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                TitleAndSearch(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(horizontal = 26.dp, vertical = 20.dp),
                    onSearchInputChange = { input -> viewModel.searchNote(input) }
                )
            }

            uiState.notes.let { notes ->
                item {
                    if (notes.isNotEmpty()) {
                        StaggeredVerticalGrid(
                            modifier = Modifier.padding(horizontal = 18.dp),
                            maxColumnWidth = 220.dp
                        ) {
                            notes.forEach { note ->
                                NoteItem(note = note, onClick = { onNote(note.id) })
                            }
                        }
                    }
                }

            }
        }


        FABAddNote(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(30.dp),
            onClick = { onNote("") }
        )
    }

}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewNoteListScreen() {
    NoteAppTheme {
        NoteListScreen(onNote = { })
    }

}