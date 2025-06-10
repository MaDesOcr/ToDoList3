package com.dev.todolist3.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AddNoteScreen(clickSaveNewNotes: () -> Unit,
                  clickAnnuler: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "Add a note")

            TextField(value = "",
                onValueChange = {},
                label = {
                    Text(text = "Titre de la note")
                }
            )

            Button(onClick = clickSaveNewNotes) {
                Text("Save new note")
            }

            Button(onClick = clickAnnuler) {
                Text("Annuler")
            }
        }

    }
}