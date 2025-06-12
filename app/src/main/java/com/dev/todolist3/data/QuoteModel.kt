package com.dev.todolist3.data

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi @Serializable
data class QuoteModel(
    val id: Int,
    val content: String,
    val compteur: Int
)
