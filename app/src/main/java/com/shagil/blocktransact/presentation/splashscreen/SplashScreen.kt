package com.shagil.blocktransact.presentation.splashscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.shagil.blocktransact.R

import com.shagil.blocktransact.ui.DarkBlue

@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
)  {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        border = BorderStroke(1.dp, Color.Green)

    ) {
        var expanded by remember { mutableStateOf(true) }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            Modifier.clickable {
                expanded = !expanded
                /*navigator.navigate(
                    MainScreenDestination()
                )*/},
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                painter = painterResource(id = R.drawable.ic_blockchain),
                colorFilter = ColorFilter.tint(color = Color.Green),
                contentDescription = "Blockchain Transaction",
                contentScale = ContentScale.Fit
            )

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkBlue)
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Welcome to this Blockchain App! Click to Enter..",
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}