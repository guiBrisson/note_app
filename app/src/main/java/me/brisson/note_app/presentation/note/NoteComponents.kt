package me.brisson.note_app.presentation.note

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.brisson.note_app.ui.components.CustomTextField
import me.brisson.note_app.ui.theme.NoteAppTheme
import me.brisson.note_app.ui.theme.montserrat

@ExperimentalAnimationApi
@Composable
fun TopComponent(
    modifier: Modifier = Modifier,
    isEditMode: Boolean,
    onBack: () -> Unit,
    onTrailing: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomButton(imageVector = Icons.Rounded.ArrowBack, onClick = onBack)

        AnimatedContent(targetState = isEditMode) { scope ->
            if (scope) {
                Box(
                    modifier = modifier
                        .height(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable { onTrailing() }
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 15.dp),
                        text = "Save",
                        style = TextStyle(
                            fontFamily = montserrat,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    )
                }
            } else {
                CustomButton(imageVector = Icons.Rounded.Edit, onClick = onTrailing)
            }
        }
    }
}

@Composable
fun NoteTitleTextField(
    modifier: Modifier = Modifier,
    input: String? = null,
    readOnly: Boolean = false,
    onSearchInputChange: (input: String) -> Unit
) {
    val textStyle = TextStyle(
        fontFamily = montserrat,
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground
    )

    val focusManager = LocalFocusManager.current

    CustomTextField(
        modifier = modifier.heightIn(min = 50.dp),
        text = input,
        onSearchInputChange = { onSearchInputChange(it) },
        label = {
            Text(text = "Title", style = textStyle, color = Color.Gray)
        },
        inputTextStyle = textStyle,
        readOnly = readOnly,
        enabled = !readOnly,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
    )
}

@Composable
fun NoteContentTextField(
    modifier: Modifier = Modifier,
    input: String? = null,
    readOnly: Boolean = false,
    onSearchInputChange: (input: String) -> Unit
) {
    val textStyle = TextStyle(
        fontFamily = montserrat,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    CustomTextField(
        modifier = modifier.fillMaxHeight(),
        text = input,
        onSearchInputChange = { onSearchInputChange(it) },
        label = {
            Text(text = "Type something...", style = textStyle, color = Color.Gray)
        },
        inputTextStyle = textStyle,
        readOnly = readOnly,
        enabled = !readOnly,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions.Default
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

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewTopComponent() {
    NoteAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TopComponent(
                modifier = Modifier.padding(horizontal = 20.dp),
                isEditMode = false,
                onBack = { },
                onTrailing = { }
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