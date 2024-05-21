package br.com.fiap.sciconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.sciconnect.screens.ExplorerScreen
import br.com.fiap.sciconnect.screens.LoginScreen
import br.com.fiap.sciconnect.screens.HomeScreen
import br.com.fiap.sciconnect.screens.MessageScreen
import br.com.fiap.sciconnect.screens.ProfileScreen
import br.com.fiap.sciconnect.screens.RegisterScreen
import br.com.fiap.sciconnect.ui.theme.SciConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SciConnectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val darkmode = remember{ mutableStateOf(false)}
                    var admin = remember {
                        mutableStateOf(false)
                    }
                    var user = remember {
                        mutableStateOf("admin")
                    }
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ){
                        composable(route = "login"){ LoginScreen(navController) }
                        composable(route = "register"){ RegisterScreen(navController)}
                        composable(route = "home"){ HomeScreen(navController) }
                        composable(route = "profile"){ ProfileScreen(navController)}
                        composable(route = "explorer"){ ExplorerScreen(navController)}
                        composable(route = "message"){ MessageScreen(navController)}
                    }
                }
            }
        }
    }
}