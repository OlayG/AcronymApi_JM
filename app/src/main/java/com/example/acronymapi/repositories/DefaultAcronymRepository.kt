package com.example.acronymapi.repositories

import com.example.acronymapi.model.Lf
import com.example.acronymapi.remote.AcronymService
import com.example.acronymapi.util.Resource
import javax.inject.Inject

class DefaultAcronymRepository @Inject constructor(
    private val acronymService: AcronymService
) : AcronymRepository {

    override suspend fun fetchAcronyms(query: String): Resource<List<Lf>> {
        return try {
            acronymService.fetchAcronyms(query).body()?.firstOrNull()?.lfs?.let {
                Resource.Success(it)
            } ?: Resource.Error("No results")
        } catch (e: Exception) {
            Resource.Error("Couldn't reach the server for some reason")
        }
    }
}
