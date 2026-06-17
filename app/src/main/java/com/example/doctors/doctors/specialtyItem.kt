package com.example.doctors.doctors

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctors.R

@Composable
fun SpecialtyItem(
    specialty: Specialty,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        onClick=onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = if (isSelected){
            BorderStroke(2.dp, Color(0xFF1E5BB4))
        }else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.size(100.dp)
                    .padding(top=8.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(specialty.image),
                    contentDescription = null,
                    Modifier
                        .size(200.dp)
                        .clip(CircleShape),
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(specialty.name),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = false)
@Composable
private fun SpecialtyItemPreview() {
    SpecialtyItem(
        specialty = Specialty(1, R.string.cardiology, R.drawable.cardiology)
        , isSelected = true,
        onClick = {}
    )
}