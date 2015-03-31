package model;

/**
*
* @author EA
* @since Mayo 16, 2014
* @version 1.0.0
*/

public class Juego {
	
	//Atributos de la clase
	private int id;
	private String nombre;
	private String tipo;
	private String objetivo;
	private String descripcion;
	private String grupo;
	
	//Constructor
	public Juego(int id, String nombre, String tipo, String objetivo, String descripcion,String grupo) {

		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.objetivo = objetivo;
		this.descripcion = descripcion;
		this.grupo = grupo;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	//Cierre de getters

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	//Cierre de setters

} //Cierre de la clase