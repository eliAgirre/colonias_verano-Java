package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VentanaAnadirMonitores;

/**
 * Controla la clase VentanaAnadirMonitores.
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see VentanaAnadirMonitor
 */

public class ControllerVentanaAnadirMonitor implements ActionListener {

	//Atributos de la clase
	private VentanaAnadirMonitores ventanaAnadirMonitores;
	
	//Constructor
	public ControllerVentanaAnadirMonitor(){
		
		ventanaAnadirMonitores=new VentanaAnadirMonitores();
		ventanaAnadirMonitores.setLocationRelativeTo(null);
		ventanaAnadirMonitores.setVisible(true);
		ventanaAnadirMonitores.agregarListener(this);

	} //Cierre de constructor
	
	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Menu")){
			
			ventanaAnadirMonitores.dispose(); //Se cierra la clase VentanaAnadirMonitores
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
			
		} //Cierre del evento del botón "Volver"
		
	} //Cierre del método actionPerformed
} //Cierre de clase
