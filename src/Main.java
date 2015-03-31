import java.sql.SQLException;

import controller.ControllerEntrance;

/**
 * Ejecuta la aplicaci�n y controla la clase VentanaEntrada.
 * 
 * @author EA
 * @since Mayo 12, 2014
 * @version 1.0.0
 * @see ControladorEntrada
 */

public class Main {
	
	/** 
	 * Ejecuta la aplicaci�n y controla la clase VentanaEntrada.
	 * 
	 * @param args Array de Strings.
	 * @throws SQLException Contiene informaci�n sobre un error de acceso a la base de datos.
	 * 
	 */

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {
		
		//Se instancia la clase ControladorEntrada
		ControllerEntrance entrada = new ControllerEntrance(); 

	} //Cierre del m�todo main

} //Cierre de la clase 