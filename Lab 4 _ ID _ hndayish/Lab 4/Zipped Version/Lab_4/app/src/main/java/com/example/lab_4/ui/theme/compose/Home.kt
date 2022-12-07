package com.example.lab_4.ui.theme.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lab_4.R
import com.example.lab_4.ui.theme.compose.navigation.ScreenDestinations

@Composable
fun newsButton(navController: NavController)
{
    Button(onClick = { navController.navigate("news_screen") }) {
        Text(text = stringResource(id = R.string.news_check), modifier = Modifier.padding(top = 20.dp,start=70.dp, end = 70.dp, bottom = 20.dp), fontSize = 20.sp)
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()
        .padding(20.dp)) {
        Card(
        elevation = 50.dp,

        ) {
        Image(painter = painterResource(id = R.drawable.home), contentDescription = null)
        Column(modifier = Modifier
            .padding(start = 18.dp, top = 80.dp)
            .background(
                color = MaterialTheme.colors.onSecondary,
                shape = MaterialTheme.shapes.medium
            )) {
        Text(text = stringResource(id = R.string.welcome_msg), style = MaterialTheme.typography.h6,color = Color.White,textAlign = TextAlign.Center, modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 5.dp, end = 5.dp))

        }
            }
        Column(modifier = Modifier.padding(top = 60.dp)) {
//            newsButton(navController)

            // This button was causing issues during the navigation
            
        }
}
}