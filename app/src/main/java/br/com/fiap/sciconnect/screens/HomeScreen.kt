package br.com.fiap.sciconnect.screens

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.sciconnect.R
import br.com.fiap.sciconnect.components.Header
import br.com.fiap.sciconnect.components.LetterAvatar
import br.com.fiap.sciconnect.components.Navigation

@Composable
fun HomeScreen(navController: NavController,darkmode: MutableState<Boolean>){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Header(darkmode = darkmode)
        val scroll = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(vertical = 100.dp)
                .fillMaxSize()
                .background(
                    Color.LightGray
                )
                .verticalScroll(scroll)
        ){
            Post(user = "gabriel", title = "teste do post",time = "Ter 15:08",darkmode = darkmode)
            Post(user = "matheus", title = "olha lá",time = "Qua 15:08",darkmode = darkmode)
            Post(user = "RAFA",title = "pull request",time = "Sex 13:00",darkmode = darkmode)
            Post(user = "gabriel", title = "teste do post",time = "Ter 15:08",darkmode = darkmode)
            Post(user = "matheus", title = "olha lá",time = "Qua 15:08",darkmode = darkmode)
        }
        Navigation(navController,darkmode = darkmode)
    }
}

@Composable
fun mockImage(){
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(Color(0, 0, 0))
    )
}

@Composable
fun Post(user:String,title:String,time:String,Description:String? = null,arquive:String? = null,darkmode: MutableState<Boolean>){
    Box(
        modifier = Modifier
            .padding(bottom = 1.dp)
            .fillMaxWidth()
            .height(150.dp)
            .background(
                if(darkmode.value) Color(49,52,57) else Color(255, 255, 255)
            )
    ){
        Box(modifier = Modifier.padding(10.dp)){
            Column() {
                Row() {
                    LetterAvatar(name = user,darkmode)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column() {
                        Text(
                            text = user,
                            color = if(darkmode.value) Color(255,255,255) else Color(0,0,0),
                            fontSize = 16.sp
                        )
                        Text(
                            text = time,
                            color = if(darkmode.value) Color(255,255,255) else Color(0,0,0),
                            fontSize = 8.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if(darkmode.value) Color(255,255,255) else Color(0,0,0)
                )
            }
        }
    }
}