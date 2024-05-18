package br.com.fiap.sciconnect.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.sciconnect.database.repository.PostRepository
import br.com.fiap.sciconnect.model.Post
import br.com.fiap.sciconnect.components.Header
import br.com.fiap.sciconnect.components.LetterAvatar
import br.com.fiap.sciconnect.components.Navigation

@Composable
fun HomeScreen(
    navController: NavController,
    darkmode: MutableState<Boolean>,
    admin: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(darkmode = darkmode, admin = admin)
        Column(
            modifier = Modifier
                .padding(vertical = 100.dp)
                .fillMaxSize()
                .background(
                    Color.LightGray
                )
            //.verticalScroll(scroll)
        ) {

        }
        Navigation(navController, darkmode = darkmode, admin = admin)
    }
}