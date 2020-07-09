package dev.eastar.studypush.ui.profile

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun Hello() {
    Column {
        Text(text = "eastar")
        Text(text = "1234567")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StudypushTheme {
        Hello()
    }
}