package com.example.myapplication.presentation.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.ShoppingRepository
import com.example.myapplication.domain.ShoppingItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    val items: StateFlow<List<ShoppingItem>> =
        repository.getItems()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun addItem(
        title: String
    ) {

        viewModelScope.launch {

            repository.addItem(title)
        }
    }

    fun deleteItem(
        item: ShoppingItem
    ) {

        viewModelScope.launch {

            repository.deleteItem(item)
        }
    }
}