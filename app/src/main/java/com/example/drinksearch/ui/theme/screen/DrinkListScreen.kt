package com.example.drinksearch.ui.theme.screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drinksearch.R
import com.example.drinksearch.model.DrinkData
import com.example.drinksearch.network.DrinkService
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrinkListScreen(scaffoldState: ScaffoldState,lazyScroll:ScrollState, navController: NavController, coroutineScope: CoroutineScope, drinkApi: DrinkService, drinks: MutableState<List<DrinkData>>) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { Search(scope = coroutineScope,drinkApi = drinkApi, drinks = drinks, scrollState = lazyScroll) }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            DrinkList(navController = navController, scrollState = lazyScroll, drinkList = drinks.value)
        }
    }
}

@Composable
fun DrinkList(navController: NavController, scrollState: ScrollState, drinkList: List<DrinkData>) {
    Box(
        modifier = Modifier
            .padding(6.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .scrollable(scrollState, Orientation.Vertical)
        ) {
            items(drinkList) {
                    drinkData->
                DrinkListItem(drinkData = drinkData, onDrinkClick = {
                    navController.navigate("Detail/${drinkData.idDrink}")
                })
            }
        }
    }
}

@Composable
fun DrinkListItem(drinkData: DrinkData, onDrinkClick: (DrinkData) -> Unit = {}) {
    Box(modifier = Modifier.padding(10.dp)
    )
     {
        Card(shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .requiredHeight(120.dp)
                .clickable {
                    onDrinkClick(drinkData)
                },
            elevation = 6.dp,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 7.dp, end = 7.dp)
            ) {
//
                CoilImage(
                imageModel = drinkData.strDrinkThumb,
                contentScale = ContentScale.Crop,
                error = ImageBitmap.imageResource(R.drawable.drink),
                placeHolder = ImageBitmap.imageResource(R.drawable.drink),
                    modifier = Modifier
                        .size(width = 250.dp, height = 100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .weight(3.0f)
            )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .weight(7.0f)
                ) {
                    Text(text = drinkData.strDrink, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Start,modifier = Modifier.padding(top = 8.dp, start = 8.dp), fontFamily = FontFamily.SansSerif)
                    Text(text = drinkData.strCategory, fontSize = 16.sp, textAlign = TextAlign.Start,modifier = Modifier.padding(top = 8.dp, start = 8.dp), fontFamily = FontFamily.Default)
                    Text(text = drinkData.strAlcoholic, fontSize = 16.sp, textAlign = TextAlign.Start,modifier = Modifier.padding(top = 8.dp, start = 8.dp),fontFamily = FontFamily.Monospace)

                }
            }
        }
    }
}

@Composable
fun Search(scope: CoroutineScope, drinkApi: DrinkService, drinks: MutableState<List<DrinkData>>, scrollState: ScrollState) {

    var drinkState by remember {
        mutableStateOf("")
    }

    Column {

        val context = LocalContext.current

        Text(text = "DrinkSearch", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp,start = 20.dp), color = Color.Black, fontFamily = FontFamily.Serif)

        androidx.compose.material.TextField(
            placeholder = { Text(text = "Search Drink",
                fontSize = 18.sp,
            ) },
            value = drinkState,
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.hsl(88f,.77f,.78f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                textColor = Color.Black,
            ),
            onValueChange = { drinkState = it },
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                .fillMaxWidth()
                .requiredHeight(60.dp),
            leadingIcon = {
                Image(painter = painterResource(id = R.drawable.drink),
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .size(24.dp)
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (drinkState.isNotEmpty()) {
                        makeReq(scope, drinkApi, drinks, drinkState, scrollState,context)
                    } else {
                        makeToast(context, "Please enter a drink")
                    }
                   }) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            },
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
            singleLine = true
        )
    }


}


fun makeReq(scope: CoroutineScope, drinkApi: DrinkService, drinks: MutableState<List<DrinkData>>, drinkState: String, scrollState: ScrollState, context: Context){
    scope.launch {
        val result = drinkApi.getDrinksByName(drinkState)
        drinks.value = result.body()?.drinks ?: emptyList()
        scrollState.animateScrollTo(0)
        if (drinks.value.isEmpty()) {
            makeToast(context, "No drinks found")
        }
    }

}


fun makeToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}