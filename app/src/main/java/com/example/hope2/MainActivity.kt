package com.example.hope2

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hope2.ui.theme.Hope2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hope2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // ProfilePage()
                    Greeting(name = "Hello World!")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var nameState by remember {
        mutableStateOf("")
    }
    var name by rememberSaveable {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center) {
        // Text
        Text(text = "Hello $name")
        // Spacer
        Spacer(modifier = Modifier.height(20.dp))
        // Text Field
        TextField(value = nameState, onValueChange = {
            nameState = it
        })
        // Button
        Button(onClick = {
            name = nameState
        }) {
            Text(text = "Display")
        }
    }
}

@Composable
fun MyApp() {
    Text(text = "Welcome to the World!",
        color = Color.Blue)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting(name = "Hello World!")
}