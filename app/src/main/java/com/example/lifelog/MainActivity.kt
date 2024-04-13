package com.example.lifelog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifelog.ui.theme.LifeLogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifeLogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    NotepadLayout()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotepadLayout() {
// It will remember present cards counting we can increment it for card increasing
    var howManyCards by remember{ mutableIntStateOf(5) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Life Log")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Navigation button")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Increasing value so that we can add more notes when clicked on add icon
                            howManyCards++

                    }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Note")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CardViewList(cardList = List(howManyCards){"$it"})
        }
    }
}

@Composable
fun CardViewList(cardList: List<String>) {
    for (list in cardList){
        CardView(list)
    }
}

@Composable
fun CardView(noteNumber: String) {
    Surface (modifier = Modifier.padding(horizontal = 16.dp)){
        Card (modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ){
            Text(modifier = Modifier.padding(24.dp),
                text = "Note $noteNumber"
            )
        }
    }
}

@Preview()
@Composable
fun NotepadPreview() {
    MyApp()
}

@Preview()
@Composable
fun CardPreview() {
    LifeLogTheme {
        CardView("1")
    }
}


