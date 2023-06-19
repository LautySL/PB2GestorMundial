package ar.edu.unlam;

import java.util.Objects;

public class Partido {
	
	private Integer idPartido;
	private Integer golesLocal, golesVisitante, penalesConvertidosLocales, penalesConvertidosVisitante;
	private Equipo local;
	private Equipo rival;
	private Resultado resultado;

	public Partido (Integer idPartido, Equipo local, Equipo rival) {
		this.idPartido = idPartido;
		this.local = local;
		this.rival = rival;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultadoDelPartido(Resultado resultadoDelPartido) {
		this.resultado = resultadoDelPartido;
	}

	public Integer getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getRival() {
		return rival;
	}

	public void setRival(Equipo rival) {
		this.rival = rival;
	}
	
	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}
	
	public Integer getPenalesConvertidosLocales() {
		return penalesConvertidosLocales;
	}

	public void setPenalesConvertidosLocales(Integer penalesConvertidosLocales) {
		this.penalesConvertidosLocales = penalesConvertidosLocales;
	}

	public Integer getPenalesConvertidosVisitante() {
		return penalesConvertidosVisitante;
	}

	public void setPenalesConvertidosVisitante(Integer penalesConvertidosVisitante) {
		this.penalesConvertidosVisitante = penalesConvertidosVisitante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPartido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(idPartido, other.idPartido);
	}
	
}
