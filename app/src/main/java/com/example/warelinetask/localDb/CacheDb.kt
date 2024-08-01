package com.example.warelinetask.localDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhotoEntity::class], version = 1)
abstract class CacheDb : RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile private var INSTANCE: CacheDb? = null

        fun getDatabase(context: Context): CacheDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CacheDb::class.java,
                    "cache_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
