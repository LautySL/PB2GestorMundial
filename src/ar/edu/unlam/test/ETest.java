package ar.edu.unlam.test;

import static org.junit.Assert.*;

import java.util.*;

import ar.edu.unlam.*;

import org.junit.Test;

public class ETest {

	private String selecciones[] = {"Qatar", "Ecuador", "Senegal", "Paises Bajos", "Inglaterra", "Irán", "EEUU", "Gales", "Argentina", "Arabia Saudita", "México", "Polonia", "Francia", "Australia", "Dinamarca", "Tunez", "España", "Costa Rica", "Alemania", "Japón", "Bélgica", "Canadá", "Marruecos", "Croacia", "Brasil", "Serbia", "Suiza", "Camerún", "Portugal", "Ghana", "Uruguay", "Corea del Sur"};
	
	private Torneo inicializarTorneo() {
		List <Equipo> equipos = new ArrayList <>();
		int grupo = 0;
		
		for (int i = 0; i < selecciones.length; i++) {
			if (i != 0 && i % 4 == 0){
				grupo++;
			}
			equipos.add(new Equipo (selecciones[i], Grupo.values()[grupo]));
		} 
		Torneo torneo = new Torneo (equipos);
		return torneo;
	}  
	
	@Test
	public void queSePuedaCrearUnTorneoCon32Equipos() {
		Torneo torneoNuevo = inicializarTorneo();
		assertNotNull(torneoNuevo);
	}
	
	@Test
	public void queSePuedaCrearUnPartidoDeGruposConDosEquiposDelMismoGrupo() throws Exception {
		Torneo torneo = inicializarTorneo();
		
		Equipo Qatar = torneo.getEquipos().get(0);
		Equipo Ecuador = torneo.getEquipos().get(1);
		
		torneo.registrarPartido(123, Qatar, Ecuador);
		
		assertNotNull(torneo.getPartidoPorID(123));
	}
	
	@Test
	public void queSePuedaCrearUnPartidoDeEliminatoriasConDosEquiposPertenecientesALaListaDeEquiposEnEliminatorias() throws Exception {
		Torneo torneo = inicializarTorneo();
		
		Equipo Croacia = torneo.getEquipos().get(23);
		Equipo Canada = torneo.getEquipos().get(21);
		Equipo Argentina = torneo.getEquipos().get(8);
		Equipo Mexico = torneo.getEquipos().get(10);
		
		torneo.registrarPartido(1234, Croacia, Canada);
		torneo.registrarPartido(6789, Argentina, Mexico);
		
		torneo.registrarResultado(1234, 4, 1);
		torneo.registrarResultado(6789, 2, 0);
		
		torneo.finalizarFaseDeGrupos();
		
		torneo.registrarPartido(3714, Argentina, Croacia);
		
		torneo.registrarResultado(3714, 3, 0);
		
		Integer cantidadDePartidosJugados = torneo.getPartidos().size();
		
		assertEquals ((Integer) 3, cantidadDePartidosJugados);
	}
	
	
	@Test (expected = PartidoJugadoException.class)
	public void queAlCrearUnPartidoDondeLosEquiposYaJugaronSeLanceUnaPartidoJugadoException() throws Exception {
		Torneo torneo = inicializarTorneo();
		
		Equipo Qatar = torneo.getEquipos().get(0);
		Equipo Ecuador = torneo.getEquipos().get(1);
		
		torneo.registrarPartido(123, Qatar, Ecuador);
		
		torneo.registrarPartido(123, Qatar, Ecuador);
	}
	
	@Test (expected = EquipoDuplicadoException.class)
	public void queAlCrearUnPartidoDeGruposDondeElEquipoLocalEsElMismoQueElEquipoRivalSeLanceUnaEquipoDuplicadoException() throws Exception {
		Torneo torneo = inicializarTorneo();
		
		Equipo Qatar = torneo.getEquipos().get(0);
		
		torneo.registrarPartido(123, Qatar, Qatar);
	}
	
	@Test
	public void queAlRegistrarElResultadoDeUnPartidoDeGruposSeAcumulenLosPuntosCorrespondientesACadaEquipo() throws Exception {
		Torneo torneo = inicializarTorneo();
		
		Equipo Argentina = torneo.getEquipos().get(8);
		Equipo Arabia_Saudita = torneo.getEquipos().get(9);
		
		torneo.registrarPartido(1234, Argentina, Arabia_Saudita);
		
		torneo.registrarResultado(1234, 1, 2);
		
		Integer puntajeDeArgentina = Argentina.getPuntaje();
		Integer puntajeDeArabiaSaudita = Arabia_Saudita.getPuntaje();
		
		assertEquals ((Integer) 0 , puntajeDeArgentina);
		assertEquals ((Integer) 3 , puntajeDeArabiaSaudita);
	}
	
	@Test
	public void queAlObtenerElResultadoDeUnPartidoDeGruposSeaElElementoEmpateDelEnum() throws Exception {
		Torneo torneo = inicializarTorneo();
		
		Equipo Mexico = torneo.getEquipos().get(10);
		Equipo Polonia = torneo.getEquipos().get(11);
		
		torneo.registrarPartido(1234, Mexico, Polonia);
		
		torneo.registrarResultado(1234, 0, 0);
		
		Resultado resultadoEsperado = Resultado.EMPATE;
		Resultado resultadoDelPartido = torneo.obtenerResultadoDeUnPartido(1234);
		
		assertEquals(resultadoEsperado , resultadoDelPartido);
	}
	
	@Test
	public void queAlObtenerElResultadoDeUnPartidoDeEliminatoriasEnCasoDeEmpateSeObtengaElEnumDelGanadorPorPenales() throws Exception {
		Torneo torneo = inicializarTorneo();
		
		torneo.finalizarFaseDeGrupos();
		
		Equipo Brasil = torneo.getEquipos().get(22);
		Equipo Croacia = torneo.getEquipos().get(23);
		
		torneo.registrarPartido(1234, Brasil, Croacia);
		
		torneo.registrarResultado(1234, 1, 1, 2, 4);
		
		Resultado resultadoEsperado = Resultado.GANA_VISITANTE;
		Resultado resultadoDelPartido = torneo.obtenerResultadoDeUnPartido(1234);
		
		assertEquals(resultadoEsperado , resultadoDelPartido);
	}
	
	@Test
	public void queAlConsultarPuntajeDeEquiposDeLosGrupoSeObtenganLosEquiposOrdenadosPorGrupoAscendenteYPorPuntosDescendentemente() throws Exception {
		Torneo torneo = inicializarTorneo();
		
		Equipo Qatar = torneo.getEquipos().get(0);
		Equipo Ecuador = torneo.getEquipos().get(1);
		
		torneo.registrarPartido(1234, Qatar, Ecuador);
		
		torneo.registrarResultado(1234, 0, 2);
		
		torneo.ordenarGrupos();
		
		assertEquals(torneo.getEquipos().get(0), Ecuador);
		
	}
	
	@Test
	public void queAlFinalizarLaFaseDeGruposSeAgreguenLosMejores2EquiposDeCadaGrupoALaColeccionDeEquiposEnEliminatorias() throws Exception {
		
		Torneo torneo = inicializarTorneo();
		
		Equipo Qatar = torneo.getEquipos().get(0);
		Equipo Ecuador = torneo.getEquipos().get(1);
	
		torneo.registrarPartido(1234, Qatar, Ecuador);
		
		torneo.registrarResultado(1234, 0, 2);
		
		torneo.finalizarFaseDeGrupos();
		
		Integer cantidadDeEquipos = torneo.getEquiposEnEliminatorias().size();
		
		assertEquals((Integer) 16, cantidadDeEquipos);
		
	
	}
}
