package com.deimos.openaiapi

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIService {
    @POST("completions")
    suspend fun completions(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") apiKey: String,
        @Body request: CompletionRequest
    ): Response<CompletionResponse>
}