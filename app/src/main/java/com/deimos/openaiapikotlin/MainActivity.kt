package com.deimos.openaiapikotlin

import MY_API_KEY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.deimos.openaiapi.OpenAI
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val API_KEY = "Bearer $MY_API_KEY"
        val openAI = OpenAI(API_KEY)
        var prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever, and very friendly."
        val question = "What is your name?"
        prompt += "\n\nHuman: $question \nAI:" // This is to hold the context in the conversation

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = openAI.createCompletion(
                    model = "text-davinci-003",
                    prompt = prompt,
                    temperature = 0.9,
                    max_tokens = 150,
                    top_p = 1,
                    frequency_penalty = 0.0,
                    presence_penalty = 0.6,
                    stop = listOf(" Human:", " AI:")
                )

                if (response.isSuccessful) {
                    Log.d("RESPONSE", "Response: ${response.body()?.choices?.first()?.text}")
                } else {
                    Log.d("RESPONSE", "Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("RESPONSE", "Error: $e")
            }
        }
    }
}