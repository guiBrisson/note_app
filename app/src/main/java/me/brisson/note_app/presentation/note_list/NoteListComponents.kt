package me.brisson.note_app.presentation.note_list

import android.text.format.DateUtils
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.brisson.note_app.domain.model.Note
import me.brisson.note_app.ui.components.CustomTextField
import me.brisson.note_app.ui.theme.NoteAppTheme
import me.brisson.note_app.ui.theme.montserrat
import me.brisson.note_app.utils.random
import java.util.*

@ExperimentalAnimationApi
@Composable
fun TitleAndSearch(
    modifier: Modifier = Modifier,
    onSearchInputChange: (searchInput: String) -> Unit
) {
    var showSearch by remember { mutableStateOf(false) }

    AnimatedContent(targetState = showSearch) { scope ->
        if (scope) {
            val focusManager = LocalFocusManager.current
            val focusRequester = FocusRequester()
            val textStyle = TextStyle(
                fontFamily = montserrat,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            CustomTextField(
                modifier = modifier
                    .clip(shape = RoundedCornerShape(4.dp))
                    .height(45.dp),
                focusRequester = focusRequester,
                inputTextStyle = textStyle,
                singleLine = true,
                onSearchInputChange = { onSearchInputChange(it) },
                onClearInput = { showSearch = false },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
            )
        } else {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Text(
                    text = "Notes",
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable { showSearch = true }
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun FABAddNote(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.size(60.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp,
        shape = CircleShape
    ) {
        IconButton(
            modifier = Modifier.fillMaxSize(),
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.random()
    ) {
        Column(modifier = Modifier
            .padding(15.dp)
            .clickable { onClick() }) {
            val date = DateUtils.getRelativeTimeSpanString(
                note.createdAt,
                Calendar.getInstance().timeInMillis,
                DateUtils.SECOND_IN_MILLIS,
            )
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = note.title,
                style = TextStyle(
                    fontFamily = montserrat,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = date.toString(),
                style = TextStyle(
                    fontFamily = montserrat,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Light
                )
            )
        }

    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewTitleAndSearch() {
    NoteAppTheme {
        TitleAndSearch(onSearchInputChange = { })
    }
}

@Preview
@Composable
fun PreviewFABAddNote() {
    NoteAppTheme {
        FABAddNote { }
    }
}
