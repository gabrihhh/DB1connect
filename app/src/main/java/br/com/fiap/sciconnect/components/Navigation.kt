package br.com.fiap.sciconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.sciconnect.R
import br.com.fiap.sciconnect.database.repository.PostRepository

@Composable
fun Navigation(
    navController: NavController,
    darkmode: MutableState<Boolean>,
    admin: MutableState<Boolean>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(alignment = Alignment.BottomStart)
                .background(
                    if (darkmode.value) Color(49, 52, 57) else Color(255, 255, 255)
                ),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {}
            ) {
                Image(
                    painter = painterResource(
                        id = if (darkmode.value) R.drawable.homedark else R.drawable.home
                    ),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        navController.navigate("post")
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = if (darkmode.value) R.drawable.adddark else R.drawable.add
                    ),
                    contentDescription = "Add",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {navController.navigate("await")}
            ) {
                val context = LocalContext.current
                val postRepository = PostRepository(context)
                var listaAwaitingPosts = remember {
                    mutableStateOf(postRepository.listarAwaitingPosts())
                }
                if(listaAwaitingPosts.value.size > 0 && admin.value == true){
                    Image(
                        painter = painterResource(
                            id = if (darkmode.value) R.drawable.listdark else R.drawable.list_notification
                        ),
                        contentDescription = "List",
                        modifier = Modifier
                            .size(28.dp)
                    )
                }else{
                    Image(
                        painter = painterResource(
                            id = if (darkmode.value) R.drawable.listdark else R.drawable.list
                        ),
                        contentDescription = "List",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate("home") }
            ) {
                Image(
                    painter = painterResource(
                        id = if (darkmode.value) R.drawable.persondark else R.drawable.person
                    ),
                    contentDescription = "List",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }
}