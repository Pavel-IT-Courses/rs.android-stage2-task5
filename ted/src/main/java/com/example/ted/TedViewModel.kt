package com.example.ted

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ted.model.data.TedPresentation
import kotlinx.coroutines.launch

class TedViewModel: ViewModel() {
    private val _items = MutableLiveData<List<TedPresentation>>()
    val items: LiveData<List<TedPresentation>> get() = _items

    init {
        viewModelScope.launch {
            _items.value = TedApiImpl.getListOfPresentations()
        }
    }
}
