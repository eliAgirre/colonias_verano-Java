package controller;

import java.awt.event.*;

import view.VentanaEditarJuegos;

/**
 * Controla la clase VentanaEditarJuegos.
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see VentanaEditarJuegos
 */

public class ControllerVentanaEditarJuegos implements ActionListener{

	//Atributos de la clase
	private VentanaEditarJuegos ventanaEditarJuegos;
	
	//Constructor
	public ControllerVentanaEditarJuegos(int id){
		
		ventanaEditarJuegos=new VentanaEditarJuegos(id);
		ventanaEditarJuegos.setLocationRelativeTo(null);
		ventanaEditarJuegos.setVisible(true);
		ventanaEditarJuegos.agregarListener(this);
	}

	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Volver")){
			
			ventanaEditarJuegos.dispose(); //Se cierra la clase VentanaEditarJuegos
			@SuppressWarnings("unused")
			ControllerVentanaTablaJuegos controladorVentanaTablaJuegos=new ControllerVentanaTablaJuegos(); //Hace visible la VentanaTablasMatriculas
		}
		
	}
}
