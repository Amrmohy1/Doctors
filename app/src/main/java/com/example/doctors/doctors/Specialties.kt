package com.example.doctors.doctors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doctors.R
import com.example.doctors.Routes

@Composable
fun Specialties(navController: NavController,modifier: Modifier = Modifier) {
        val specialties = remember{
            listOf(
                Specialty(1, R.string.cardiology, R.drawable.cardiology),
                Specialty(2, R.string.dermatology, R.drawable.dermatology),
                Specialty(3, R.string.orthopedics, R.drawable.orthopedics),
                Specialty(4, R.string.pediatrics, R.drawable.pediatrics),
                Specialty(5, R.string.ophthalmology, R.drawable.ophthalmology),
                Specialty(6, R.string.ent, R.drawable.ent),
                Specialty(7, R.string.dentistry, R.drawable.dentistry),
                Specialty(8, R.string.general_surgery, R.drawable.general_surgery),
                Specialty(9, R.string.internal_medicine, R.drawable.internal_medicine),
                Specialty(10, R.string.gynecology, R.drawable.gynecology),
                Specialty(11, R.string.urology, R.drawable.urology),
                Specialty(13, R.string.pulmonology, R.drawable.pulmonology),
                Specialty(15, R.string.vascular_surgery, R.drawable.vascular_surgery),
                Specialty(16, R.string.neurosurgery, R.drawable.neurosurgery),
            )
        }

        var selectedSpecialtyId by remember { mutableIntStateOf(1) }
        Column(
            modifier= Modifier.fillMaxSize()
                .background(Color(0xFF1E5BB4))
                .padding(bottom = 80.dp)
        ) {
                Text(
                    text = stringResource(R.string.specialities),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=35.dp, bottom = 25.dp)
                )
            Surface(
                modifier= Modifier.fillMaxSize(),
                color = Color(0xFFF5F7FA),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier= Modifier.fillMaxSize() ,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    items(specialties){specialty->
                        val specialtyName = stringResource(specialty.name)
                        SpecialtyItem(
                            specialty = specialty,
                            isSelected = specialty.id == selectedSpecialtyId,
                            onClick = {
                                selectedSpecialtyId = specialty.id

                                navController.navigate("doctors/$specialtyName")                            }
                        )
                    }
                }
            }



        }
}

@Preview(showSystemUi = true, showBackground = false)
@Composable
private fun SpecialtiesPreview() {
    Specialties(navController = rememberNavController())
}