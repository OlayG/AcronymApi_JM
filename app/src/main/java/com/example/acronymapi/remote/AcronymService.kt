package com.example.acronymapi.remote

import com.example.acronymapi.model.Acronym
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    @GET("software/acromine/dictionary.py")
    suspend fun fetchAcronyms(@Query("sf") query: String): Response<List<Acronym>>
}