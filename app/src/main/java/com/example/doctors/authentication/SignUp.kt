package com.example.doctors.authentication

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doctors.MainActivity
import com.example.doctors.R
import com.example.doctors.Routes

@Composable
fun SignUp(modifier: Modifier = Modifier,navController: NavController,viewModel: AuthViewModel=viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var errorEmail by remember { mutableStateOf<String?>(null) }
    var errorPassword by remember { mutableStateOf<String?>(null) }
    var errorConfirmPassword by remember { mutableStateOf<String?>(null) }



    val context = LocalContext.current
    val authState by viewModel.authState.collectAsState()
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                Toast.makeText(context, "تمت العملية بنجاح!", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.signIn) {
                    popUpTo(Routes.signUp) { inclusive = true }
                }
            }
            is AuthState.ErrorMassage -> {
                Toast.makeText(context, (authState as AuthState.ErrorMassage).massage, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally ,
        modifier= modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
            
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = email,
            onValueChange =
                {
                    email = it
                    if (errorEmail != null) errorEmail = null
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

        OutlinedTextField(
            value = password,
            onValueChange =
                {
                    password = it
                    if (errorPassword != null) errorPassword = null
                },
            label = { Text(stringResource(R.string.password)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
                        },
                        contentDescription = null
                    )
                }
            },
            isError = errorPassword != null,
            supportingText = {
                if (errorPassword != null) {
                    Text(
                        text = errorPassword!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange =
                {
                    confirmPassword = it
                    if (errorConfirmPassword != null) errorConfirmPassword = null
                },
            label = { Text(stringResource(R.string.confirm_password)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation =
                if (confirmPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            trailingIcon = {
                IconButton(
                    onClick = { confirmPasswordVisible = !confirmPasswordVisible }
                ) {
                    Icon(
                        imageVector = if (confirmPasswordVisible) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
                        },
                        contentDescription = null
                    )
                }
            },
            isError = errorConfirmPassword != null,
            supportingText = {
                if (errorConfirmPassword != null) {
                    Text(
                        text = errorConfirmPassword!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {

                append(stringResource(R.string.have_account) + " ")
                withLink(
                    LinkAnnotation.Clickable(tag = "sign_in", linkInteractionListener = {
                        navController.navigate(Routes.signIn)
                    })
                )
                {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(R.string.sign_in))
                    }

                }
            },
            textAlign = TextAlign.Start,

            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                errorEmail = Validator.getEmailError(email)
                errorPassword = Validator.getPasswordError(password)
                errorConfirmPassword = Validator.getConfirmPasswordError(password, confirmPassword)
                val isFormValid = errorEmail==null &&
                        errorPassword==null &&
                        errorConfirmPassword==null &&
                        email.isNotBlank() &&
                        password.isNotBlank()
                if (isFormValid) {
                    viewModel.signUp(email, password)
                }
            },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier
                .fillMaxWidth(),
            enabled = authState !is AuthState.Loading
        ) {
            if (authState is AuthState.Loading) {
                CircularProgressIndicator(color = Color.White) // إظهار مؤشر التحميل داخل الزر بشكل صحيح
            } else {
                Text(
                    text = stringResource(R.string.create_account,),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
//
//@Preview(showBackground = false, showSystemUi = true)
//@Composable
//private fun SignUpPreview() {
//    SignUp()
//}