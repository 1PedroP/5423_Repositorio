package pt.iefp.myapplication_1

import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {



            var nome_v2 by remember {
                mutableStateOf("Adivinhe:")
            }

            var txt1 by remember {
                mutableStateOf("")
            }

            var txt1_len by remember {
                mutableStateOf(txt1.length)
            }

            var numeroSorteado by remember {
                mutableIntStateOf(Random.nextInt(1, 10)) // Número entre 1 e 10
            }


            Column {



                Spacer(modifier = Modifier.height(50.dp))



                Spacer(modifier = Modifier.height(50.dp))

                Row{


                    Spacer(modifier = Modifier)

                    Mytxt(msg = "Insira um número entre 1 e 10:")

                }// row 1

                Spacer(modifier = Modifier.height(30.dp))








                Column(modifier = Modifier.padding(
                    horizontal = 15.dp,
                    vertical = 40.dp)
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp)) // espaço de 16 dp

                        Text(
                            text = nome_v2,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black,
                            fontSize = 30.sp,
                            fontStyle = FontStyle.Italic
                        )

                        Spacer(modifier = Modifier.height(16.dp)) // espaço abaixo do texto


                    }



                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        TextField(
                            value = txt1,
                            onValueChange = { txt1 = it
                                            txt1_len = it.length},
                            modifier = Modifier.width(250.dp),
                            textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center)
                        )
                    }
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Num chars : $txt1_len"
                    )

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                val tentativa = txt1.toIntOrNull()
                                if (tentativa != null) {
                                    when {
                                        tentativa !in 1..10 -> {
                                            nome_v2 = "O número deve estar entre 1 e 10!"
                                        }
                                        tentativa == numeroSorteado -> {
                                            nome_v2 = "Parabéns! Era o número $numeroSorteado!"
                                            numeroSorteado = Random.nextInt(1, 11)
                                        }
                                        else -> {
                                            nome_v2 = "Errado! Tente novamente."
                                        }
                                    }
                                } else {
                                    nome_v2 = "Digite um número válido!"
                                }
                                txt1 = ""

                                Log.d("Tentativa", "Valor digitado: $tentativa, Número certo: $numeroSorteado")
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .padding(top = 20.dp)
                        ) {
                            Text(
                                text = "Submeter",

                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } // Column 1

        }
    }
}

@Composable
fun Mytxt(msg: String) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = msg,
            fontWeight = FontWeight.Normal,
            color = Color.Blue,
            fontSize = 30.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center, modifier = Modifier.width(425.dp)
        )
    }
}
















