package controller;

import java.sql.SQLException;

import view.VentanaEntrada;

/**
 * Tiene un atributo para controlar y abrir la VentanaEntrada.
 * 
 * @author EA
 * @since Mayo 12, 2014
 * @version 1.0.0
 * @see	VentanaEntrada
 * @see Main
 */

public class ControllerEntrance {

	//Atributo de la clase
	private static VentanaEntrada ventanaEntrada; //Declara la clase VentanaEntrada

	/**
     * Constructor sin parámetros.
	 * @throws SQLException Contiene información sobre un error de acceso a la base de datos.
     */
	public ControllerEntrance() throws SQLException {
			
		ventanaEntrada=new VentanaEntrada(); //Instancia la clase VentanaEntrada
		ventanaEntrada.setLocationRelativeTo(null); //Coloca la VentanaEntrada en el centro de la pantalla.
		ventanaEntrada.setVisible(true); //La VentanaEntrada es visible
		
	} //Cierre del constructor
	
} //Cierre de la clase