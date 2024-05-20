package br.com.fiap.sciconnect.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.text.ListFormatter.Width
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.wear.compose.material.dialog.Alert
import br.com.fiap.sciconnect.R
import java.io.File
import java.io.IOException
import java.util.Date
import java.util.Locale

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
val items = listOf("Item 1", "Item 2", "Item 3", "Item 4","Item 5","Item 6","Item 7","Item 8","Item 9","Item 10")
var selectedItems by mutableStateOf(listOf<String>())
var Avatar by mutableStateOf<String>("")
//definindo class para construção do JSON no final
data class Registro(
    val name: String,
    val cpf: String,
    val typeUser: String,
    val senha:String,
    val formacao: String
)
@Composable
fun RegisterScreen(navController: NavController){
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
            RadioOptionInteresse =""
            RadioSelectInteresse = false
            Descricao = ""
            selectedItems = listOf<String>()
            Avatar = ""
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
            "Avatar" -> Avatar(navController)
            else -> RegisterEscolha()
        }
    }
}

@Composable
fun Avatar(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Agora é só escolher uma foto!",
            color = Color(22, 15, 65),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .border(BorderStroke(1.dp,Color(22, 15, 65)), shape = RoundedCornerShape(10.dp))
                .clickable {  }
            ,
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.photoicon),
                contentDescription = "Foto",
                Modifier.scale(2.0f)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .background(color = Color(22, 15, 65), shape = RoundedCornerShape(10.dp))
                .clickable(onClick = {
                    navController.navigate("home")
                }),
            contentAlignment = androidx.compose.ui.Alignment.Center,
        ) {
            Text(
                color= Color(255,255,255),
                fontWeight = FontWeight.Bold,
                text="Concluir"
            )
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
        Column(modifier = Modifier.width(300.dp).padding(10.dp)){
            SelectableBox(items = items) { selection ->
                selectedItems = selection
            }
        }
        Label(text = "Áreas de interesse")
        Box(modifier = Modifier.width(300.dp).height(100.dp).border(BorderStroke(1.dp,Color(22, 15, 65)), shape = RoundedCornerShape(10.dp))){
            val scrollState = rememberScrollState()
            Column(modifier = Modifier.verticalScroll(scrollState).padding(10.dp),
                verticalArrangement = Arrangement.SpaceAround){
                selectedItems.forEach { selectedItem ->
                    Box(
                        modifier = Modifier
                            .border(BorderStroke(1.dp,Color(22, 15, 65)), shape = RoundedCornerShape(10.dp))
                            .clickable{
                                selectedItems = selectedItems.filter{it != selectedItem}
                            }
                            .padding(10.dp,5.dp)
                        ,
                        ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text = selectedItem,
                                fontSize = 18.sp,
                                color = Color(22, 15, 65),
                            )
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(
                                text = "x",
                                color = Color(22, 15, 65),
                                fontSize = 17.sp,
                            )
                        }
                    }

                }

            }
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
                placeholder = {Text(text = "Descrição...")},
                modifier = Modifier
                    .width(300.dp)
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
                    PaginarRegistro = "Avatar"
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
       Column(
       ){
           Label("Nome")
           Box(
               modifier = Modifier
                   .width(300.dp)
                   .border(
                       border = BorderStroke(2.dp, Color(22, 15, 65)),
                       shape = RoundedCornerShape(10.dp)
                   )){
               TextField(
                   value = Nome,
                   onValueChange = { newValue ->
                       Nome = newValue
                   },
                   placeholder = {Text(text="usuário...")},
                   colors = TextFieldDefaults.colors(
                       unfocusedContainerColor = Color.White,
                       focusedContainerColor = Color.White,
                       focusedTextColor = Color(0,0,0),
                   )
               )
           }
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
               placeholder = {Text(text="cpf...")},
               modifier = Modifier
                   .width(300.dp)
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
                   placeholder = {Text(text="Escreva sua formação...")},
                   modifier = Modifier
                       .width(300.dp)
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
               placeholder = {Text(text="Senha...")},
               modifier = Modifier
                   .width(300.dp)
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
               placeholder = {Text(text="Confirme a senha...")},
               modifier = Modifier
                   .width(300.dp)
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
    Spacer(modifier = Modifier.height(20.dp))
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
    Spacer(modifier = Modifier.height(20.dp))
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

@Composable
fun SelectableBox(
    items: List<String>,
    onSelectionChanged: (List<String>) -> Unit
) {
    // Estado para armazenar os itens selecionados
    var selectedItems by remember { mutableStateOf(setOf<String>()) }

    // Função para lidar com a seleção de itens
     fun toggleSelection(item: String) {
        selectedItems = if (selectedItems.contains(item)) {
            selectedItems - item
        } else {
            selectedItems + item
        }
        // Chama a função de callback quando a seleção muda
        onSelectionChanged(selectedItems.toList())
    }
    val scrollState = rememberScrollState()
    // Composable Box que contém a lista de itens
    Box(modifier = Modifier
        .padding(16.dp)
        .border(BorderStroke(1.dp, Color(22, 15, 65)),
            shape = RoundedCornerShape(10.dp))
        .height(100.dp)
    ) {
        Row {
            Column(modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)) {
                items.forEach { item ->
                    Text(
                        text = item,
                        color = Color(22, 15, 65),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { toggleSelection(item) }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

