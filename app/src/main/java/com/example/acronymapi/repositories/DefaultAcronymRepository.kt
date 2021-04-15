package com.example.acronymapi.repositories

import com.example.acronymapi.data.remote.AcronymService
import com.example.acronymapi.data.remote.model.Acronym
import com.example.acronymapi.util.Resource
import javax.inject.Inject

class DefaultAcronymRepository @Inject constructor(
    private val acronymService: AcronymService
) : AcronymRepository {

    override suspend fun fetchAcronyms(query: String): Resource<List<Acronym>?> {
        return try {
            val response = acronymService.fetchAcronyms(query)
            return if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                Resource.Success(response.body())
            } else {
                Resource.Error("No results", null)
            }
        } catch (e : Exception) {
                Resource.Error("Couldn't reach the server for some reason", null)
        }
    }
}