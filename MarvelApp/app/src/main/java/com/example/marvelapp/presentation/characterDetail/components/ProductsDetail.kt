package com.example.marvelapp.presentation.characterDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelapp.domain.models.Product

@Composable
fun ProductsDetail(
    sectionTitle: String,
    productsList: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        Text(
            text = sectionTitle,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 8.dp)
        )
        LazyRow {
            items(if (productsList.isNotEmpty()) productsList.size else 3) {
                ShimmerProductListItem(
                    isLoading = productsList.isEmpty(),
                    contentAfterLoading = {
                        ProductItem(
                            productsList[it],
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}