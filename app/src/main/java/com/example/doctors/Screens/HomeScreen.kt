package com.example.doctors.Screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.collections.listOf
import com.example.doctors.doctors.Specialties

@Composable
fun HomeScreen(modifier: Modifier = Modifier,navController: NavController) {
    val navItem = listOf(
        NavItem("settings", Icons.Default.Settings),
        NavItem("home", Icons.Default.Home) ,
        NavItem("profile", Icons.Default.Person),

    )
    var selectedIndex by remember {mutableIntStateOf(1) }
    Scaffold(
        modifier=Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItem.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex==index,
                        onClick = {
                            selectedIndex=index
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        },
                        label = {
                            Text(
                                text = item.name
                            )
                        }
                    )

                }
            }
        }
    ) {innerPadding->
        ContentScreen(modifier=Modifier.padding(innerPadding),selectedIndex,navController)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier,selectedIndex:Int,navController: NavController) {
    when(selectedIndex){
        0-> Settings()
        1-> Specialties(navController = navController)
        2-> Profile()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}