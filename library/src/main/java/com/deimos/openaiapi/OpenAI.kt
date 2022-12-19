package com.deimos.openaiapi

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenAI(private val apiKey: String) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val openAIService = retrofit.create(OpenAIService::class.java)

    suspend fun createCompletion(
        model: String,
        prompt: String,
        temperature: Double,
        max_tokens: Int,
        top_p: Int,
        frequency_penalty: Double,
        presence_penalty: Double,
        stop: List<String>
    ): Response<CompletionResponse> {
        val request = CompletionRequest(
            model,
            prompt,
            temperature,
            max_tokens,
            top_p,
            frequency_penalty,
            presence_penalty,
            stop
        )

        return openAIService.completions(apiKey = apiKey, request = request)
    }
}