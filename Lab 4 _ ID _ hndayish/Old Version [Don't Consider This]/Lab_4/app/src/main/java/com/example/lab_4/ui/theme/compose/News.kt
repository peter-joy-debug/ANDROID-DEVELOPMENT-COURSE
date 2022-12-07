package com.example.lab_4.ui.theme.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lab_4.R
import com.example.lab_4.model.NewsDataModel
import com.example.lab_4.model.newsData


@Composable
fun NewsScreen(navController: NavHostController) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.padding(bottom = 50.dp),
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp)
    )
    {
        items(newsData){ news ->
            NewsItem(item = news, context)
        }
    }
}

@Composable
fun NewsItem(item: NewsDataModel, context: Context) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(2.dp, color = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .padding(8.dp)
            .padding(bottom = 2.dp)
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(
                        context,
                        context.resources.getString(R.string.readmsg) + " " + item.title,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(painter = painterResource(item.imgUrl), contentDescription = null,Modifier.height(290.dp).width(390.dp))
            Text(text = item.title, style = MaterialTheme.typography.h6)
            Text(text = item.desc, style = MaterialTheme.typography.body1)
            Text(text = stringResource(id = R.string.read_more), style = MaterialTheme.typography.h6, color = Color.Yellow)
        }

    }
}
