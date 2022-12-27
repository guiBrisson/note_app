package me.brisson.note_app.presentation.note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.brisson.note_app.ui.components.CustomTextField
import me.brisson.note_app.ui.theme.NoteAppTheme
import me.brisson.note_app.ui.theme.montserrat

@Composable
fun TopComponent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomButton(imageVector = Icons.Rounded.ArrowBack, onClick = onBack)
        CustomButton(imageVector = Icons.Rounded.Edit, onClick = onEdit)
    }
}

@Composable
fun NoteTitleTextField(
    modifier: Modifier = Modifier,
    onSearchInputChange: (input: String) -> Unit
) {
    val textStyle = TextStyle(
        fontFamily = montserrat,
        fontSize = 30.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    CustomTextField(
        modifier = modifier.heightIn(min = 50.dp),
        onSearchInputChange = { onSearchInputChange(it) },
        label = {
            Text(text = "Title", style = textStyle, color = Color.Gray)
        },
        inputTextStyle = textStyle
    )
}

@Composable
fun NoteContentTextField(
    modifier: Modifier = Modifier,
    onSearchInputChange: (input: String) -> Unit
) {
    val textStyle = TextStyle(
        fontFamily = montserrat,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    CustomTextField(
        modifier = modifier.heightIn(min = 50.dp),
        onSearchInputChange = { onSearchInputChange(it) },
        label = {
            Text(text = "Type something...", style = textStyle, color = Color.Gray)
        },
        inputTextStyle = textStyle
    )

}

@Composable
private fun CustomButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            imageVector = imageVector,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun PreviewTopComponent() {
    NoteAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TopComponent(
                modifier = Modifier.padding(horizontal = 20.dp),
                onBack = { },
                onEdit = { }
            )
        }
    }
}

@Preview
@Composable
fun PreviewNoteTitleTextField() {
    NoteAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                NoteTitleTextField(onSearchInputChange = { })
                NoteContentTextField(onSearchInputChange = { })
            }
        }
    }
}