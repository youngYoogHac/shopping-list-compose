package com.example.myapplication.presentation.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.ThemeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val repository: ThemeRepository
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> =
        repository.isDarkTheme.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    fun changeTheme(
        enabled: Boolean
    ) {

        viewModelScope.launch {

            repository.setDarkTheme(
                enabled
            )
        }
    }
}