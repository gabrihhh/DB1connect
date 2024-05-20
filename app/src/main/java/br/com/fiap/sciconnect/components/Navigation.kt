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
import androidx.compose.ui.draw.scale
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
    rota: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(alignment = Alignment.BottomStart)
                .background(
                    Color(255, 255, 255)
                ),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        navController.navigate("home")
                    }
            ) {
                Image(
                    painter = painterResource(
                        if(rota ==="home"){
                            R.drawable.exploreativo
                        }else{
                            R.drawable.explore
                        }
                    ),
                    contentDescription = "Home",
                    modifier = Modifier
                        .scale(2.0f)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        navController.navigate("explorer")
                    }
            ) {
                Image(
                    painter = painterResource(
                        if(rota ==="explore"){
                            R.drawable.searchativo
                        }else{
                            R.drawable.search
                        }
                    ),
                    contentDescription = "explorer",
                    modifier = Modifier
                        .scale(2.0f)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {navController.navigate("message")}
            ) {
                val context = LocalContext.current
                val postRepository = PostRepository(context)
                    Image(
                        painter = painterResource(
                            if(rota ==="msg"){
                                R.drawable.msgativo
                            }else{
                                R.drawable.msg
                            }
                        ),
                        contentDescription = "List",
                        modifier = Modifier
                            .scale(2.0f)
                    )

            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate("profile") }
            ) {
                Image(
                    painter = painterResource(
                        if(rota ==="user"){
                            R.drawable.userativo
                        }else{
                            R.drawable.user
                        }
                    ),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .scale(2.0f)
                )
            }
        }
    }
}