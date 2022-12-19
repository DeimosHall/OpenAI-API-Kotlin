package com.deimos.openaiapi

import com.google.gson.annotations.SerializedName

data class CompletionResponse(
    @SerializedName("id") val id: String,
    @SerializedName("choices") val choices: List<Choices>
)