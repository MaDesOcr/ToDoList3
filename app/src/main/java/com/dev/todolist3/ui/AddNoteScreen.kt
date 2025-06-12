package com.dev.todolist3.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dev.todolist3.data.NoteApi
import com.dev.todolist3.data.NoteDto
import kotlinx.coroutines.launch


@Composable
fun AddNoteScreen(clickSaveNewNotes: () -> Unit,
                  clickAnnuler: () -> Unit) {
    var givenContent by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "Add a note")

            TextField(value = givenContent,
                onValueChange = {givenContent = it},
                label = {
                    Text(text = "Titre de la note")
                }
            )

            Button(onClick = {
                coroutineScope.launch {
                    NoteApi.addNoteDto(NoteDto(content = givenContent, date = "2025-01-01"))
                }
                clickSaveNewNotes()
            }) {
                Text("Save new note")
            }

            Button(onClick = clickAnnuler) {
                Text("Annuler")
            }
        }

    }
}