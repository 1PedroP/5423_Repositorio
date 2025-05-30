package pt.iefp.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.random.Random

@Composable
fun NovoJogo(nome: String, navController: NavHostController) {
    var mensagem by remember { mutableStateOf("🎯 Olá, $nome! Adivinhe um número entre 1 e 10!") }
    var entrada by remember { mutableStateOf("") }
    var numeroSorteado by remember { mutableIntStateOf(Random.nextInt(1, 11)) }
    var tentativasJogador by remember { mutableStateOf(mutableListOf<Int>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = mensagem,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = when {
                mensagem.contains("Parabéns") -> Color(0xFF4CAF50)
                mensagem.contains("Errado") -> Color(0xFFF44336)
                else -> MaterialTheme.colorScheme.onBackground
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = entrada,
            onValueChange = { entrada = it },
            label = { Text("Digite um número (1–10)", fontSize = 24.sp) },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 28.sp),
            modifier = Modifier
                .width(400.dp)
                .height(70.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    entrada = ""
                    mensagem = "🎯 Olá, $nome! Adivinhe um número entre 1 e 10"
                    numeroSorteado = Random.nextInt(1, 11)
                    tentativasJogador.clear()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0097A7))
            ) {
                Text("Novo Jogo", fontSize = 16.sp)
            }

            Button(
                onClick = {
                    val tentativa = entrada.toIntOrNull()
                    if (tentativa != null && tentativa in 1..10) {
                        tentativasJogador.add(tentativa) // Registra as tentativas
                    }

                    mensagem = when {
                        tentativa == null -> "⚠️ Digite um número válido!"
                        tentativa !in 1..10 -> "🔢 O número deve estar entre 1 e 10!"
                        tentativa == numeroSorteado -> {
                            numeroSorteado = Random.nextInt(1, 11)
                            "🎉 Parabéns, $nome! Acertou!"
                        }
                        else -> "❌ Errado, $nome! Tente novamente."
                    }
                    entrada = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1))
            ) {
                Text("Submeter", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("ranking/${tentativasJogador.joinToString(",")}") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E24AA))
        ) {
            Text("Ver Tentativas", fontSize = 16.sp, color = Color.White)
        }
    }
}
