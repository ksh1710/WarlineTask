package com.example.warelinetask.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.warelinetask.api.ImageRepo

class PexelsViewModelFactory(private val repository: ImageRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PexelsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PexelsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
