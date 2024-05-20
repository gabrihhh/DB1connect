package br.com.fiap.db1connect.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.DB1Connect.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(255, 255, 255))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                Modifier.scale(1.0f)
            )
            var login = remember {
                mutableStateOf("")
            }
            TextField(
                value = login.value,
                onValueChange = { newValue ->
                    login.value = newValue
                },

                modifier = Modifier
                    .width(300.dp)
                    .border(
                        border = BorderStroke(2.dp, Color(22, 15, 65)),
                        shape = RoundedCornerShape(10.dp)
                    ),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("username") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedLabelColor = Color(229, 60, 91),
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = Color(255,255,25)
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            var password = remember {
                mutableStateOf("")
            }
            TextField(
                value = password.value,
                onValueChange = { newValue ->
                    password.value = newValue
                },
                modifier = Modifier
                    .width(300.dp)
                    .background(color = Color(255, 255, 255), shape = RoundedCornerShape(10.dp))
                    .border(
                        border = BorderStroke(2.dp, color = Color(22, 15, 65)),
                        shape = RoundedCornerShape(10.dp)
                    ),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("password") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedLabelColor = Color(229, 60, 91),
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = Color(255,255,25)
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(50.dp)
                    .background(color = Color(229, 60, 91), shape = RoundedCornerShape(10.dp))
                    .clickable(onClick = {}),
                contentAlignment = androidx.compose.ui.Alignment.Center,
            ) {
                Text(
                    color= Color(255,255,255),
                    fontWeight = FontWeight.Bold,
                    text="Entrar"
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .background(color = Color(255, 255, 255))
                    .border(
                        BorderStroke(1.dp, Color(22, 15, 65)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable(onClick = {}),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(id = R.drawable.logogoogle),
                        contentDescription = "Google",
                        modifier = Modifier.scale(3.0f)
                    )
                    Text(
                        color = Color(22, 15, 65),
                        fontWeight = FontWeight.Bold,
                        text="Logar com"
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Box(modifier = Modifier
                .height(5.dp)
                .width(300.dp)
                .background(color = Color(22, 15, 65), shape = RoundedCornerShape(10.dp))){}
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .background(color = Color(22, 15, 65), shape = RoundedCornerShape(10.dp))
                    .clickable(onClick = {
                        navController.navigate("register")
                    }),
                contentAlignment = androidx.compose.ui.Alignment.Center,
            ) {
                Text(
                    color= Color(255,255,255),
                    fontWeight = FontWeight.Bold,
                    text="Inscreva-se"
                )
            }
        }
    }
}

