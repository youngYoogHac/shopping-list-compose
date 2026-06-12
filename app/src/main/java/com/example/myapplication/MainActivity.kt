package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.room.Room
import com.example.myapplication.data.datastore.dataStore
import com.example.myapplication.data.local.ShoppingDatabase
import com.example.myapplication.data.repository.ShoppingRepositoryImpl
import com.example.myapplication.data.repository.ThemeRepositoryImpl
import com.example.myapplication.presentation.shopping.ShoppingScreen
import com.example.myapplication.presentation.shopping.ShoppingViewModel
import com.example.myapplication.presentation.theme.ThemeViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        val database =
            Room.databaseBuilder(
                applicationContext,
                ShoppingDatabase::class.java,
                "shopping_db"
            ).build()

        val shoppingRepository =
            ShoppingRepositoryImpl(
                database.shoppingDao()
            )

        val themeRepository =
            ThemeRepositoryImpl(
                applicationContext.dataStore
            )

        val shoppingViewModel =
            ShoppingViewModel(
                shoppingRepository
            )

        val themeViewModel =
            ThemeViewModel(
                themeRepository
            )

        setContent {

            val isDarkTheme by
            themeViewModel
                .isDarkTheme
                .collectAsState()

            MyApplicationTheme(
                darkTheme = isDarkTheme
            ) {

                ShoppingScreen(
                    viewModel = shoppingViewModel,
                    isDarkTheme = isDarkTheme,
                    onThemeChanged = {
                        themeViewModel.changeTheme(it)
                    }
                )
            }
        }
    }
}