package com.example.doctors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doctors.authentication.ForgotPassword
import com.example.doctors.authentication.SignIn
import com.example.doctors.authentication.SignUp
import com.example.doctors.ui.theme.DoctorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController=rememberNavController()
            NavHost(navController = navController,startDestination = Routes.signIn, builder ={
                composable(Routes.signIn){
                    SignIn(navController=navController)
                }
                composable(Routes.signUp){
                    SignUp(navController=navController)
                }
                composable(Routes.home){
                    Home(navController=navController)
                }
                composable(Routes.forgotPassword){
                    ForgotPassword(navController=navController)
                }
            } )
        }
    }
}

