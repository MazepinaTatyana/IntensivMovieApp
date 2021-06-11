package ru.androidschool.intensiv.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.logging.HttpLoggingInterceptor

class CustomHttpLogging : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        val logName = "OkHttp"
        if (!message.startsWith("{")) {
            Log.d(logName, message)
            return
        }
        try {
            val prettyPrintJson = GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(JsonParser.parseString(message))
            Log.d(logName, prettyPrintJson)
        } catch (e: Exception) {
            Log.d(logName, message)
        }
    }
}
