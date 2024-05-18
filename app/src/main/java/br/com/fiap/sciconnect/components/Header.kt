package br.com.fiap.sciconnect.components

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.fiap.sciconnect.R

@Composable
fun Header(darkmode:MutableState<Boolean>, admin: MutableState<Boolean>){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(Color.White)){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Box(modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Row(modifier = Modifier
                    .height(70.dp)
                    .width(300.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                    ){
                    CircleWithLetter(name = "Gabriel")
                    Image(
                        painter = painterResource(id = R.drawable.alarm),
                        contentDescription = "Alarm",
                        Modifier.scale(2.0f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier
                .height(1.dp)
                .width(350.dp)
                .background(Color.Black, shape = RoundedCornerShape(10.dp))){}
        }
    }
}

@Composable
fun CircleWithLetter(
    name: String,
    circleColor: Color = Color(22, 15, 65)
) {
    Box(
        modifier = Modifier
            .size(30.dp)
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