package model;

/**
*
* @author EA
* @since Mayo 14, 2014
* @version 1.0.0
*/

public class Grupo {

	//Atributos de la clase
	private int edad;
	private String grupo;
	
	//Constructor
	public Grupo(int edad, String grupo) {

		this.edad = edad;
		this.grupo = grupo;
	}

	public int getEdad() {
		return edad;
	}

	public String getGrupo() {
		return grupo;
	}
	
	//Cierre de getters
	
	/**
     * Para que en el JComboBox aparezca el grupo en la llamada "VentanaAnadirMatricula".
     * 
     * @return grupo Devuelve edad de tipo String.
     */
	public String toString() {
		
		return this.grupo;
	} //Cierre del método toString
}