package com.example.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewmodel: LoginViewmodel= viewModel(),
    onNavigate: () -> Unit
){
    val username = loginViewmodel.username.value
    var password = loginViewmodel.password.value
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier= Modifier
        .fillMaxSize()
        .background(Color(0xFF0E141C))) {

        Text(text = "LOGIN",
            textAlign = TextAlign.Center,
            fontSize = 34.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = oldStandardTT,
            color = Color(0xFF607EA2)
            )
        OutlinedTextField(
            value = username,
            onValueChange = {loginViewmodel.UsernameChange(it)},
            label = {Text("Enter your Username")},
            singleLine = true,
            colors= TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.LightGray,
                unfocusedTextColor = Color.Gray
            ),
            modifier = Modifier.padding(12.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {loginViewmodel.PasswordChange(it)},
            label = {Text("Enter your Password")},
            singleLine=true,
            visualTransformation = PasswordVisualTransformation(),
            colors= TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.LightGray,
                unfocusedTextColor = Color.Gray
            ),
            modifier = Modifier.padding(12.dp)
        )

        ElevatedButton(modifier=Modifier
            .padding(4.dp),
            onClick = {loginViewmodel.onClick()
                      onNavigate()},
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF607EA2)
            )
        )
            {
            Text(text="Login",
                fontFamily = oldStandardTT,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}