package me.brisson.note_app.presentation.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.brisson.note_app.ui.theme.NoteAppTheme

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
            TopComponent(onBack = onBack, onEdit = { /*todo*/ })
            NoteTitleTextField(
                modifier = Modifier.padding(vertical = 20.dp),
                onSearchInputChange = { /*todo*/ }
            )
            NoteContentTextField(onSearchInputChange = { /*todo*/ })
        }
    }
}

@Preview
@Composable
fun PreviewNoteScreen() {
    NoteAppTheme {
        NoteScreen(onBack = { })
    }
}