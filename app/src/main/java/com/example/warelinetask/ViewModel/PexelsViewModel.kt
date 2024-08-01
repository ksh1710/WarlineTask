package com.example.warelinetask.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warelinetask.api.ImageRepo
import com.example.warelinetask.model.Photo
import kotlinx.coroutines.launch

class PexelsViewModel(private val repository: ImageRepo) : ViewModel() {

    private val _photos = mutableStateOf<List<Photo>>(emptyList())
    val photos: State<List<Photo>> get() = _photos

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    private val perPage = 100

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val photos = repository.fetchCuratedPhotosFromApi(perPage)
                _photos.value = photos
            } catch (e: Exception) {
                Log.e("idk", "network request failed:${e.toString()}")
                _photos.value = repository.loadCachedImages()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
