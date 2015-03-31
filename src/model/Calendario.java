package model;

/**
 * 
 * 
 * @author EA
 * @since Mayo 20, 2014
 * @version 1.0.0
 */

public class Calendario {
	
	//Atributos de la clase
	private String dia;
	private String dniMonitor;
	private String nombreResponsable;
	private String apellidoResponsable;
	private String grupo;
	private String idJuego;
	private String nombreJuego;
	private String objetivo;
	private String descripcion;
	
	//Constructor
	public Calendario(String dia, String dniMonitor,String nombreResponsable, String apellidoResponsable, String grupo,String idJuego, String nombreJuego, String objetivo, String descripcion) {
	
		this.dia = dia;
		this.dniMonitor = dniMonitor;
		this.nombreResponsable = nombreResponsable;
		this.apellidoResponsable = apellidoResponsable;
		this.grupo = grupo;
		this.idJuego = idJuego;
		this.nombreJuego = nombreJuego;
		this.objetivo = objetivo;
		this.descripcion = descripcion;
	}

	public String getDia() {
		return dia;
	}

	public String getDniMonitor() {
		return dniMonitor;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public String getApellidoResponsable() {
		return apellidoResponsable;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getIdJuego() {
		return idJuego;
	}

	public String getNombreJuego() {
		return nombreJuego;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	//Cierre de getters
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public void setIdJuego(String idJuego) {
		this.idJuego = idJuego;
	}
	
	public void setNombreJuego(String nombreJuego) {
		this.nombreJuego = nombreJuego;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	//Cierre de setters

} //Cierre de la clase
