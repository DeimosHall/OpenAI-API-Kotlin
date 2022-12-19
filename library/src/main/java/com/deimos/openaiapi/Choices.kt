package com.deimos.openaiapi

import com.google.gson.annotations.SerializedName

data class Choices(
    @SerializedName("text") val text: String,
    @SerializedName("index") val index: Int,
    @SerializedName("logprobs") val logprobs: String,
    @SerializedName("finish_reason") val finishReason: String
)