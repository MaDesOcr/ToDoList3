package com.dev.todolist3.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dev.todolist3.data.NoteApi
import kotlinx.serialization.InternalSerializationApi

@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
@Composable
fun ListeNotesScreen(clickAddNotes: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(Unit) {
            val textapi = try {
                NoteApi.getNotes()
            } catch (e: Exception) {
                "Erreur lors de la récupération."
            }
        }

        Column {
            Text(text = "Liste Notes")
            Button(onClick = clickAddNotes) {
                Text("Créer une note")
            }
        }
    }
}
