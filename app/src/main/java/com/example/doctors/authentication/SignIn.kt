package com.example.doctors.authentication

import android.widget.Toast
import androidx.collection.emptyLongSet
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
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
import androidx.navigation.compose.rememberNavController
import com.example.doctors.R
import com.example.doctors.Routes

@Composable
fun SignIn(navController: NavController,modifier: Modifier = Modifier,viewModel: AuthViewModel= viewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorPassword by remember { mutableStateOf<String?>(null) }
    val authState by viewModel.authState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(authState) {
        when(authState){
            is AuthState.Authenticated->{
                navController.navigate(Routes.home){
                    popUpTo(Routes.signIn){
                        inclusive=true
                    }
                }
                Toast.makeText(context, "successfully logged in", Toast.LENGTH_SHORT).show()
            }
            is AuthState.ErrorMassage-> Toast.makeText(context, (authState as AuthState.ErrorMassage).massage, Toast.LENGTH_SHORT).show()
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
            text = stringResource(R.string.sign_in),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier=Modifier.fillMaxWidth()
        )
        Spacer(modifier= Modifier.height(30.dp))
        OutlinedTextField(
            value = email,
            onValueChange =
                {
                    email=it
                    errorEmail=null
                },
            label = {Text(text = stringResource(R.string.email))},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            isError = errorEmail !=null,
            supportingText = {
                if (errorEmail!=null){
                    Text(
                        text = errorEmail!!,color= MaterialTheme.colorScheme.error
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
                    password=it
                    errorPassword=null
                },
            label = {Text(text = stringResource(R.string.password))},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisible){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector = if (passwordVisible){
                            Icons.Default.VisibilityOff
                        }else{
                            Icons.Default.Visibility
                        },
                        contentDescription = null
                    )
                }
            },
            isError = errorPassword !=null,
            supportingText = {
                if (errorPassword!=null){
                    Text(
                        text = errorPassword!!,color= MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier= Modifier.fillMaxWidth()
        )
        Text(
            text= buildAnnotatedString {
                append(stringResource(R.string.have_no_account)+" ")
                withLink(
                    LinkAnnotation.Clickable(tag ="sign_up", linkInteractionListener = {
                        navController.navigate(Routes.signUp)
                    })
                ){
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                        )
                    )
                    {
                        append(stringResource(R.string.sign_up))
                    }
                }
            },

            modifier= Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    navController.navigate(Routes.signUp)
                }
                .fillMaxWidth()
        )
        Spacer(modifier= Modifier.height(30.dp))
        Button(
            onClick = {
                errorEmail = Validator.getEmailError(email)
                errorPassword = Validator.getPasswordError(password)
                val isFormValid = errorEmail==null &&
                        errorPassword==null
                if(isFormValid){
                   viewModel.signIn(email,password)
                }
            },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier= Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Text(
            text = stringResource(R.string.forgot_password),
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            textDecoration= TextDecoration.Underline,
            textAlign = TextAlign.Start,
            modifier= Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ){
                    navController.navigate(Routes.forgotPassword)
                }
        )
    }
}

//@Preview(showSystemUi = true, showBackground = false)
//@Composable
//private fun SignInPreview() {
//    SignIn()
//}