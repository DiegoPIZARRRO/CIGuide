package com.example.ciguide.ui.progression

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ciguide.R
import com.example.ciguide.ui.navigation.Screen

@Composable
fun Progression(navController: NavController){
    val scrollState = rememberScrollState()
    var tapCount by remember { mutableIntStateOf(0) }
    val tapsRequeridos = 3

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ){
        Text(
            text = "Progresion (Post Señor de la luna)",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Pre-Providencia", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "  - Luego de derrotar al señor de la luna, surgiran grandes cambios en el mundo.",
            fontSize=15.sp,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = buildAnnotatedString {append("  - Aparecen nuevos enemigos los cuales pueden" +
                    " llegar a aparecer en el inframundo o en el bioma sagrado." +
                    " Un jefe importante para llegar a Providencia, son")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append(" los guardianes profanos")
                }},
            fontSize=15.sp,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(id = R.drawable.profaned_guardians),
            contentDescription = "Guardianes profanos.",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Pre-Devorador de dioses", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "- Una vez de haber derrotado a la providencia se desbloqueará el mineral uelibloom," +
                " Asi logrando obtener las armaduras de tarragon.")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = buildAnnotatedString { append("- Apareceran 4 nuevos jefes complementarios los cuales son los siguientes:")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold))
                {append("\n - El tejedor de tormentas" +
                        "\n - El vacio incesante" +
                        "\n - Signus, el enviado del devorador" +
                        "\n - Polterghast") }})
        Image(
            painter= painterResource(id = R.drawable.polterghast),
            contentDescription = "Polterghast",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Fit
        )
        Text(text=("- Este ultimo deja algunos objetos \nimportantes para nuestra aventura mas tarde."))
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Pre-Yharon", fontWeight = FontWeight.Bold,fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "- Luego de derrotar al devorador de dioses este dejará unos lingotes de cosmilita," +
                "\n junto a este podras crear el yunque cosmico, dando acceso a armas de alto grado.")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = buildAnnotatedString {
            append("- Ahora los eventos de luna congelada, luna de calabaza y eclipse solar. Dejaran nuevos materiales:")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold))
            { append("\n - Energia endotermica" +
                     "\n - Combustible pesadilla" +
                     "\n - Fragmentos oscuridad"
                )
            }
        })
        Text(text = "Para este punto ya se puede ocupar la  famosa murasama")
        Image(
            painter = painterResource(id = R.drawable.murasama),
            contentDescription = "Murasama",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Pre-Calamitas/Exo-mecanicos",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "- Luego de derrotar a Yharon, soltará las alas de renacimiento, pudiendo crear las trazadoras de seraphines." +
                "\n- Ahora se desbloqueo el mineral aurico, dando acceso a crear las armaduras " +
                "\nde Auric tesla y algunas armas para finalizar el juego.")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "End-game")

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Mensaje de su creador:",
            fontWeight = FontWeight.Bold)
        Text(text = "Si llegaste hasta este punto significa que haz logrado grandes cosas y que de verdad te diste el tiempo de finalizar esta nueva aventura."+
             "\nTe invito a que pruebes el Rush Boss y si quieres un reto aun mayor, solo digo, Nameless deity.",
            modifier = Modifier.clickable{
                tapCount++
                if (tapCount == tapsRequeridos) {
                    navController.navigate(Screen.Secret.ruta)
                    tapCount = 0
                }
            })
    }
}