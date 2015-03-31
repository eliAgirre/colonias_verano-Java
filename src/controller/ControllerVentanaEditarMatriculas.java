package controller;

import java.awt.event.*;

import view.VentanaEditarMatriculas;

/**
 * Controla la clase VentanaEditarMatriculas.
 * 
 * @author EA
 * @since Mayo 13, 2014
 * @version 1.0.0
 * @see VentanaEditarMatriculas
 */

public class ControllerVentanaEditarMatriculas implements ActionListener{

	//Atributos de la clase
	private VentanaEditarMatriculas ventanaEditarMatriculas;
	
	//Constructor
	public ControllerVentanaEditarMatriculas(String dni){
		
		ventanaEditarMatriculas=new VentanaEditarMatriculas(dni);
		ventanaEditarMatriculas.setLocationRelativeTo(null);
		ventanaEditarMatriculas.setVisible(true);
		ventanaEditarMatriculas.agregarListener(this);

	}

	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Volver")){
			
			ventanaEditarMatriculas.dispose(); //Se cierra la clase VentanaEditarMatriculas
			@SuppressWarnings("unused")
			ControllerVentanaTablaMatriculas controladorVentanaTablaMatriculas=new ControllerVentanaTablaMatriculas(); //Hacer visible la VentanaTablaMatriculas
		}
		
	}
}
