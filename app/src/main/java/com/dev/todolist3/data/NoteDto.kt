package com.dev.todolist3.data

import kotlinx.serialization.Serializable

@Serializable
data class NoteDto(
    val date: String,
    val content: String
)