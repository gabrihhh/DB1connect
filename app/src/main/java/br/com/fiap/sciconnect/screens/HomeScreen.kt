package br.com.fiap.sciconnect.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import br.com.fiap.sciconnect.service.RetrofitFactory
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.Manifest
import android.content.Context
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.LaunchedEffect
import br.com.fiap.sciconnect.service.NotificationHandler
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

var match by mutableStateOf<Boolean>(false)
var Count by mutableStateOf<Int>(1)
var ListaUsers by mutableStateOf(listOf<User?>(null))
var nomePerfil by mutableStateOf("gabriel")
var tituloPerfil by mutableStateOf("Desenvolvedor")
var textoPerfil by mutableStateOf("Sou o seu guia na jornada da programação. Comigo, você vai explorar o fascinante mundo do JavaScript. Juntos, vamos mergulhar nos conceitos, desafios e possibilidades que essa linguagem oferece. Estou aqui para responder às suas perguntas, ajudar a resolver problemas e compartilhar dicas valiosas. Vamos codificar e transformar ideias em realidade!")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    user: MutableState<User?>,
    context: Context
) {
    val postNotificationPermission = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val notificationHandler = NotificationHandler(context)

    LaunchedEffect(key1 = true) {
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }
    }
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
              //Mexer aqui
                Box(modifier = Modifier.fillMaxSize()){
                    Column(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(10.dp),
                        verticalArrangement = Arrangement.SpaceAround

                    ){
                        Text(
                            text = match.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = nomePerfil,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = tituloPerfil.toString(),
                            color = Color.Black
                        )
                        Text(
                            text = textoPerfil,
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
                    var call = RetrofitFactory()
                        .getUsersService()
                        .getUsuarios()
                    call.enqueue(object : Callback<List<User>> {
                        override fun onResponse(
                            call: Call<List<User>>,
                            response: Response<List<User>>
                        ) {
                            Log.i("FIAP", "onResponse: ${response.body()}")
                            ListaUsers = response.body()!!
                            nomePerfil = ListaUsers[Count]!!.nome
                            tituloPerfil = ListaUsers[Count]!!.formadoTitulo
                            textoPerfil = ListaUsers[Count]!!.texto
                            match = false
                            Count ++
                        }
                        override fun onFailure(call: Call<List<User>>, t: Throwable) {
                            Log.i("FIAP", "onResponse: ${t.message}")
                        }

                    })
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.notlike),
                        contentDescription = "Deslike",
                        Modifier.scale(4.0f)
                    )
                }

                Box(modifier = Modifier.clickable {
                    var call = RetrofitFactory()
                        .getUsersService()
                        .getUsuarios()
                    call.enqueue(object : Callback<List<User>> {
                        override fun onResponse(
                            call: Call<List<User>>,
                            response: Response<List<User>>
                        ) {
                            Log.i("FIAP", "onResponse: ${response.body()}")
                            ListaUsers = response.body()!!
                            nomePerfil = ListaUsers[Count]!!.nome
                            tituloPerfil = ListaUsers[Count]!!.formadoTitulo
                            textoPerfil = ListaUsers[Count]!!.texto
                            ListaUsers[Count]!!.interesses.forEach{ interessesPerfil ->
                                if(user.value!!.interesses.contains(interessesPerfil)){
                                    match = true
                                    notificationHandler.showSimpleNotification()
                                }else{
                                    match = false
                                }
                            }
                            Count ++
                        }

                        override fun onFailure(call: Call<List<User>>, t: Throwable) {
                            Log.i("FIAP", "onResponse: ${t.message}")
                        }

                    })
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