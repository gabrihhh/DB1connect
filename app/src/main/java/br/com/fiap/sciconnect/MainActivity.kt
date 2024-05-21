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
import br.com.fiap.sciconnect.model.User
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
                        mutableStateOf<User?>(null)
                    }

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ){
                        composable(route = "login"){ LoginScreen(navController,user) }
                        composable(route = "register"){ RegisterScreen(navController, user)}
                        if (user != null) {
                            composable(route = "home") { HomeScreen(navController, user) }
                            composable(route = "profile") { ProfileScreen(navController,user) }
                            composable(route = "explorer") { ExplorerScreen(navController,user) }
                            composable(route = "message") { MessageScreen(navController,user) }
                        }else
                        {
                            navController.navigate("login")
                        }
                    }
                }
            }
        }
    }
}