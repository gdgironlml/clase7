package com.example.clase7.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import androidx.compose.ui.platform.LocalContext
import com.example.clase7.R
import com.example.clase7.utils.validateEmail
import com.example.clase7.utils.validatePassword

@Composable
fun LoginScreen(navController: NavController){

    val auth = Firebase.auth

    var stateEmail by remember {mutableStateOf("")}
    var statePassword by remember {mutableStateOf("")}

    var stateMessage by remember {mutableStateOf("")}

    var emailMessage by remember {mutableStateOf("")}
    var passwordMessage by remember {mutableStateOf("")}

    val activity = LocalView.current.context as Activity

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =Arrangement.Center
    ){

        Image(
            imageVector = Icons.Filled.Person,
            contentDescription = "User icon",
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = stringResource(R.string.login_screen_text),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0066B3)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = stateEmail,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "email icon"
                )
            },
            onValueChange = {stateEmail = it},
            label = {Text(stringResource(R.string.fields_email))},
            supportingText = {
                if (emailMessage.isNotEmpty()){
                    Text(
                        text=emailMessage,
                        color=Color.Red
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = statePassword,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "password icon"
                )
            },
            onValueChange = {statePassword = it},
            label = {Text(stringResource(R.string.fields_password))},
            supportingText = {
                if (passwordMessage.isNotEmpty()){
                    Text(
                        text=passwordMessage,
                        color = Color.Red
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                val emailValidation = validateEmail(context, stateEmail)
                val passwordValidation = validatePassword(context, statePassword)

                emailMessage = emailValidation.second
                passwordMessage = passwordValidation.second

                if (emailValidation.first && passwordValidation.first){
                    auth.signInWithEmailAndPassword(stateEmail, statePassword)
                        .addOnCompleteListener (activity) {
                                task ->
                            stateMessage = if (task.isSuccessful){
                                navController.navigate("logSuccess")
                                ""
                            } else {
                                "Fallo el inicio de sesion"
                            }
                        }
                }else {
                    val combinedValidation = emailValidation.second + " " + passwordValidation.second
                    Toast.makeText(activity, combinedValidation, Toast.LENGTH_SHORT)
                        .show()
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC9252B),
                contentColor = Color.White
            )
        ){
            Text(stringResource(R.string.login_screen_login_button) )
        }
        Text(
            text = stateMessage
        )
        Button(
            onClick = {navController.navigate("register")},
            colors =ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEAB1A7),
                contentColor = Color.White
            )
        ){
            Text(stringResource(R.string.login_screen_register_button))
        }
    }
}