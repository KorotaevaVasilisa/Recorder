package com.example.recorder.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recorder.R

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val list = listOf<String>("1", "2", "3")
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.your_notes),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp
        )
        LazyColumn(modifier = modifier
            .fillMaxWidth()
            .height(700.dp)
            .padding(top = 8.dp)) {
            items(list.size) { item ->
                Text(text = list[item])
            }
        }
        FloatingActionButton(
            modifier = modifier.align(Alignment.CenterHorizontally).size(70.dp),
            onClick = { /*do something*/ }) {
            Icon(painter = painterResource(id = R.drawable.mic),
                contentDescription = stringResource(id = R.string.microphone))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ShowScreen() {
    MainScreen()
}
