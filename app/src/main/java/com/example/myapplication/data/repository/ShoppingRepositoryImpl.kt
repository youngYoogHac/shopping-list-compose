package com.example.myapplication.data.repository

import com.example.myapplication.data.local.ShoppingDao
import com.example.myapplication.data.local.ShoppingItemEntity
import com.example.myapplication.domain.ShoppingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingRepositoryImpl(
    private val dao: ShoppingDao
) : ShoppingRepository {

    override fun getItems(): Flow<List<ShoppingItem>> {

        return dao.getItems().map { entities ->

            entities.map {

                ShoppingItem(
                    id = it.id,
                    title = it.title
                )
            }
        }
    }

    override suspend fun addItem(
        title: String
    ) {

        dao.insert(
            ShoppingItemEntity(
                title = title
            )
        )
    }

    override suspend fun deleteItem(
        item: ShoppingItem
    ) {

        dao.delete(
            ShoppingItemEntity(
                id = item.id,
                title = item.title
            )
        )
    }
}