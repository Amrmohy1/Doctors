package com.example.doctors.doctors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun DoctorsItems(modifier: Modifier = Modifier,doctorsModel: DoctorsModel) {
    Card(
        modifier= Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFFFFFFFF))

    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier=Modifier.fillMaxWidth()
                .padding(16.dp)
                .height(120.dp)
        ){

            Spacer(modifier=Modifier.width(25.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                modifier= Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = doctorsModel.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier=Modifier.height(10.dp))
                Text(
                    text = doctorsModel.about,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            AsyncImage(
                model = doctorsModel.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier= Modifier.size(100.dp)
                    .clip(CircleShape)

            )

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DoctorsItemsPreview() {
    DoctorsItems(doctorsModel = DoctorsModel("ddksssss","https://gemini.google.com/share/ab0f7849e800","ssssssss"))

}