package br.com.fiap.sciconnect.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.sciconnect.R
import br.com.fiap.sciconnect.components.Header
import br.com.fiap.sciconnect.components.Navigation
import br.com.fiap.sciconnect.model.User

@Composable
fun HomeScreen(
    navController: NavController,
    user: MutableState<User?>,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(user)
        Column(
            modifier = Modifier
                .padding(vertical = 100.dp)
                .fillMaxSize()
                .background(
                    Color.White
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            //.verticalScroll(scroll)
        ) {
            Box(modifier = Modifier
                .width(300.dp)
                .height(400.dp)
                .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(10.dp))
            ){
                Box(modifier = Modifier.fillMaxSize()){
                    Column(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(10.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ){
                        Text(
                            text = "Gabriel Patara",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "Mentor de Programação",
                            color = Color.Black
                        )
                        Text(
                            text = "Sou o seu guia na jornada da programação. Comigo, você vai explorar o fascinante mundo do JavaScript. Juntos, vamos mergulhar nos conceitos, desafios e possibilidades que essa linguagem oferece. Estou aqui para responder às suas perguntas, ajudar a resolver problemas e compartilhar dicas valiosas. Vamos codificar e transformar ideias em realidade!",
                            fontSize = 10.sp,
                            textAlign = TextAlign.Justify,
                            lineHeight = 15.sp,
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Box(modifier = Modifier.clickable {
                    //DESLIKE
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.notlike),
                        contentDescription = "Deslike",
                        Modifier.scale(4.0f)
                    )
                }

                Box(modifier = Modifier.clickable {
                    //LIKE
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = "like",
                        Modifier.scale(4.0f)
                    )
                }
            }
        }
        Navigation(navController,rota = "home")
    }
}