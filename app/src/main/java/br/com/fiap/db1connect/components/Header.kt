package br.com.fiap.db1connect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.fiap.DB1Connect.R

//import br.com.fiap.db1connect.R

@Composable
fun Header(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color.White)){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Box(modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Row(modifier = Modifier
                    .height(70.dp)
                    .width(300.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                    ){
                    CircleWithLetter(name = "Gabriel", size = 30.dp)
                    Image(
                        painter = painterResource(id = R.drawable.alarm),
                        contentDescription = "Alarm",
                        Modifier.scale(3.0f)
                    )
                }
            }
        }
    }
}

@Composable
fun CircleWithLetter(
    name: String,
    circleColor: Color = Color(22, 15, 65),
    size: Dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(circleColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.take(1).uppercase(),
            color = Color.White,
            textAlign = TextAlign.Center,
            style = androidx.compose.ui.text.TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        )
    }
}