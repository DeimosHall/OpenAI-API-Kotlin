package com.deimos.openaiapikotlin

import MY_API_KEY
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.deimos.openaiapi.OpenAI
import com.deimos.openaiapikotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val API_KEY = "Bearer $MY_API_KEY" // Replace MY_API_KEY with your own key and keep the word Bearer
        val openAI = OpenAI(API_KEY)
        // You can change the prompt, this is what we find in the chat example from the OpenAI page
        var prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever, and very friendly."

        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString()
            deleteTVMessage()
            hideKeyboard()

            CoroutineScope(Dispatchers.IO).launch {
                prompt += "\n\nHuman: $message \nAI:"

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
                        var answer = response.body()?.choices?.first()?.text
                        answer = answer?.trimStart() // Delete the first space from the answer

                        runOnUiThread {
                            binding.tvResponse.text = answer
                        }
                    } else {
                        Log.d("RESPONSE", "Error: ${response.code()} ${response.message()}")
                    }
                } catch (e: Exception) {
                    Log.d("RESPONSE", "Error: $e")
                }
            }
        }

        binding.tvResponse.setOnClickListener { hideKeyboard() }
    }

    private fun deleteTVMessage() {
        binding.etMessage.setText("")
    }

    private fun hideKeyboard() {
        val activityView = this.window.decorView
        val imm = activityView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activityView.windowToken, 0)
    }
}