package com.dev.todolist3.ui

import android.R.attr.shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.todolist3.data.NoteModel
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
@Composable
fun NoteUi(note : NoteModel, deleteNote : ()-> Unit){
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(7.dp)) {
        Row (modifier = Modifier.fillMaxWidth().background(color = Color.LightGray).padding(15.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = note.content, style = MaterialTheme.typography.headlineSmall)
            Column(horizontalAlignment = Alignment.End) {
                Text(text = note.date)
                IconButton(onClick = deleteNote) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@OptIn(InternalSerializationApi::class)
@Preview
@Composable
fun NoteUiPreview(){
    NoteUi(note = NoteModel(id = 1, content = "Voici du content", date ="2025-01-01")) {

    }
}