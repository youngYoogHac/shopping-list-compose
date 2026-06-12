package com.example.myapplication.data.repository

import com.example.myapplication.domain.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {

    fun getItems(): Flow<List<ShoppingItem>>

    suspend fun addItem(
        title: String
    )

    suspend fun deleteItem(
        item: ShoppingItem
    )
}