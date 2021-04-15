package com.example.acronymapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymapi.data.remote.model.Acronym
import com.example.acronymapi.repositories.AcronymRepository
import com.example.acronymapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val acronymRepository: AcronymRepository
) : ViewModel() {

    private val _acronym = MutableLiveData<Resource<List<Acronym>?>>()
    val acronym : LiveData<Resource<List<Acronym>?>> get() = _acronym

    fun fetchAcronyms(query: String) {
        _acronym.postValue(Resource.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val response = acronymRepository.fetchAcronyms(query)
            _acronym.postValue(response)
        }
    }
}
