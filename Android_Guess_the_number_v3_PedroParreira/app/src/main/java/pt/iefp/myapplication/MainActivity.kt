package pt.iefp.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeView(navController)
        }

        composable(
            route = "novoJogo/{nome}",
            arguments = listOf(navArgument("nome") {
                type = NavType.StringType
                defaultValue = "Jogador"
            })
        ) { backStackEntry ->
            val nome = backStackEntry.arguments?.getString("nome") ?: "Jogador"
            NovoJogo(nome, navController)
        }


    }
}
