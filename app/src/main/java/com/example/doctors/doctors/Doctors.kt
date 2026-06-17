package com.example.doctors.doctors

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Doctors(specialtyName: String, modifier: Modifier = Modifier) {
    val doctorsList = remember { mutableStateOf<List<DoctorsModel>>(emptyList()) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data").document("doctors")
            .collection("details")
            .get()
            .addOnCompleteListener() {
                if (it.isSuccessful){
                    val resultList =it.result.documents.mapNotNull { docs->
                        docs.toObject(DoctorsModel::class.java)
                    }
                    resultList.forEach {
                        Log.d("DOCTORS_DATA", it.toString())
                    }
                    doctorsList.value=resultList
                }else{
                    Toast.makeText(context, it.exception?.message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
                    Log.e("Firestore", it.exception?.message ?: "Unknown Error")
                    doctorsList.value= emptyList()
                    }
            }
    }
    CompositionLocalProvider (LocalLayoutDirection provides LayoutDirection.Rtl) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E5BB4))
                .height(120.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White,

                modifier = Modifier
                    .size(50.dp)
                    .clickable(
                        onClick = {
                            //back button on click
                        }
                    )

            )
            Text(
                text = "تخصص $specialtyName",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFF4F7FA)),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(doctorsList.value) { index ->
                DoctorsItems(doctorsModel = index)
            }
        }
    }
}
}

@Preview(showSystemUi = true)
@Composable
private fun DoctorsPreview() {
    Doctors("Dermatology")
}