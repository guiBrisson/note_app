package me.brisson.note_app.presentation.note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@ExperimentalAnimationApi
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 20.dp)
        ) {
            TopComponent(
                onBack = onBack,
                isEditMode = uiState.editMode,
                onTrailing = { viewModel.changeMode(!uiState.editMode) }
            )

            uiState.note?.let { note ->
                AnimatedVisibility(visible = uiState.editMode) {
                    Text(
                        text = note.createdAt.toString(), // todo: format date
                        style = TextStyle(
                            fontFamily = montserrat,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    )
                }
            }

            NoteTitleTextField(
                modifier = Modifier.padding(vertical = 30.dp),
                input = uiState.note?.title,
                readOnly = !uiState.editMode,
                onSearchInputChange = { /*todo*/ }
            )
            NoteContentTextField(
                input = uiState.note?.content,
                readOnly = !uiState.editMode,
                onSearchInputChange = { /*todo*/ }
            )
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