package com.dev.todolist3.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.serialization.json.Json

@OptIn(InternalSerializationApi::class)
object NoteApi {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getNotes(): List<NoteModel> = withContext(Dispatchers.IO) {
        val url = URL("http://10.0.2.2:8081/api/notes")
        val connection = url.openConnection() as HttpURLConnection
        try {
            println("on passe par là")

            val text = connection.inputStream.bufferedReader().readText()

            println(text)

            val listeDeNotes = json.decodeFromString<List<NoteModel>>(text)
            //val mo//json.decodeFromString<NoteModel>
            listeDeNotes
        } catch (e : Exception){
            println(e)
        } finally {
            connection.disconnect()
        } as List<NoteModel>
    }
}