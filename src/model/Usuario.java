package model;

/**
*
* @author EA
* @since Mayo 13, 2014
* @version 1.0.0
* @see VentanaPrincipal
*/

public class Usuario {
	
	//Atributos de la clase
	private String usuario;
	private String password;
	private String tipo;	
	
	/**
     * Constructor sin parámetros.
     */
	public Usuario(){
			
	}

	/**
     * Para que puedan acceder desde otra clase del mismo paquete u otro paquete.
     * @return usuario Devuelve el nombre del usuario
     */
	public String getUsuario() {
		return usuario;
	}

	/**
     * Obtener la contraseña del usuario.
     * 
     * @return password Devuelve la clave del usuario.
     */
	public String getPassword() {
		return password;
	}

	/**
     * Acceder al atributo tipo del método getter desde otra clase del mismo paquete o de otro paquete.
     * 
     * @return tipo Devuelve el tipo de usuario.
     */
	public String getTipo() {
		return tipo;
	}

	//Cierre de métodos getters
	
	
	/**
     * Para establecer el atributo usuario desde otra clase.
     * 
     * @param usuario Establece el nombre del usuario.
     */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
     * Modifica la clave del usuario.
     * 
     * @param password Establece la constraseña del usuario.
     */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * Establece el tipo de usuario.
     * 
     * @param tipo Establece el tipo de usuario como el encargado o de consulta.
     */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
} //Cierre de clase
