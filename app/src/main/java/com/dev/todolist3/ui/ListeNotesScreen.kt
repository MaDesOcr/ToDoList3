package com.dev.todolist3.ui

import android.provider.ContactsContract
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dev.todolist3.data.NoteApi
import com.dev.todolist3.data.NoteModel
import kotlinx.serialization.InternalSerializationApi

@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
@Composable
fun ListeNotesScreen(clickAddNotes: () -> Unit) {

    var listeNotes by remember { mutableStateOf<List<NoteModel>>(emptyList()) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(Unit) {
            listeNotes = try {
                NoteApi.getNotes()
            } catch (e: Exception) {
                "Erreur lors de la récupération."
            } as List<NoteModel>
        }

        Column {
            Text(text = "Liste Notes")

            listeNotes.forEach { note -> NoteUi(
                note,
                deleteNote = { -> print(note)
                    /*listeNotes = listeNotes.filter {
                                    it.id != note.id
                                }*/
                } as () -> Unit
                )
            }

            Button(onClick = clickAddNotes) {
                Text("Créer une note")
            }
        }
    }
}
