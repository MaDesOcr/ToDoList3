package com.dev.todolist3.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.todolist3.data.NoteModel
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
@Composable
fun NoteUi(note : NoteModel, deleteNote : ()-> Unit){
    Box(modifier = Modifier.fillMaxWidth()) {
        Row (modifier = Modifier.fillMaxWidth()){
            Text(text = note.content)
            Text(text = note.date)
            IconButton(onClick = deleteNote) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}