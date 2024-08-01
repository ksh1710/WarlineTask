package com.example.warelinetask.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.warelinetask.view.components.CustomTopBar
import com.example.warelinetask.view.components.PhotoGrid
import com.example.warelinetask.ViewModel.PexelsViewModel
import com.example.warelinetask.ViewModel.PexelsViewModelFactory
import com.example.warelinetask.api.ImageRepo


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier, repo: ImageRepo) {
    val factory = PexelsViewModelFactory(repo)
    val vm: PexelsViewModel = viewModel(factory = factory)
    val photos by vm.photos
    val loading by vm.isLoading

    Scaffold(
        topBar = {
            CustomTopBar()
        }
    ) { it ->
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center),
                    color = Color.Red,
                    strokeWidth = 6.dp
                )
            } else if (photos.isEmpty()) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No images available. Please check your internet connection.",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Icon(imageVector = Icons.Default.Error, contentDescription =null )
                }
            } else {
                PhotoGrid(images = photos, repo = repo)
            }
        }
    }
}
