package me.brisson.note_app

import androidx.navigation.NavHostController
import me.brisson.note_app.NoteDestinationArgs.NOTE_ID_ARG
import me.brisson.note_app.NoteScreens.NOTE_LIST_SCREEN
import me.brisson.note_app.NoteScreens.NOTE_SCREEN

private object NoteScreens {
    const val NOTE_LIST_SCREEN = "note_list"
    const val NOTE_SCREEN = "note"
}

object NoteDestinationArgs {
    const val NOTE_ID_ARG = "note_id"
}

object NoteDestinations {
    const val NOTE_LIST_ROUTE = NOTE_LIST_SCREEN
    const val NOTE_ROUTE = "$NOTE_SCREEN/?{$NOTE_ID_ARG}"
}

class NoteNavigationActions(private val navController: NavHostController) {
    fun navigateToNote(noteId: String?) {
        val route = "$NOTE_SCREEN/?${noteId ?: ""}"
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }
}
