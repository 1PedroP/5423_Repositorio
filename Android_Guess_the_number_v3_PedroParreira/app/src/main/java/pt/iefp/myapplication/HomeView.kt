package pt.iefp.myapplication

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeView(navController: NavHostController) {
    var nome by remember { mutableStateOf("") }

    val nomeValido = nomeReal(nome)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🎮 Bem-vindo ao\nJogo do Guess a Number!",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Insira o seu nome", fontSize = 18.sp) },
            singleLine = true,
            isError = nome.isNotBlank() && !nomeValido,
            modifier = Modifier.fillMaxWidth()
        )

        if (nome.isNotBlank() && !nomeValido) {
            Text(
                text = "Insira um nome completo válido (ex: João Silva).",
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("novoJogo/${nome.trim()}")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = nomeValido
        ) {
            Text("Começar Jogo", fontSize = 18.sp)
        }
    }
}

fun nomeReal(nome: String): Boolean {
    val nomeLimpo = nome.trim()


    if (!nomeLimpo.matches(Regex("^[A-Za-zÁÉÍÓÚáéíóúÂÊÔâêôÃÕãõÇç]{3,}$")))
        return false


    val todasIguais = nomeLimpo.toSet().size == 1
    if (todasIguais)
        return false

    return true
}


