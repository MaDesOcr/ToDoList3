package com.dev.todolist3.ui

import android.provider.ContactsContract
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.todolist3.data.NoteApi
import com.dev.todolist3.data.NoteModel
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
@Composable
fun ListeNotesScreen(clickAddNotes: () -> Unit) {

    var listeNotes by remember { mutableStateOf<List<NoteModel>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        LaunchedEffect(Unit) {
            listeNotes = try {
                NoteApi.getNotes()
            } catch (e: Exception) {
                "Erreur lors de la récupération."
            } as List<NoteModel>
        }

        Column(modifier = Modifier.align(Alignment.Center).fillMaxSize().padding(
            20.dp), horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "Liste Notes", textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineMedium, textDecoration = TextDecoration.Underline)

            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column {
                listeNotes.forEach { note ->
                    NoteUi(
                        note,
                        deleteNote = { ->
                            coroutineScope.launch {
                                try {
                                    listeNotes = listeNotes.filter {
                                        it.id != note.id
                                    }
                                    NoteApi.deleteNote(note.id)
                                } catch (e: Exception) {
                                    println("Erreur lors de la suppression de la note: ${e.message}")
                                }
                            }
                        }

                    )
                    Spacer(modifier = Modifier.height(20.dp))

                }

                    Button(onClick = clickAddNotes) {
                        Text("Créer une note")
                    }
                }


            }

        }
    }
}


@OptIn(InternalSerializationApi::class)
@Preview(showBackground = true)
@Composable
fun ListeNotesScreenPreview(){
    ListeNotesScreen() {

    }
}