package com.example.honore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            HelloWorldTheme {
            // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
            Greeting()
//                }
//            }
        }
    }
}

@Composable
fun MessageText(newName: String)
{
    if(newName.isNotEmpty())
    {
        Text(
            text = stringResource(id = R.string.greeting) + " " + newName,
            color = Color.Green,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun SayHi(clicked:()->Unit)
{
    Button(onClick = clicked) {
        Text(text = stringResource(id = R.string.buttonHello))

    }
}

@Composable
fun NameTextField(name: String, changed: (String)->Unit)
{
    TextField(value = name, onValueChange = changed, label = { Text(stringResource(id = R.string.enterName))},

        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp))
}
@Composable
fun ImageMethod(imgValue: Int)
{
    Image(painter = painterResource(id = imgValue), contentDescription = stringResource(
        id = R.string.greeting),
        modifier = Modifier
            .padding(top = 40.dp, bottom = 40.dp)
            .size(190.dp)
            .clip(shape = CircleShape)
    )
}


@Composable
fun Greeting() {
//    Text(text = "Hello $name!"
    var photoOne by remember { mutableStateOf(R.drawable.bulb) }
    val name =remember { mutableStateOf("") }
    val textFieldName = remember { mutableStateOf(value = "") }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        NameTextField(name = textFieldName.value, changed ={textFieldName.value=it} )

        SayHi({
            name.value=textFieldName.value
            photoOne=R.drawable.pineapple
        })
        ImageMethod(photoOne)
//        Image(painter = painterResource(id = R.drawable.bulb), contentDescription = stringResource(
//            id = R.string.scotty),
//            modifier = Modifier
//                .padding(top = 40.dp, bottom = 40.dp)
//                .size(190.dp)
//                .clip(shape = CircleShape)
//                .align(Alignment.CenterHorizontally)
//
//        )
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Black)
        ) {
//            Text(text = stringResource(id=R.string.greeting)+" "+ stringResource(id = R.string.scotty),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Red),
//                color = Color.Green,
//                fontSize = 24.sp,
//                textAlign = TextAlign.Center)

            MessageText(name.value)

        }


    }

}

@Preview
//    (showBackground = true)
@Composable
fun DefaultPreview() {
//    HelloWorldTheme {
    Greeting()
    }
