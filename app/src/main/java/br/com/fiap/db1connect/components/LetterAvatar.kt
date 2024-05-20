package br.com.fiap.db1connect.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LetterAvatar(name:String,darkmode: MutableState<Boolean>){
    var letter = name.toCharArray()[0].uppercase()
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(
                if(darkmode.value) Color(255,255,255) else Color(49,52,57),
                shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            fontSize = 25.sp,
            color = if(darkmode.value) Color(49,52,57) else Color.White,
            text = letter,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }

}

