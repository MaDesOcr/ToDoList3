package com.dev.todolist3.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.serialization.json.Json
import java.io.IOException
import kotlin.io.use
import kotlinx.serialization.encodeToString

private val json = Json {
    ignoreUnknownKeys = true
}

@OptIn(InternalSerializationApi::class)
object NoteApi {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getNotes(): List<NoteModel> = withContext(Dispatchers.IO) {
        val url = URL("http://10.0.2.2:8081/api/notes")
        val connection = url.openConnection() as HttpURLConnection
        try {
            val text = connection.inputStream.bufferedReader().readText()
            val listeDeNotes = json.decodeFromString<List<NoteModel>>(text)
            listeDeNotes
        } catch (e : Exception){
            println(e)
        } finally {
            connection.disconnect()
        } as List<NoteModel>
    }

    suspend fun deleteNote(id : Int) = withContext(Dispatchers.IO) {
        val url = URL("http://10.0.2.2:8081/api/notes/$id")
        val connection = url.openConnection() as HttpURLConnection
     /*   connection.requestMethod = "DELETE"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000*/
        try {
            val responseCode = connection.responseCode
            println("responseCode $responseCode")
           } catch (e : Exception){
            println(e)
        } finally {
            connection.disconnect()
        }
    }

   /* suspend fun addNote(note: NoteModel): NoteModel = withContext(Dispatchers.IO) {

        val url = URL("http://10.0.2.2:8081/api/notes")
        val conn = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json")
            doOutput = true
        }
        try {
            val bodyJson = json.encodeToString(NoteModel.serializer(), note)            connection.outputStream.bufferedWriter().use { it.write(bodyJson) }
            conn.outputStream
                .bufferedWriter()
                .use {  writer: java.io.BufferedWriter ->
                    writer.write(bodyJson)
                }
            val code = conn.responseCode
            val responseText = if (code in 200..299) {
                conn.inputStream.bufferedReader().readText()
            } else {
                val err = conn.errorStream.bufferedReader().readText()
                throw IOException("HTTP $code – $err")
            }
            json.decodeFromString(responseText)
        } finally {
            conn.disconnect()
        }
    }*/

    suspend fun addNoteDto(dto: NoteDto): NoteDto = withContext(Dispatchers.IO) {
        val url = URL("http://10.0.2.2:8081/api/notes")
        val conn = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json")
            doOutput = true
        }
        try {
            val bodyJson: String = json.encodeToString<NoteDto>(dto)

            conn.outputStream.use { os ->
                os.write(bodyJson.toByteArray(Charsets.UTF_8))
            }
            val code = conn.responseCode
            val responseText = if (code in 200..299) {
                conn.inputStream.bufferedReader().readText()
            } else {
                val err = conn.errorStream.bufferedReader().readText()
                throw IOException("HTTP $code – $err")
            }
            json.decodeFromString<NoteDto>(responseText)
        } finally {
            conn.disconnect()
        }
    }

}