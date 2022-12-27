package me.brisson.note_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.note_app.ui.theme.NoteAppTheme
import me.brisson.note_app.ui.theme.montserrat

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    focusRequester: FocusRequester = FocusRequester(),
    label: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onSearchInputChange: (input: String) -> Unit,
    onClearInput: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    inputTextStyle: TextStyle = TextStyle.Default
) {
    var input by remember { mutableStateOf(TextFieldValue(text)) }

    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(40.dp)
            .focusRequester(focusRequester),
        value = input,
        onValueChange = {
            input = it
            onSearchInputChange(it.text)
        },
        singleLine = true,
        readOnly = readOnly,
        enabled = enabled,
        textStyle = inputTextStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(IntrinsicSize.Max)
                ) {
                    if (input.text.isEmpty() && label != null) {
                        label()
                    }
                    innerTextField()
                }
                onClearInput?.let { onClearInput ->
                    Box(
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .height(IntrinsicSize.Max)
                            .clickable {
                                input = TextFieldValue("")
                                onClearInput()
                            }
                    ) {
                        Icon(
                            modifier = Modifier.align(Alignment.Center),
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewCustomTextField() {
    NoteAppTheme {
        Surface {
            CustomTextField(
                modifier = Modifier.height(50.dp),
                onSearchInputChange = { },
                label = {
                    Text(text = "label", color = Color.Gray, fontFamily = montserrat)
                },
                onClearInput = { }
            )
        }
    }
}