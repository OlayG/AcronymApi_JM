package com.example.acronymapi.repositories

import com.example.acronymapi.data.remote.model.Acronym
import com.example.acronymapi.util.Resource

interface AcronymRepository {

    suspend fun fetchAcronyms(query: String) : Resource<List<Acronym>?>

}