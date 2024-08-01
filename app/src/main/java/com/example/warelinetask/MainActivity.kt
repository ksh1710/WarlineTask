package com.example.warelinetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.warelinetask.api.ImageRepo
import com.example.warelinetask.caching.DiskCache
import com.example.warelinetask.view.MainScreen
import com.example.warelinetask.ui.theme.WarelineTaskTheme


class MainActivity : ComponentActivity() {

    private lateinit var diskCache: DiskCache

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diskCache = DiskCache(this)
        enableEdgeToEdge()
        setContent {
            val repository = ImageRepo(context = this, diskCache = diskCache)
            WarelineTaskTheme {
                MainScreen(repo = repository)
            }
        }
    }
}
