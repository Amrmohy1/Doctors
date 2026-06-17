package com.example.doctors

import com.example.doctors.Screens.HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doctors.authentication.ForgotPassword
import com.example.doctors.authentication.SignIn
import com.example.doctors.authentication.SignUp
import com.example.doctors.doctors.Doctors
import com.example.doctors.doctors.Specialties
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController=rememberNavController()
            val isLoggedIn= FirebaseAuth.getInstance().currentUser!=null
            val firstPage = if (isLoggedIn) Routes.home else Routes.signIn
            NavHost(navController = navController,startDestination = firstPage, builder ={
                composable(Routes.signIn){
                    SignIn(navController=navController)
                }
                composable(Routes.signUp){
                    SignUp(navController=navController)
                }
                composable(Routes.home){
                    HomeScreen(navController=navController)
                }
                composable(Routes.forgotPassword){
                    ForgotPassword(navController=navController)
                }
                composable(
                     Routes.doctorWithSpeciality
                ) { backStackEntry ->

                    val specialtyName =
                        backStackEntry.arguments?.getString("specialtyName") ?: ""

                    Doctors(
                        specialtyName = specialtyName
                    )
                }
                composable(Routes.specialtise){
                    Specialties(navController=navController)
                }
            } )
        }
    }
}

