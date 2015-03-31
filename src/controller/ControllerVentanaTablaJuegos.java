package controller;

import java.awt.event.*;

import view.VentanaTablaJuegos;

/**
 * Controla la clase VentanaTablaJuegos.
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see VentanaTablaJuegos
 */

public class ControllerVentanaTablaJuegos implements ActionListener{

	//Atributos de la clase
	private VentanaTablaJuegos ventanaTablaJuegos;
	
	//Constructor
	public ControllerVentanaTablaJuegos(){
		
		ventanaTablaJuegos=new VentanaTablaJuegos();
		ventanaTablaJuegos.setLocationRelativeTo(null);
		ventanaTablaJuegos.setVisible(true);
		ventanaTablaJuegos.agregarListener(this);

	}
	
	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Menu")){
			
			ventanaTablaJuegos.dispose(); //Se cierra la clase VentanaTablaJuegos
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
		}
		
	}
}
