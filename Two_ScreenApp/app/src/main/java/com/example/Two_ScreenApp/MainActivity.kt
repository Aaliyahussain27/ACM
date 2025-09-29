package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import com.example.practice.ui.theme.PracticeTheme

val oldStandardTT = FontFamily(
    Font(R.font.oldstandardtt_regular, weight = FontWeight.Normal),
    Font(R.font.oldstandardtt_bold, weight = FontWeight.Bold)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
