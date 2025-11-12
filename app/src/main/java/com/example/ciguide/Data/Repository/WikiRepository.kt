package com.example.ciguide.Data.Repository

import com.example.ciguide.Data.Model.Boss
import kotlinx.coroutines.delay
import com.example.ciguide.R
//providence, DDD, yharon, exo mecanicos, calamitas

class WikiRepository {
    private val bosslist: List<Boss> = listOf(
        Boss(1, "Providence", "Diosa Profana, deidad ardiente de la luz y la oscuridad. Primer y gran jefe post-Señor de la Luna ",
               listOf("Arca de los elementos","Rayo alfa","Arco mecánico","Diseminador","Disco elemental","Hacha elemental"),
            imageRes = R.drawable.profaned_goddess
        ),
        Boss(2, "Devorador de Mundos", "Enorme gusano cósmico. requiere una importante preparación antes de intentar luchar ",
            listOf("El Ultimo luto","Tridente venusino","Baston de descarga sombria","Canion de acido sulfurico","Desgarrador macabro","Lamento de calamari"),
            imageRes = R.drawable.the_devourer_of_gods
        ),
        Boss(3, "Yharon, el dragon de la jungla", "Yharon es increíblemente poderoso y tiene decenas de ataques y varias fases, cada una más dura que la anterior.",
            listOf("Cuchillos empireos","Gran baston del mago de nebulosa","Alluvion","Caida del eclipse","hoja de verdugo","Baston de heraldo corvido"),
            imageRes = R.drawable.yharon_render
        ),
        Boss(4, "Exo mecanicos", "Draedon es un maestro herrero y científico cibernético, y el jugador lucha contra sus Exo Mechs: Ares, Apollo, Artemis y Thanatos.",
            listOf("El oraculo","Cristal de yharim","Mini-gun","Ala de colera","Baston brillante de yharon","El baston de gusano mecanico"),
            imageRes = R.drawable.exo_mechs
        ),
        Boss(5, "La bruja suprema ,Calamitas", "Una bruja de azufre increíblemente poderosa. Es el jefe final del Calamity Mod",
            listOf("El oraculo","Murasama","Cristal de yharim","Fotoviscerador","Ala de colera","Baston brillante de yharon"),
            imageRes = R.drawable.calamita
        )
    )
    suspend fun getBosses(): List<Boss> {
        delay(2000)
        return bosslist
    }
}
