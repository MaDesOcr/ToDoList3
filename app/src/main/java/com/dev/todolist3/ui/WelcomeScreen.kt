package com.dev.todolist3.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dev.todolist3.data.QuoteApi


@Composable
fun WelcomeScreen(onStartClick: () -> Unit) {
    var quote by remember {
        mutableStateOf("Chargement...")
    }

    LaunchedEffect(Unit) {
        quote = try {
            QuoteApi.getQuote()
        } catch (e: Exception) {
            "Erreur lors de la récupération."
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = quote,
                style = MaterialTheme.typography.bodyLarge
            )
            Button(onClick = onStartClick) {
                Text("Afficher liste de notes")
            }
        }
    }
}



