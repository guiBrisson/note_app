package me.brisson.note_app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.brisson.note_app.NoteDestinationArgs.NOTE_ID_ARG
import me.brisson.note_app.presentation.note.NoteScreen
import me.brisson.note_app.presentation.note_list.NoteListScreen

@ExperimentalAnimationApi
@Composable
fun NoteNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NoteDestinations.NOTE_LIST_ROUTE,
    navActions: NoteNavigationActions = remember(navController) {
        NoteNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NoteDestinations.NOTE_LIST_ROUTE) {
            NoteListScreen(onNote = { noteId -> navActions.navigateToNote(noteId) })
        }

        composable(
            route = NoteDestinations.NOTE_ROUTE,
            arguments = listOf(
                navArgument(NOTE_ID_ARG) {
                    type = NavType.StringType ; defaultValue = ""
                }
            )
        ) {
            NoteScreen(onBack = { navController.popBackStack() })
        }

    }
}