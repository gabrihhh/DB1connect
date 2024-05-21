package br.com.fiap.sciconnect.screens

import android.widget.Space
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.sciconnect.R
import br.com.fiap.sciconnect.components.CircleWithLetter
import br.com.fiap.sciconnect.components.Header
import br.com.fiap.sciconnect.components.Navigation
import br.com.fiap.sciconnect.model.User

var PaginarMessage by mutableStateOf<String>("")
var inputMessage by mutableStateOf<String>("")
var messages by mutableStateOf<List<String>?>(null)
@Composable
fun MessageScreen(
    navController: NavController,
    user: MutableState<User?>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(user)
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(vertical = 100.dp)
                .fillMaxSize()
                .background(
                    Color.White
                )
                .verticalScroll(scrollState),
        ) {
            when(PaginarMessage){
                "message" -> ScreenMessage(user)
                else -> ScreenDefaultMessage()
            }
        }
        Navigation(navController, rota = "msg")
    }
}

@Composable
fun ScreenMessage(user: MutableState<User?>) {
        Column{
            val scrollState = rememberScrollState()
            Box(modifier = Modifier
                .width(330.dp)
                .height(420.dp)
                .verticalScroll(scrollState)
                ){
                Column {
                    messages?.forEach{
                            msg ->
                        message(string = msg,user)
                    }
                }
            }
            Box(modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically){
                    Box(modifier = Modifier
                        .width(200.dp)
                        .border(
                            BorderStroke(1.dp, Color(22, 15, 65)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            PaginarExplorer = "ExplorerFiltro"
                        },
                    ) {
                        TextField(
                            value = inputMessage,
                            onValueChange = { newValue -> inputMessage = newValue },
                            placeholder = { Text(text = "Digite.") },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Image(
                        painter = painterResource(id = R.drawable.msg),
                        contentDescription = "Logo",
                        Modifier
                            .scale(3.0f)
                            .border(BorderStroke(1.dp, Color(22, 15, 65)))
                            .clickable {
                                messages = messages.orEmpty() + inputMessage
                            }
                    )

                }

            }
        }
}

@Composable
fun ScreenDefaultMessage(){
    ListaUser.forEach{user ->
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(BorderStroke(1.dp, Color(22, 15, 65)), shape = RoundedCornerShape(10.dp))
            .clickable {
                PaginarMessage = "message"
            },
            Alignment.CenterStart){
            Row{
                if (user != null) {
                    Spacer(modifier = Modifier.width(20.dp))
                    CircleWithLetter(name = user.nome, size = 20.dp)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = user.nome, color = Color(22, 15, 65))
                }
            }
        }
    }
}

@Composable
fun message(string: String,user: MutableState<User?>){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp)
        ,
        contentAlignment = Alignment.CenterEnd
    ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = string,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(20.dp))
            user.value?.let { CircleWithLetter(name = it.nome, size = 20.dp) }
        }
    }
}