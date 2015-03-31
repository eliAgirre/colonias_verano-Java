package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VentanaConsultarCalendario;

/**
 * Controla la clase VentanaConsultarCalendario.
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see VentanaConsultarCalendario
 */

public class ControllerVentanaConsultarCalendario implements ActionListener{

	//Atributos de la clase
	private VentanaConsultarCalendario VentanaConsultarCalendario;
	
	//Constructor
	public ControllerVentanaConsultarCalendario(String grupo){
		
		VentanaConsultarCalendario=new VentanaConsultarCalendario(grupo);
		VentanaConsultarCalendario.setLocationRelativeTo(null);
		VentanaConsultarCalendario.setVisible(true);
		VentanaConsultarCalendario.agregarListener(this);

	}

	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Menu")){
			
			VentanaConsultarCalendario.dispose(); //Se cierra la clase VentanaConsultarCalendario
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
		}
		
	}
}
