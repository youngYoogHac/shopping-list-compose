package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Query("SELECT * FROM shopping_items")
    fun getItems(): Flow<List<ShoppingItemEntity>>

    @Insert
    suspend fun insert(
        item: ShoppingItemEntity
    )

    @Delete
    suspend fun delete(
        item: ShoppingItemEntity
    )
}