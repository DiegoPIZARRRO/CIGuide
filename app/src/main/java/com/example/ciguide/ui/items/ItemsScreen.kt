package com.example.ciguide.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ciguide.Data.Model.Item
import com.example.ciguide.Data.Repository.ItemRepository

@Composable
fun itemsScreen(){
    val repository = ItemRepository()
    val items = repository.getitems()
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){items(items){item -> itemCard(item = item)}}
}

@Composable
fun itemCard(item: Item){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical= 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Image(painter = painterResource(id=item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )
        Column(modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 20.sp
            )
        }

    }
}