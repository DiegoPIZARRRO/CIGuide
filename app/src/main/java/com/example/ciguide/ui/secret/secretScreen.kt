package com.example.ciguide.ui.secret

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle


@Composable
fun secretScreen(){
    val music = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No se como lo hiciste,\npero lo encontraste.",
            style = MaterialTheme.typography.headlineMedium,
        )
        val musicURL = "https://music.youtube.com/watch?v=Q8r3p8luTFc&si=3fUdATsmA-dYnCnZ"
        val LinkString = buildAnnotatedString {
            pushStringAnnotation(tag = "URL", annotation = musicURL)
            withStyle(
                style = SpanStyle(
                    color = Color(0xFF64B5F6)
                )
            ) {
                append("Primero ponte esto pushale aquí")
            }
            pop()
        }
        ClickableText(
            text = LinkString,
            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
            onClick = { offset -> LinkString.getStringAnnotations(tag = "URL", start = offset, end = offset).firstOrNull()?.let {
                annotation -> music.openUri(annotation.item)}
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Bueno, ya que estas aquí, quiero que sepas el" +
                "\ncariño que le tengo a este juego tanto asi que" +
                "\npase horas jugando en mi punto mas bajo de" +
                "\nmi mentalidad, la primera vez que llegue a completar fue una nueva experiencia, pase" +
                "\npor muchos enojos, frustraciones de no poder terminarlo. Cada que derrotaba a un jefe era " +
                "\nun gran alivio y satisfaccion, sentia que " +
                "\nestaba mejorando no solo en el juego, sino tambien conmigo mismo, escuchando sus " +
                "\nsoundtracks, leyendo las historias de cada jefe, este juego para muchos puede ser otro " +
                "\njuego mas, pero al menos para mi fue algo que me ayudó a sentirme bien y superar me. " +
                "\nQuiero invitarte a ti tambien, no solo para jugar estó, sino como una pequeña escapada" +
                "\npara que sientas lo que es superar se a uno " +
                "\nmismo quizas muchas veces vas a querer rendirte porque lo has intentado muchas " +
                "\nveces y no lo vences, pero he ahi la superacion, aprende sus patrones, aprende que arma tiene mas dps contra el, " +
                "\nsi no te funciona con una clase prueba con otra, tambien esto puedes aplicarlo en ti, " +
                "\nsi algo no te funciona prueba con otras cosas u otros metodos. Si llegaste a este punto," +
                "\nQuiero que sepas que uno ya esta mejor ",
            style= MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}