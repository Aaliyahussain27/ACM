package com.example.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile_card(){
    Scaffold(
        containerColor = Color(0xFF40677A),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF001524),
                    titleContentColor = Color.White
                    //Color(0xFFFCF5C7)
                ),
                title = {
                    Text(
                        text="Profile",
                        fontFamily = oldStandardTT
                        ) }
            )
        },
//        bottomBar = {
//            BottomAppBar { Text(text = " ") }
//        }
    ) { innerPadding ->
        Row(modifier = Modifier.padding(innerPadding)) {
            Image(
                painter = painterResource(id=R.drawable.profile_pic1),
                contentDescription = "Profile pic",
                modifier = Modifier
                    .padding(8.dp)
                    .size(180.dp,200.dp)
                    .clip(shape = RoundedCornerShape(18.dp))
            )

            Column {
                Text(modifier = Modifier
                    .padding(8.dp),
                    //fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    fontFamily = oldStandardTT,
                    color = Color.White,
                    text = "Aaliya Hussain")

                Text(modifier = Modifier
                    .padding(8.dp),
                    //fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = oldStandardTT,
                    color = Color.White,
                    text = "I am a passionate second-year Computer Engineering student, deeply involved in app development. She is an active member of her college’s Technical Team, ACM Chapter and Entrepreneurship Cell")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun karprac(){
    Profile_card()
}