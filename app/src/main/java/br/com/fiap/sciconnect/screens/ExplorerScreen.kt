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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.sciconnect.R
import br.com.fiap.sciconnect.database.repository.PostRepository
import br.com.fiap.sciconnect.model.Post
import br.com.fiap.sciconnect.components.Header
import br.com.fiap.sciconnect.components.LetterAvatar
import br.com.fiap.sciconnect.components.Navigation

var PaginarExplorer by mutableStateOf<String?>(null)
var Filtro by mutableStateOf<String>("")

@Composable
fun ExplorerScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header()
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
            when (PaginarExplorer) {
                "Interesses" -> Interesses()
                else -> ExplorerDefault()
            }
        }
        Navigation(navController,rota = "explore")
    }
}

@Composable
fun ExplorerDefault(){
    Box(modifier = Modifier
        .width(300.dp)
        .height(400.dp)){
        Filter()
    }
}


@Composable
fun Filter(){
    Box(modifier = Modifier
        .width(300.dp)
        .border(BorderStroke(1.dp, Color(22, 15, 65)), shape = RoundedCornerShape(10.dp)),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            TextField(
                value = Filtro,
                onValueChange = {newValue -> Filtro = newValue},
                placeholder = {Text(text="Procurar...")},
                colors = TextFieldDefaults.colors(
                   unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
                )
            Image(painter = painterResource(id = R.drawable.search), contentDescription = "Search",
                modifier = Modifier.scale(2.5f))
        }
    }
}