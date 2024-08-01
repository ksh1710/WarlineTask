package com.example.warelinetask.caching

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileOutputStream


class DiskCache(private val context: Context) {

    private val cacheDir = File(context.cacheDir, "images").apply { mkdirs() }

    fun getBitmapFromDiskCache(key: String): Bitmap? {
        val file = File(cacheDir, key)
        return if (file.exists()) {
            Log.d("idk", "Cache hit for key: $key")
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            Log.d("idk", "Cache miss for key: $key")
            null
        }
    }

    fun addBitmapToDiskCache(key: String, bitmap: Bitmap) {
        val file = File(cacheDir, key)
        if (!file.exists()) {
            Log.d("idk", "Caching bitmap with key: $key")
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
            }
        }else{
            Log.d("idk", "Bitmap already cached with key: $key")

        }
    }
}
