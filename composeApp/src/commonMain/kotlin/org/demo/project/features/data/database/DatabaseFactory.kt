package org.demo.project.features.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<FavoriteBookDatabase>
}