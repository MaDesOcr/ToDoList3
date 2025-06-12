package com.dev.todolist3.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.todolist3.ui.AddNoteScreen
import com.dev.todolist3.ui.ListeNotesScreen
import com.dev.todolist3.ui.WelcomeScreen

object Destinations {
    const val Welcome = "welcome"
    const val ListeNotes = "listeNotes"
    const val AddNote = "addNote"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController  = navController,
        startDestination = Destinations.Welcome
    ) {
        composable(Destinations.Welcome) {
            WelcomeScreen( onStartClick = {
                navController.navigate(Destinations.ListeNotes)
            })
        }

        composable(Destinations.ListeNotes) {
            ListeNotesScreen(clickAddNotes = {
                navController.navigate(Destinations.AddNote)
            })
        }

        composable(Destinations.AddNote) {
            AddNoteScreen(clickSaveNewNotes = {
                //saveNote
                navController.navigate(Destinations.ListeNotes)

            },
                clickAnnuler = {
                    navController.navigate(Destinations.ListeNotes)
                }
            )

        }
    }



}