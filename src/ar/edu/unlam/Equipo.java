package ar.edu.unlam;

public class Equipo implements Comparable <Equipo> {

	private String nombre;
	private Grupo grupo;
	private Integer golesAFavor, golesEnContra, puntaje;
	
	public Equipo(String nombre, Grupo grupo) {
		super();
		this.nombre = nombre;
		this.grupo = grupo;
		this.golesAFavor = 0;
		this.golesEnContra = 0;
		this.puntaje = 0;
	}
	
	public void sumarGolesAFavor (Integer golesAFavor) {
		this.golesAFavor =+ golesAFavor;
	}
	
	public void restarGolesEnContra (Integer golesEnContra) {
		this.golesEnContra =- golesEnContra;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getGolesAFavor() {
		return golesAFavor;
	}

	public void setGolesAFavor(Integer golesAFavor) {
		this.golesAFavor = golesAFavor;
	}

	public Integer getGolesEnContra() {
		return golesEnContra;
	}

	public void setGolesEnContra(Integer golesEnContra) {
		this.golesEnContra = golesEnContra;
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}

	@Override
	public int compareTo(Equipo o) {
		if (this.grupo.equals(o.grupo)) {
			return -this.puntaje.compareTo(o.puntaje);
		}
		return this.grupo.compareTo(o.grupo);
	}
	
}
