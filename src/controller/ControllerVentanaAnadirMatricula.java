package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VentanaAnadirMatriculas;

/**
 * Controla la clase VentanaAnadirMatriculas.
 * 
 * @author EA
 * @since Mayo 13, 2014
 * @version 1.0.0
 * @see VentanaAnadirMatriculas
 */

public class ControllerVentanaAnadirMatricula implements ActionListener {

	//Atributos de la clase
	private VentanaAnadirMatriculas ventanaAnadirMatriculas;
	
	//Constructor
	public ControllerVentanaAnadirMatricula(){
		
		ventanaAnadirMatriculas=new VentanaAnadirMatriculas();
		ventanaAnadirMatriculas.setLocationRelativeTo(null);
		ventanaAnadirMatriculas.setVisible(true);
		ventanaAnadirMatriculas.agregarListener(this);

	} //Cierre de constructor
	
	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Menu")){
			
			ventanaAnadirMatriculas.dispose(); //Se cierra la clase VentanaAnadirMatriculas
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
		} //Cierre del evento del botón "Volver"
		
	} //Cierre del método actionPerformed
	
} //Cierre de clase
