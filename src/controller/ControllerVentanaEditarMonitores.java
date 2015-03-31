package controller;

import java.awt.event.*;
import view.VentanaEditarMonitores;

/**
 * Controla la clase VentanaEditarMonitores.
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see VentanaEditarMonitores
 */

public class ControllerVentanaEditarMonitores implements ActionListener{

	//Atributos de la clase
	private VentanaEditarMonitores ventanaEditarMonitores;
	
	//Constructor
	public ControllerVentanaEditarMonitores(String dni){
		
		ventanaEditarMonitores=new VentanaEditarMonitores(dni);
		ventanaEditarMonitores.setLocationRelativeTo(null);
		ventanaEditarMonitores.setVisible(true);
		ventanaEditarMonitores.agregarListener(this);

	}
	
	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Menu")){
			
			ventanaEditarMonitores.dispose(); //Se cierra la clase VentanaEditarMonitores
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
		}
		
	}

}
