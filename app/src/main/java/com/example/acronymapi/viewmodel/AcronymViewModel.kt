package com.example.acronymapi.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymapi.model.Lf
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

    private val _acronym = MutableLiveData<Resource<List<Lf>>>()
    val acronym: LiveData<Resource<List<Lf>>> get() = _acronym

    /**
     * 2 way binding to observer user input
     */
    var query: ObservableField<String> = ObservableField("")

    /**
     * Callback function used to listen to trigger actions
     * Button - OnClickListener
     * TextInputEditText - setOnEditorActionListener
     */
    val fetch: Function0<Unit> = this::fetchAcronyms

    /**
     * Method to fetch user query update liveData
     */
    private fun fetchAcronyms() {
        _acronym.postValue(Resource.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            query.get()?.let { _acronym.postValue(acronymRepository.fetchAcronyms(it)) }
        }
    }
}
