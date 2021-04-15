package com.example.acronymapi.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Acronym(
    val lfs: List<Lf>,
    val sf: String
)