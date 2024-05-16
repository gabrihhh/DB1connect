package br.com.fiap.sciconnect.screens

import android.annotation.SuppressLint
import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//Definindo variaveis globais
var PaginarRegistro by mutableStateOf<String?>(null)
var RegistroEscolhido by mutableStateOf<String?>(null)
var Nome by mutableStateOf<String>("")
var Cpf by mutableStateOf<String>("")
var radioOption by mutableStateOf<Boolean>(false)
var Senha by mutableStateOf<String>("")
var SenhaNovamente by mutableStateOf<String>("")
var Formacao by mutableStateOf<String>("")
var Pass by mutableStateOf<Boolean>(true)
var MsgError by mutableStateOf<Boolean>(false)
var RadioOptionInteresse by mutableStateOf<String>("")
var RadioSelectInteresse by mutableStateOf<Boolean>(false)
var Descricao by mutableStateOf<String>("")
//definindo class para construção do JSON no final
data class Registro(
    val name: String,
    val cpf: String,
    val typeUser: String,
    val senha:String,
    val formacao: String
)
@Composable
fun RegisterScreen(navController: NavController,darkmode: MutableState<Boolean>){
    DisposableEffect(Unit) {
        onDispose {
            PaginarRegistro = null
            RegistroEscolhido = null
            Nome = ""
            Cpf = ""
            radioOption = false
            Senha = ""
            SenhaNovamente = ""
            Formacao = ""
            Pass = true
            MsgError = false
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(255, 255, 255)),
        contentAlignment = Alignment.Center
    ){
        when (PaginarRegistro) {
            "Interesses" -> Interesses()
            "About" -> About()
            else -> RegisterEscolha()
        }
    }
}

@Composable
fun Interesses() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Header(focus = "Interesses")

        Text(
            text = "Selecione as areas em que tem interesse",
            color = Color(229, 60, 91),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Column(modifier = Modifier.width(300.dp)){
            
            Spacer(modifier = Modifier.height(120.dp))
        }
        Label(text = "Áreas de interesse")
        Column(modifier = Modifier.width(300.dp)){

            Spacer(modifier = Modifier.height(120.dp))
        }
        Column(modifier = Modifier.width(300.dp)){
            Label(text="Oque te trouxe até a DB1Connect?")
            RadioGroupInteresse(
                selectedOption = RadioSelectInteresse,
                onOptionSelected = { RadioOptionInteresse = it }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column{
            Text(
                text = "Escreva uma rapida descrição de você",
                color = Color(229, 60, 91),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            TextField(
                value = Descricao,
                onValueChange = { newValue ->
                    Descricao = newValue
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .border(
                        border = BorderStroke(2.dp, Color(22, 15, 65)),
                        shape = RoundedCornerShape(10.dp)
                    ),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedLabelColor = Color(229, 60, 91),
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = Color(255,255,25)
                )
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .background(color = Color(22, 15, 65), shape = RoundedCornerShape(10.dp))
                .clickable(onClick = {

                }),
            contentAlignment = androidx.compose.ui.Alignment.Center,
        ) {
            Text(
                color= Color(255,255,255),
                fontWeight = FontWeight.Bold,
                text="Prosseguir"
            )
        }

    }
}

@Composable
fun RegisterEscolha(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .background(color = Color(22, 15, 65), shape = RoundedCornerShape(10.dp))
                .clickable(onClick = {
                    RegistroEscolhido = "Usuario"
                    PaginarRegistro = "About"
                }),
            contentAlignment = androidx.compose.ui.Alignment.Center,
        ) {
            Text(
                color= Color(255,255,255),
                fontWeight = FontWeight.Bold,
                text="Registrar-se como Aprendiz"
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "ou",
            fontWeight = FontWeight.Bold,
            color = Color(22, 15, 65)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .background(color = Color(22, 15, 65), shape = RoundedCornerShape(10.dp))
                .clickable(onClick = {
                    RegistroEscolhido = "Mentor"
                    PaginarRegistro = "About"
                }),
            contentAlignment = androidx.compose.ui.Alignment.Center,
        ) {
            Text(
                color= Color(255,255,255),
                fontWeight = FontWeight.Bold,
                text="Registrar-se como Mentor"
            )
        }
    }
}

@Composable
fun About(){
   Column(
       modifier = Modifier.fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally
   ){
       Header(focus = "About")
       //Box do nome
       Column(
       ){
           Label("Nome")
           TextField(
               value = Nome,
               onValueChange = { newValue ->
                   Nome = newValue
               },
               modifier = Modifier
                   .width(300.dp)
                   .height(40.dp)
                   .border(
                       border = BorderStroke(2.dp, Color(22, 15, 65)),
                       shape = RoundedCornerShape(10.dp)
                   ),
               textStyle = TextStyle(color = Color.Black),
               colors = TextFieldDefaults.colors(
                   unfocusedContainerColor = Color.White,
                   focusedContainerColor = Color.White,
                   focusedLabelColor = Color(229, 60, 91),
                   unfocusedLabelColor = Color.Gray,
                   focusedIndicatorColor = Color(255,255,25)
               )
           )
       }
       Spacer(modifier = Modifier.height(10.dp))
       //box do cpf
       Column(
       ){
           Label("CPF")
           TextField(
               value = Cpf,
               onValueChange = { newValue ->
                   Cpf = newValue
               },
               modifier = Modifier
                   .width(300.dp)
                   .height(40.dp)
                   .border(
                       border = BorderStroke(2.dp, Color(22, 15, 65)),
                       shape = RoundedCornerShape(10.dp)
                   ),
               textStyle = TextStyle(color = Color.Black),
               colors = TextFieldDefaults.colors(
                   unfocusedContainerColor = Color.White,
                   focusedContainerColor = Color.White,
                   focusedLabelColor = Color(229, 60, 91),
                   unfocusedLabelColor = Color.Gray,
                   focusedIndicatorColor = Color(255,255,25)
               )
           )
       }
       Spacer(modifier = Modifier.height(20.dp))
       Column(modifier = Modifier.width(300.dp)){
           Label(text = "Ja possui formação?")
           RadioGroupAbout(
               selectedOption = radioOption,
               onOptionSelected = { radioOption = it }
           )
       }
       Spacer(modifier = Modifier.height(20.dp))
       if(radioOption) {
           Column(
           ) {
               Label("Qual?")
               TextField(
                   value = Formacao,
                   onValueChange = { newValue ->
                       Formacao = newValue
                   },
                   modifier = Modifier
                       .width(300.dp)
                       .height(40.dp)
                       .border(
                           border = BorderStroke(2.dp, Color(22, 15, 65)),
                           shape = RoundedCornerShape(10.dp)
                       ),
                   textStyle = TextStyle(color = Color.Black),
                   colors = TextFieldDefaults.colors(
                       unfocusedContainerColor = Color.White,
                       focusedContainerColor = Color.White,
                       focusedLabelColor = Color(229, 60, 91),
                       unfocusedLabelColor = Color.Gray,
                       focusedIndicatorColor = Color(255, 255, 25)
                   )
               )
           }
       }
       Spacer(modifier = Modifier.height(20.dp))
       Column(
       ){
           Label("Crie uma senha")
           TextField(
               value = Senha,
               onValueChange = { newValue ->
                   Senha = newValue
               },
               modifier = Modifier
                   .width(300.dp)
                   .height(40.dp)
                   .border(
                       border = BorderStroke(2.dp, Color(22, 15, 65)),
                       shape = RoundedCornerShape(10.dp)
                   ),
               textStyle = TextStyle(color = Color.Black),
               colors = TextFieldDefaults.colors(
                   unfocusedContainerColor = Color.White,
                   focusedContainerColor = Color.White,
                   focusedLabelColor = Color(229, 60, 91),
                   unfocusedLabelColor = Color.Gray,
                   focusedIndicatorColor = Color(255,255,25)
               )
           )
       }
       Spacer(modifier = Modifier.height(20.dp))
       Column(
       ){
           Label("Confirme sua senha")
           TextField(
               value = SenhaNovamente,
               onValueChange = { newValue ->
                   SenhaNovamente = newValue
               },
               modifier = Modifier
                   .width(300.dp)
                   .height(40.dp)
                   .border(
                       border = BorderStroke(2.dp, Color(22, 15, 65)),
                       shape = RoundedCornerShape(10.dp)
                   ),
               textStyle = TextStyle(color = Color.Black),
               colors = TextFieldDefaults.colors(
                   unfocusedContainerColor = Color.White,
                   focusedContainerColor = Color.White,
                   focusedLabelColor = Color(229, 60, 91),
                   unfocusedLabelColor = Color.Gray,
                   focusedIndicatorColor = Color(255,255,25)
               )
           )
       }
       Spacer(modifier = Modifier.height(30.dp))
       Box(
           modifier = Modifier
               .width(300.dp)
               .height(50.dp)
               .background(color = Color(22, 15, 65), shape = RoundedCornerShape(10.dp))
               .clickable(onClick = {
//                   if(Nome === ""){
//                       Pass = false
//                   }
//                   if(Cpf === ""){
//                       Pass = false
//                   }
//                   if(Senha !== SenhaNovamente || Senha === "" || SenhaNovamente === ""){
//                       Pass = false
//                   }
//
//                   if(Pass){
//                       PaginarRegistro = "Interesses"
//                   }else{
//                       MsgError = true
//                   }

                   PaginarRegistro = "Interesses"

               }),
           contentAlignment = androidx.compose.ui.Alignment.Center,
       ) {
           Text(
               color= Color(255,255,255),
               fontWeight = FontWeight.Bold,
               text="Prosseguir"
           )
       }
       if(MsgError){
           Label(text = "* Reveja seu formulário e tente novamente.")
       }

   }
}

@Composable
fun Header(focus:String){
    Spacer(modifier = Modifier.height(30.dp))
    Row(modifier = Modifier
        .width(300.dp)
        .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ){
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Sobre você",color = Color(22, 15, 65), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            if(focus === "About"){
                Box(modifier = Modifier
                    .height(5.dp)
                    .width(70.dp)
                    .background(color = Color(229, 60, 91), shape = RoundedCornerShape(10.dp))){}
            }
        }
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Interesses",color = Color(22, 15, 65), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            if(focus === "Interesses"){
                Box(modifier = Modifier
                    .height(5.dp)
                    .width(70.dp)
                    .background(color = Color(229, 60, 91), shape = RoundedCornerShape(10.dp))){}
            }
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun Label(text:String){
    Text(
        text = text,
        color = Color(229, 60, 91),
        fontWeight = FontWeight.Bold
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioButtonOption(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text=text, color = Color(22, 15, 65), fontWeight = FontWeight.Bold, fontSize = 10.sp)
    }
}
@Composable
fun RadioGroupAbout(
    selectedOption: Boolean,
    onOptionSelected: (Boolean) -> Unit
) {
    Row(
    ) {
        RadioButtonOption(
            text = "Sim",
            selected = selectedOption,
            onSelect = { onOptionSelected(true) }
        )
        RadioButtonOption(
            text = "Não",
            selected = !selectedOption,
            onSelect = { onOptionSelected(false) }
        )
    }
}
@Composable
fun RadioGroupInteresse(
    selectedOption: Boolean,
    onOptionSelected: (String) -> Unit
) {
    Column(
    ) {
        RadioButtonOption(
            text = "Compartilhar conhecimentos e aprendizados",
            selected = selectedOption,
            onSelect = { onOptionSelected("Compartilhar conhecimentos e aprendizados") }
        )
        RadioButtonOption(
            text = "Aprender com profissionais e professores",
            selected = !selectedOption,
            onSelect = { onOptionSelected("Aprender com profissionais e professores") }
        )
    }
}