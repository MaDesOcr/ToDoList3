package com.dev.todolist3.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@OptIn(InternalSerializationApi::class)
object QuoteApi {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getQuote(): String = withContext(Dispatchers.IO) {
        val url = URL("http://10.0.2.2:8080/citation/")
        val connection = url.openConnection() as HttpURLConnection
        try {
            val text = connection.inputStream.bufferedReader().readText()
            val model = json.decodeFromString<QuoteModel>(text)
            model.content
        } finally {
            connection.disconnect()
        }
    }
}
