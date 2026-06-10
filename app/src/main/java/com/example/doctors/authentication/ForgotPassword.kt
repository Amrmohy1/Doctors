package com.example.doctors.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doctors.R
import com.example.doctors.Routes

@Composable
fun ForgotPassword(navController: NavController,modifier: Modifier = Modifier,viewModel: AuthViewModel= viewModel()) {

    var email by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf<String?>(null) }
    val authState by viewModel.authState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.PasswordRestSent -> {
                Toast.makeText(context, "Reset link sent! Check your email.", Toast.LENGTH_LONG)
                    .show()
                navController.navigate(Routes.signIn) {
                    popUpTo(Routes.signIn) { inclusive = true }
                }
            }

            is AuthState.ErrorMassage -> {
                Toast.makeText(
                    context,
                    (authState as AuthState.ErrorMassage).massage,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.forgot_password),
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier.height(30.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errorEmail = null
            },
            label = { Text(stringResource(R.string.email)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            isError = errorEmail != null,
            supportingText = {
                if (errorEmail != null) {
                    Text(
                        text = errorEmail!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.heightIn(20.dp))
        Button(
            onClick = {
                errorEmail = Validator.getEmailError(email)
                if (errorEmail == null) {
                    viewModel.sendResetPassword(email)
                }
            },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier
                .fillMaxWidth(), enabled = authState !is AuthState.Loading
        ) {
            if (authState is AuthState.Loading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Text(
                    text = "Send Reset Link",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }


        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ForgotPasswordPreview() {
    ForgotPassword(navController = NavController(LocalContext.current))
}