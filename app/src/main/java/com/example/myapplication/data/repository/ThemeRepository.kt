package com.example.myapplication.data.repository

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    val isDarkTheme: Flow<Boolean>

    suspend fun setDarkTheme(
        enabled: Boolean
    )
}