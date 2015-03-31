package controller;

import java.awt.event.*;

import view.VentanaConsultaJuegos;

/**
 * Controla la clase VentanaConsultaJuegos.
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see VentanaConsultaJuegos
 */

public class ControllerVentanaConsultarJuegos implements ActionListener{

	//Atributos de la clase
	private VentanaConsultaJuegos ventanaConsultaJuegos;
	
	//Constructor
	public ControllerVentanaConsultarJuegos(){
		
		ventanaConsultaJuegos=new VentanaConsultaJuegos();
		ventanaConsultaJuegos.setLocationRelativeTo(null);
		ventanaConsultaJuegos.setVisible(true);
		ventanaConsultaJuegos.agregarListener(this);

	}

	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Menu")){
			
			ventanaConsultaJuegos.dispose(); //Se cierra la clase VentanaConsultaJuegos
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
		}
		
	}
}
