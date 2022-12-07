package com.example.lab_4.ui.theme.compose

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lab_4.R

@Composable
fun settingsView()
{
    Column {
        settingItemWifi()
        generalSettings()
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {

    Column(modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth()) {
    settingsView()
        }
}

@Composable
fun generalSettings()
{
    val context = LocalContext.current
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.DarkGray,
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
                        context.resources.getString(R.string.readmsg) + " Settings",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row() {
                Icon(Icons.Rounded.Add,contentDescription = null)
                Text(text = stringResource(id = R.string.set_backg), style = MaterialTheme.typography.body1)
            }

            Row(modifier = Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth()) {
                Image(painter = painterResource(R.drawable.home), contentDescription = null,
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(10.dp))

                Image(painter = painterResource(R.drawable.n7), contentDescription = null,
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(10.dp))
                Image(painter = painterResource(R.drawable.home), contentDescription = null,
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(10.dp))

                Image(painter = painterResource(R.drawable.n5), contentDescription = null,
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(10.dp))

                Image(painter = painterResource(R.drawable.n8), contentDescription = null,
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(10.dp))

            }

            Button(onClick = {}) {
                Text(text = stringResource(id = R.string.set_img), modifier = Modifier.padding(top = 3.dp,start=5.dp, end = 5.dp, bottom = 3.dp), fontSize = 18.sp)
            }


        }

    }
}

@Composable
fun settingItemWifi()
{
    val context = LocalContext.current
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.DarkGray,
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
                        context.resources.getString(R.string.readmsg) + " Setting",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row() {
                Icon(Icons.Rounded.Settings,contentDescription = null)
                Text(text = stringResource(id = R.string.general_set), style = MaterialTheme.typography.body1, modifier = Modifier.padding(bottom = 12.dp))
            }
            Text(text = stringResource(id = R.string.protocol), style = MaterialTheme.typography.h6)
            Column() {
                Row {
                    checkSmtp()
                    Text(text = stringResource(id = R.string.smtp))
                }
                Row {
                    checkFTP()
                    Text(text = stringResource(id = R.string.ftp))
                }
                Row {
                    checkIMAP()
                    Text(text = stringResource(id = R.string.imap))
                }
            }
            Text(text = stringResource(id = R.string.synchr),style = MaterialTheme.typography.h6)
            Column() {
                Row(modifier = Modifier.padding(start = 40.dp, top = 30.dp)) {
                    Switch2()
                }
            }
        }

    }
}

@Composable
fun checkSmtp(): Boolean {
    var checkedState by rememberSaveable { mutableStateOf(true) }

    Checkbox(
        checked = checkedState,
        onCheckedChange = { checkedState = it }
    )
    return checkedState
}

@Composable
fun checkFTP(): Boolean {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    Checkbox(
        checked = checkedState,
        onCheckedChange = { checkedState = it }
    )
    return checkedState
}

@Composable
fun checkIMAP(): Boolean {
    var checkedState by rememberSaveable { mutableStateOf(true) }

    Checkbox(
        checked = checkedState,
        onCheckedChange = { checkedState = it }
    )
    return checkedState
}


//To implement this composable switch I Referred to the implementation from the following source
// https://stackoverflow.com/questions/67640056/jetpack-compose-switch-trackwidth-trackstrokewidth-thumbdiameter
@Composable
fun Switch2(
    scale: Float = 2f,
    width: Dp = 30.dp,
    height: Dp = 15.dp,
    strokeWidth: Dp = 2.dp,
    checkedTrackColor: Color = Color(0xFF8BC34A),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 4.dp
) {

    val switchON = remember {
        mutableStateOf(true) // Initially the switch is ON
    }

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON.value)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // This is called when the user taps on the canvas
                        switchON.value = !switchON.value
                    }
                )
            }
    ) {
        // Track
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )

        // Thumb
        drawCircle(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }

    Spacer(modifier = Modifier.height(18.dp))

    Text(text = if (switchON.value) "ON" else "OFF", style = MaterialTheme.typography.h6)
}


