package com.dev.todolist3.data

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.Date

@InternalSerializationApi @Serializable
data class NoteModel(
    val id: Int,
    val date: String,
    val content: String
)

