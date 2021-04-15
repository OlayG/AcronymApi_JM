package com.example.acronymapi.repositories

import com.example.acronymapi.model.Lf
import com.example.acronymapi.util.Resource

interface AcronymRepository {

    suspend fun fetchAcronyms(query: String): Resource<List<Lf>>
}
