package model;

/**
 * Tiene un atributo para que pueda guardar los datos de la base de datos de la tabla Edad al crear el objeto de esta clase y mostrar el tipo en el primer JComboBox en la clase VentanaAnadirMatricula.
 * 
* @author EA
* @since Mayo 14, 2014
* @version 1.0.0
*/

public class Edad {
	
	//Atributos de la clase
	private int edad; //Edad (entre 3-5, y 6-8).
	
	/**
     * Constructor tiene como parámetro un único atributo
     * @param edad Define la edad de cada niño matriculado.
     */
	public Edad(int edad) {
		
		this.edad = edad;
	} //Cierre del constructor

	/**
     * Para que puedan acceder desde otra clase del mismo paquete u otro paquete.
     * @return edad Devuelve la edad del niño en entero.
     */
	public int getEdad() {
		return edad;
	}
	//Cierre de métodos getters.

	/**
     * Para que en el JComboBox aparezca la edad en la llamada "VentanaAnadirMatricula".
     * 
     * @return edad Devuelve edad de tipo String.
     */
	public String toString() {
		
		String edad=String.valueOf(this.edad);
		
		return edad;
	} //Cierre del método toString
	
} //Cierre de la clase 
