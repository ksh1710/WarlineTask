package com.example.warelinetask.view.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.warelinetask.api.ImageRepo
import com.example.warelinetask.model.Photo

@Composable
fun PhotoCard(photo: Photo, repo: ImageRepo) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(photo.src.medium) {
        bitmap = repo.loadImageFromInternet(photo.src.medium)
    }

    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
    ) {
        bitmap?.let { it ->
            Image(
                bitmap = it.asImageBitmap(),
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}