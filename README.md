Gestión de mundial

En nuestro producto software permitiremos crear un torneo de 32 equipos repartidos en 8
grupos. Se pueden jugar partidos mientras que el equipo local y el rival sean distintos. En la
fase de grupos solo pueden jugar equipos del mismo grupo una vez entre sí (Ejemplos:
Qatar - Ecuador o Ecuador - Qatar). En caso de que se indique el mismo equipo para el
local y el rival se debe arrojar una EquipoDuplicadoException. Para registrar un partido se
deberá utilizar el método registrarPartido(idPartido, Equipo local, Equipo rival).
Todos los partidos ya sean de la fase de grupos o de la fase de eliminatorias deben
registrarse en la misma colección de partidos perteneciente al torneo.
En los partidos de grupos, no hay penales y el resultado puede ser empate. Para registrar el
resultado de un partido de fase de grupos se debe consumir el método
registrarResultado(idPartido, golesLocal, golesVisitantes).
Cuando un partido es de eliminatoria, si resultó en empate, se deben registrar los penales
consumiendo el método registrarResultado(idPartido, golesLocal, golesVisitantes,
penalesConvertidosLocal, penalesConvertidosVisitante).
Para conocer el resultado de un partido, se debe consumir el método
obtenerResultado(idPartido) devuelve un enum: EMPATE, GANA_LOCAL, GANA_VISITANTE.
Cada vez que un equipo gana un partido en la fase de grupos acumula 3 puntos, en caso de
empate se reparten 1 punto para cada uno. Cada vez que se registra un partido de fase de
grupos se acumulan los goles y puntos correspondientes a cada equipo (Goles a favor,
goles en contra y puntaje, deben formar parte de la clase Equipo).
En el torneo se debe permitir finalizar la fase de grupos el cual asigna a la colección de
equipos en eliminatorias, los equipos que pasan a la fase de eliminatorias (Los equipos que
pasan a la fase de eliminatorias son los dos primeros en puntaje de cada grupo).
Las selecciones en el torneo son las siguientes: "Catar", "Ecuador", "Senegal", "Países
Bajos", "Inglaterra", "Irán", "Estados Unidos", "Gales", "Argentina", "Arabia Saudita",
"México", "Polonia", "Francia", "Australia", "Dinamarca", "Túnez", "España", "Costa Rica",
"Alemania", "Japón", "Bélgica", "Canadá", "Marruecos", "Croacia", "Brasil", "Serbia", "Suiza",
"Camerún", "Portugal", "Ghana", "Uruguay", "Corea del Sur".

Tests a desarrollar
1. queSePuedaCrearUnTorneoCon32Equipos()
2. queSePuedaCrearUnPartidoDeGruposConDosEquiposDelMismoGrupo()
3. queSePuedaCrearUnPartidoDeEliminatoriasConDosEquiposPertenecientesALaLista
DeEquiposEnEliminatorias()
4. queAlCrearUnPartidoDondeLosEquiposYaJugaronSeLanceUnaPartidoJugadoExcept
ion()
5. queAlCrearUnPartidoDeGruposDondeElEquipoLocalEsElMismoQueElEquipoRivalSe
LanceUnaEquipoDuplicadoException()
6. queAlRegistrarElResultadoDeUnPartidoDeGruposSeAcumulenLosPuntosCorrespon
dientesACadaEquipo()
7. queAlObtenerElResultadoDeUnPartidoDeGruposSeaElElementoEmpateDelEnum()
8. queAlObtenerElResultadoDeUnPartidoDeEliminatoriasEnCasoDeEmpateSeObtenga
ElEnumDelGanadorPorPenales()
9. queAlConsultarPuntajeDeEquiposDeLosGrupoSeObtenganLosEquiposOrdenadosP
orGrupoAscendenteYPorPuntosDescendentemente()
10. queAlFinalizarLaFaseDeGruposSeAgreguenLosMejores2EquiposDeCadaGrupoALa
ColeccionDeEquiposEnEliminatorias()
