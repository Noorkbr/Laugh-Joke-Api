package com.example.jokerjokes.service


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseJokes(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("delivery")
    val delivery: String? = null,
    @SerializedName("joke")
    val joke: String? = null,
    @SerializedName("error")
    val error: Boolean? = null,
    @SerializedName("flags")
    val flags: Flags? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("lang")
    val lang: String? = null,
    @SerializedName("safe")
    val safe: Boolean? = null,
    @SerializedName("setup")
    val setup: String? = null,
    @SerializedName("type")
    val type: String? = null
) {
    @Keep
    data class Flags(
        @SerializedName("explicit")
        val explicit: Boolean? = null,
        @SerializedName("nsfw")
        val nsfw: Boolean? = null,
        @SerializedName("political")
        val political: Boolean? = null,
        @SerializedName("racist")
        val racist: Boolean? = null,
        @SerializedName("religious")
        val religious: Boolean? = null,
        @SerializedName("sexist")
        val sexist: Boolean? = null
    )
}