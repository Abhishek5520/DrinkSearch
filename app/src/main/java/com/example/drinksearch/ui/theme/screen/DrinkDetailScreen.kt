package com.example.drinksearch.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.drinksearch.R
import com.example.drinksearch.model.DrinkData
import com.skydoves.landscapist.coil.CoilImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrinkDetailScreen(drink: DrinkData,scrollState: ScrollState = rememberScrollState(), navController: NavController) {
    Scaffold(topBar = {
        DetailTopAppBar(onBackPressed = {navController.popBackStack()},drink = drink)
    }){
        Card(shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            elevation = 8.dp
            ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoilImage(
                    imageModel = drink.strDrinkThumb,
                    contentScale = ContentScale.Crop,
                    error = ImageBitmap.imageResource(R.drawable.drink),
                    placeHolder = ImageBitmap.imageResource(R.drawable.drink),
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .fillMaxWidth()
                        .clip(shape = MaterialTheme.shapes.medium)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = drink.strCategory?:"Unknown", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp))
                    Text(text = drink.strAlcoholic.uppercase()?:"Unknown", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = drink.strGlass, fontWeight = FontWeight.SemiBold)
                    Text(text = drink.strIBA?:"", fontWeight = FontWeight.SemiBold)

                }
                Text(text = "Ingredients", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))
                if (drink.strIngredient1 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient1?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure1?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient2 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient2?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure2?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient3 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient3?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure3?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient4 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient4?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure4?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient5 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient5?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure5?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient6 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient6?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure6?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient7 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient7?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure7?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient8 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient8?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure8?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient9 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient9?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure9?:"", fontWeight = FontWeight.Medium)

                    }
                }
                if (drink.strIngredient10 != null){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = drink.strIngredient10?:"", fontWeight = FontWeight.Medium)
                        Text(text = drink.strMeasure10?:"", fontWeight = FontWeight.Medium)

                    }
                }

                Text(text = "Instructions", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))
                Text(text = drink.strInstructions, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 8.dp))
            }
        }

    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailTopAppBar(onBackPressed: () -> Unit = {},drink: DrinkData) {
    Card(shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        elevation = 8.dp,
    ){
        TopAppBar(title = { Text(text = drink.strDrink, fontWeight = FontWeight.SemiBold) },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.hsl(143f,.63f,.49f)
        )
    }

}
