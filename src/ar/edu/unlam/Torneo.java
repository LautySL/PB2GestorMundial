package ar.edu.unlam;

import java.util.*;

public class Torneo {
	
	private List <Equipo> equipos, equiposEnEliminatorias;
	private Set <Partido> partidos;
	private Fase fase;
	
	public Torneo (List <Equipo> equipos) {
		this.equipos = equipos;
		this.equiposEnEliminatorias = new ArrayList <Equipo>();
		this.partidos = new HashSet <Partido>();
		this.fase = Fase.GRUPOS;
	}

	public Boolean registrarPartido (Integer idPartido, Equipo local, Equipo rival) throws PartidoJugadoException, EquipoDuplicadoException {
		Partido partidoARegistrar = new Partido (idPartido, local, rival);
		Boolean seRegistroElPartido = false;
		
		 if (local.getNombre().equals(rival.getNombre())) {
		        throw new EquipoDuplicadoException();
		    }

		    if (this.fase.equals(Fase.GRUPOS)) {
		        if (local.getGrupo().equals(rival.getGrupo())) {
		            boolean existePartido = false;
		            for (Partido partido : partidos) {
		                if (partidoARegistrar.getIdPartido().equals(partido.getIdPartido())) {
		                    existePartido = true;
		                    break;
		                }
		            }
		            if (!existePartido) {
		                this.partidos.add(partidoARegistrar);
		                seRegistroElPartido = true;
		            } else {
		                throw new PartidoJugadoException();
		            }
		        } else {
		            return false;
		        }
		    } else {
		        boolean existePartido = false;
		        for (Partido partido : partidos) {
		            if (partidoARegistrar.getIdPartido().equals(partido.getIdPartido())) {
		                existePartido = true;
		                break;
		            }
		        }
		        if (!existePartido) {
		            this.partidos.add(partidoARegistrar);
		            seRegistroElPartido = true;
		        } else {
		        	throw new PartidoJugadoException();
		        }
		    }
			
		if (this.partidos.size() == 0) {
			this.partidos.add(partidoARegistrar);
			seRegistroElPartido = true;
		}
		 return seRegistroElPartido;
	}
	
	public void ordenarGrupos() {
		Collections.sort(this.equipos);
	}
	
	public void finalizarFaseDeGrupos() {
		this.fase = Fase.ELIMINATORIAS;	
		ordenarGrupos();
		List <Integer> indices = new ArrayList<>(Arrays.asList(0,1,4,5,8,9,12,13,16,17,20,21,24,25,28,29));

		for (Integer indice : indices) {
			this.equiposEnEliminatorias.add(this.equipos.get(indice));
		}
	}
	
	private void sumarPuntaje (Integer idPartido) {
		Partido partidoASumarPuntaje = getPartidoPorID(idPartido);
		
		Equipo local = partidoASumarPuntaje.getLocal();
		Equipo rival = partidoASumarPuntaje.getRival();
		Resultado resultadoDelPartido = partidoASumarPuntaje.getResultado();
		
		switch (resultadoDelPartido) {
		case GANA_LOCAL:
				local.setPuntaje(local.getPuntaje() + 3);
			break;
		case GANA_VISITANTE:
				rival.setPuntaje(rival.getPuntaje() + 3);
			break;
		case EMPATE:
				local.setPuntaje(local.getPuntaje() + 1);
				rival.setPuntaje(rival.getPuntaje() + 1);
			break;
		}
	}
	
	public Resultado obtenerResultadoDeUnPartido (Integer idPartido) {
		Partido partidoAObtenerResultado = getPartidoPorID(idPartido);
		Resultado resultadoDelPartido = null;
		resultadoDelPartido = partidoAObtenerResultado.getResultado();
		return resultadoDelPartido;
	}
	
	private void sumarYRestarGolesALosEquipos (Integer idPartido) {
		Partido partido = getPartidoPorID (idPartido);
		
		Equipo local = partido.getLocal();
		Equipo rival = partido.getRival();
		
		Integer golesLocal = partido.getGolesLocal();
		Integer golesVisitante = partido.getGolesVisitante();
		
		local.sumarGolesAFavor(golesLocal);
		local.restarGolesEnContra(golesVisitante);
		
		rival.sumarGolesAFavor(golesVisitante);
		rival.restarGolesEnContra(golesLocal);
	}
	
	public void registrarResultado (Integer idPartido, Integer golesLocal, Integer golesVisitante) {
		Partido partido = getPartidoPorID(idPartido);
		
		partido.setGolesLocal(golesLocal);
		partido.setGolesVisitante(golesVisitante);
		
		if (partido.getGolesLocal() > partido.getGolesVisitante()) {
			partido.setResultadoDelPartido(Resultado.GANA_LOCAL);
		} else if (partido.getGolesLocal() < partido.getGolesVisitante()) {
			partido.setResultadoDelPartido(Resultado.GANA_VISITANTE);
		} else if (partido.getGolesLocal().equals(partido.getGolesVisitante())) {
			partido.setResultadoDelPartido(Resultado.EMPATE);	
		}
		sumarPuntaje(idPartido);
		sumarYRestarGolesALosEquipos(idPartido);
	}
	
	public void registrarResultado (Integer idPartido, Integer golesLocal, Integer golesVisitante, Integer penalesLocal, Integer penalesVisitante) {
		Partido partido = getPartidoPorID(idPartido);
		
		partido.setGolesLocal(golesLocal);
		partido.setGolesVisitante(golesVisitante);
		partido.setPenalesConvertidosLocales(penalesLocal);
		partido.setPenalesConvertidosVisitante(penalesVisitante);
		
		if (partido.getGolesLocal() > partido.getGolesVisitante()) {	
			partido.setResultadoDelPartido(Resultado.GANA_LOCAL);
		} else if (partido.getGolesLocal() < partido.getGolesVisitante()) {			
			partido.setResultadoDelPartido(Resultado.GANA_VISITANTE);			
		} else if (partido.getGolesLocal().equals(partido.getGolesVisitante())) {
			if (partido.getPenalesConvertidosLocales() > partido.getPenalesConvertidosVisitante()) {		
				partido.setResultadoDelPartido(Resultado.GANA_LOCAL);	
			} else {
				partido.setResultadoDelPartido(Resultado.GANA_VISITANTE);
			}
		}
		sumarPuntaje(idPartido);
		sumarYRestarGolesALosEquipos(idPartido);
	}

	public Set<Partido> getPartidos() {
		return partidos;
	}
	
	public Partido getPartidoPorID (Integer idPartido) {
		Partido partidoABuscar = null;
		for (Partido partido : partidos) {
			if (partido.getIdPartido().equals(idPartido)) {
				partidoABuscar = partido;
			}
		} return partidoABuscar;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	public List<Equipo> getEquiposEnEliminatorias() {
		return equiposEnEliminatorias;
	}
	
	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase faseDelPartido) {
		this.fase = faseDelPartido;
	}

	public void setEquiposEnEliminatorias(List<Equipo> equiposEnEliminatorias) {
		this.equiposEnEliminatorias = equiposEnEliminatorias;
	}
	
	
	
}

