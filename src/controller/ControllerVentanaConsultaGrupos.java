package controller;

import java.awt.event.*;

import view.VentanaConsultaGrupos;

/**
 * Controla la clase VentanaConsultaGrupos.
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see VentanaConsultaGrupos
 */

public class ControllerVentanaConsultaGrupos implements ActionListener{

	//Atributos de la clase
	private VentanaConsultaGrupos ventanaConsultaGrupos;
	
	//Constructor
	public ControllerVentanaConsultaGrupos(){
		
		ventanaConsultaGrupos=new VentanaConsultaGrupos();
		ventanaConsultaGrupos.setLocationRelativeTo(null);
		ventanaConsultaGrupos.setVisible(true);
		ventanaConsultaGrupos.agregarListener(this);

	}
	
	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Menu")){
			
			ventanaConsultaGrupos.dispose(); //Se cierra la clase VentanaConsultaGrupos
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
		}
		
	}

}
