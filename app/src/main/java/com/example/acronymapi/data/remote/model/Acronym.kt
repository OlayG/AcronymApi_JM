package com.example.acronymapi.data.remote.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Acronym(
    val lfs: List<Lf>,
    val sf: String
)