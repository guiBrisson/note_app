package me.brisson.note_app.presentation.note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.brisson.note_app.ui.theme.NoteAppTheme
import me.brisson.note_app.ui.theme.montserrat
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalAnimationApi
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp, vertical = 20.dp)
        ) {
            TopComponent(
                onBack = onBack,
                isEditMode = uiState.editMode,
                onTrailing = { viewModel.changeMode(!uiState.editMode) }
            )

            uiState.title.let {
                if (it.isNullOrEmpty()) {
                    NoteTitleTextField(
                        modifier = Modifier.padding(top = 30.dp),
                        readOnly = !uiState.editMode,
                        onSearchInputChange = { input -> viewModel.updateNoteTitle(input) }
                    )
                } else {
                    NoteTitleTextField(
                        modifier = Modifier.padding(top = 30.dp),
                        input = uiState.title,
                        readOnly = !uiState.editMode,
                        onSearchInputChange = { input -> viewModel.updateNoteTitle(input) }
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 15.dp))

            uiState.note?.let { note ->
                AnimatedVisibility(visible = !uiState.editMode) {
                    val formattedDate =
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(note.createdAt)
                    Text(
                        text = formattedDate,
                        style = TextStyle(
                            fontFamily = montserrat,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 15.dp))

            uiState.content.let {
                if (it.isNullOrEmpty()) {
                    NoteContentTextField(
                        readOnly = !uiState.editMode,
                        onSearchInputChange = { input -> viewModel.updateNoteContent(input) }
                    )
                } else {
                    NoteContentTextField(
                        input = uiState.content,
                        readOnly = !uiState.editMode,
                        onSearchInputChange = { input -> viewModel.updateNoteContent(input) }
                    )
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewNoteScreen() {
    NoteAppTheme {
        NoteScreen(onBack = { })
    }
}