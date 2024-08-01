package com.example.warelinetask.api

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.example.warelinetask.caching.DiskCache
import com.example.warelinetask.caching.ImageCache
import com.example.warelinetask.localDb.CacheDb
import com.example.warelinetask.localDb.PhotoEntity
import com.example.warelinetask.model.ApiImage
import com.example.warelinetask.model.Photo
import com.example.warelinetask.model.Src
import com.example.warelinetask.view.components.loadImageFromUrl


class ImageRepo(private val context: Context, private val diskCache: DiskCache) {

    suspend fun fetchCuratedPhotosFromApi(perPage: Int = 10): List<Photo> {
        val photos = RetrofitInstance.api.getPhotosFromPexelsApi(perPage).photos
        savePhotosToLocalDatabase(photos)
        return photos
    }

    private val photoDao = CacheDb.getDatabase(context = context).photoDao()

    ///caching to local db
    private suspend fun savePhotosToLocalDatabase(photos: List<Photo>) {
        val photoEntities = photos.map { PhotoEntity(it.id, it.src.medium) }
        photoDao.insertAll(photoEntities)
    }

    //offline image loading cache se
    suspend fun loadCachedImages(): List<Photo> {
        val photoEntities = photoDao.getAllPhotos()
        return photoEntities.map { Photo(id = it.id, src = Src(medium = it.url)) }
    }

    suspend fun loadImageFromInternet(url: String): Bitmap? {

        //to check the memory cache
        val cacheKey = url.hashCode().toString()

        var bitmap = ImageCache.getBitmapFromMemCache(cacheKey)
        if (bitmap != null) {
            Log.d("idk", "Memory cache hit for key: $cacheKey")
            return bitmap
        }

        //to check the disk cache
        bitmap = diskCache.getBitmapFromDiskCache(cacheKey)
        if (bitmap != null) {
            Log.d("idk", "Disk cache hit for key: $cacheKey")
            ImageCache.addBitmapToMemoryCache(cacheKey, bitmap)
            return bitmap
        }

        //to load image from the network
        Log.d("idk", "Loading image from network for url: $url")
        bitmap = loadImageFromUrl(url)
        if (bitmap != null) {
            Log.d("idk", "Caching loaded image with key: $cacheKey")
            ImageCache.addBitmapToMemoryCache(cacheKey, bitmap)
            diskCache.addBitmapToDiskCache(cacheKey, bitmap)
        }
        return bitmap
    }
}
