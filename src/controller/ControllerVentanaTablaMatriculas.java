package controller;

import java.awt.event.*;

import view.VentanaTablaMatriculas;

/**
 * Controla la clase VentanaTablaMatriculas.
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see VentanaTablaMatriculas
 */

public class ControllerVentanaTablaMatriculas implements ActionListener{

	//Atributos de la clase
	private VentanaTablaMatriculas ventanaTablaMatriculas;
	
	//Constructor
	public ControllerVentanaTablaMatriculas(){
		
		ventanaTablaMatriculas=new VentanaTablaMatriculas();
		ventanaTablaMatriculas.setLocationRelativeTo(null);
		ventanaTablaMatriculas.setVisible(true);
		ventanaTablaMatriculas.agregarListener(this);

	}

	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Menu")){
			
			ventanaTablaMatriculas.dispose(); //Se cierra la clase VentanaTablaMatriculas
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
		}
		
	}
}
