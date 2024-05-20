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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.DB1Connect.R
import br.com.fiap.DB1Connect.model.Usuario
import br.com.fiap.DB1Connect.service.RetrofitFactory
import br.com.fiap.db1connect.components.CircleWithLetter
import br.com.fiap.db1connect.components.Header
import br.com.fiap.db1connect.components.Navigation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var PaginarExplorer by mutableStateOf<String?>(null)
var Filtro by mutableStateOf<String>("")
var ListSelect = listOf("Item 1", "Item 2", "Item 3", "Item 4","Item 5","Item 6","Item 7","Item 8","Item 9","Item 10")
var selectedListSelect by mutableStateOf(listOf<String>())
var RadioSelectFiltro by mutableStateOf<Boolean>(true)
var RadioOptionFiltro by mutableStateOf<String>("")
var ListaRetornoFake = listOf("Gabelias", "Maycon", "Miguel")
var ListaUsuarios by mutableStateOf(listOf<Usuario>())

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
                "ExplorerReturn" -> ExplorerReturn()
                else -> ExplorerFilter()
            }
        }
        Navigation(navController,rota = "explore")
    }
}

@Composable
fun ExplorerReturn() {
    Box(modifier = Modifier
        .width(300.dp)
        .height(500.dp)){
        Column{
            Filter()
            Spacer(modifier = Modifier.height(20.dp))
            val scrollState = rememberScrollState()
            Box(modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .verticalScroll(scrollState)
                .background(
                    Color(22, 15, 65),
                    shape = RoundedCornerShape(10.dp)
                )
            ){
                Column {
                    //retorno da requisição do filtro
                    ListaUsuarios.forEach{ item ->
                    Box(modifier = Modifier
                        .padding(10.dp, 5.dp)
                        .clickable {
                            //fazer algo com o item
                        }){
                            Box(
                                modifier = Modifier
                                    .height(30.dp)
                                    .fillMaxWidth()
                                    .background(Color(255, 255, 255)),
                                contentAlignment = Alignment.CenterStart
                            ){
                                Row(){
                                    Spacer(modifier = Modifier.width(10.dp))
                                    CircleWithLetter(name = item.nome,size = 20.dp)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = item.nome, color = Color(22, 15, 65))
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ExplorerFilter(){
    Box(modifier = Modifier
        .width(300.dp)
        .height(500.dp)){
        Column{
            Filter()
            Spacer(modifier = Modifier.height(20.dp))
            Label(text = "Encontre pessoas com os mesmos interesses que você")

            SelectableBox(items = ListSelect) { selection ->
                selectedListSelect = selection
            }

            Label(text = "Áreas de interesse")
            val scrollState = rememberScrollState()
            Box(modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .border(BorderStroke(1.dp, Color(22, 15, 65)), shape = RoundedCornerShape(1.dp))
                .verticalScroll(scrollState)){
                Column(modifier = Modifier
                    .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceAround){
                    selectedListSelect.forEach { selectedItem ->
                        Box(
                            modifier = Modifier
                                .border(
                                    BorderStroke(1.dp, Color(22, 15, 65)),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    selectedListSelect =
                                        selectedListSelect.filter { it != selectedItem }
                                }
                                .padding(10.dp, 5.dp)
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
            Spacer(modifier = Modifier.height(10.dp))
            RadioGroupFiltro(
                selectedOption = RadioSelectFiltro,
                onOptionSelected = { RadioOptionFiltro = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .background(color = Color(22, 15, 65), shape = RoundedCornerShape(10.dp))
                    .clickable(onClick = {

                        var call = RetrofitFactory().getUsersService().getUsuarios()
                        call.enqueue(object : Callback<List<Usuario>> {
                            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                                ListaUsuarios = response.body()!!
                                PaginarExplorer = "ExplorerReturn"
                            }

                            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                    }),
                contentAlignment = androidx.compose.ui.Alignment.Center,
            ) {
                Text(
                    color= Color(255,255,255),
                    fontWeight = FontWeight.Bold,
                    text="Aplicar filtro"
                )
            }
        }
    }

}


@Composable
fun Filter(){
    Box(modifier = Modifier
        .width(300.dp)
        .border(BorderStroke(1.dp, Color(22, 15, 65)), shape = RoundedCornerShape(10.dp))
        .clickable {
            PaginarExplorer = "ExplorerFiltro"
        },
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
                ),
                )
            Image(painter = painterResource(id = R.drawable.search), contentDescription = "Search",
                modifier = Modifier
                    .scale(2.5f))
        }
    }
}

@Composable
fun RadioGroupFiltro(
    selectedOption: Boolean,
    onOptionSelected: (String) -> Unit
) {
    Column(
    ) {
        RadioButtonOption(
            text = "Tutores, Professores e profissionais da área",
            selected = selectedOption,
            onSelect = { onOptionSelected("Tutores") }
        )
        RadioButtonOption(
            text = "Aprendizes, Alunos e entusiastas",
            selected = !selectedOption,
            onSelect = { onOptionSelected("Aprendizes") }
        )
    }
}
