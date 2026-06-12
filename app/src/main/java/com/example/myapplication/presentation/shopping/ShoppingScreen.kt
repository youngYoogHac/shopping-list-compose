package com.example.myapplication.presentation.shopping

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.ShoppingItem

@Composable
fun ShoppingScreen(
    viewModel: ShoppingViewModel,
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit
) {

    val items by viewModel.items.collectAsState()

    var text by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Список покупок",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Темная тема",
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = onThemeChanged
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier.weight(1f),
                    label = {
                        Text("Товар")
                    }
                )

                Button(
                    onClick = {
                        viewModel.addItem(text)
                        text = ""
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Добавить")
                }
            }

            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(items) { item ->

                    ShoppingItemCard(
                        item = item,
                        onDelete = {
                            viewModel.deleteItem(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ShoppingItemCard(
    item: ShoppingItem,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Text(
                text = item.title,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = onDelete
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить"
                )
            }
        }
    }
}