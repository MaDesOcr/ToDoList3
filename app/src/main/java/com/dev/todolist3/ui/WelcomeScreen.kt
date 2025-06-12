package com.dev.todolist3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.dev.todolist3.data.QuoteApi
import com.dev.todolist3.R


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
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit){detectTapGestures(onTap = {onStartClick()})},
        //.clickable{onStartClick()}
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.img),
            contentDescription = "Poussin", modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Column {
            Text(
                text = quote,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}



