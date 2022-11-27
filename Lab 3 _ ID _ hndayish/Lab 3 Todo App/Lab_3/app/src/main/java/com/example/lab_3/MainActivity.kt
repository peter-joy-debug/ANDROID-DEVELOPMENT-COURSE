package com.example.lab_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lab_3.ui.compose.TaskScreen
import com.example.lab_3.ui.theme.TaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ScreenSetup()
                }
            }
        }
    }

    @Composable
    fun ScreenSetup(){
        TaskScreen()
    }
}