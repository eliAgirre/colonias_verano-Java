package controller;

import java.awt.event.*;

import view.VentanaAcercaDe;
import controller.ControllerVentanaPrincipal;

/**
 * Controla la clase VentanaAcercaDe.
 * 
 * @author EA
 * @since Mayo 13, 2014
 * @version 1.0.0
 * @see VentanaAcercaDe
 */

public class ControllerVentanaAcercaDe implements ActionListener{

	//Atributos de la clase
	private VentanaAcercaDe ventanaAcercade;
	
	//Constructor
	public ControllerVentanaAcercaDe(){
		
		ventanaAcercade=new VentanaAcercaDe();
		ventanaAcercade.setLocationRelativeTo(null);
		ventanaAcercade.setVisible(true);
		ventanaAcercade.agregarListener(this);

	}
	
	//Dar respuesta al ActionListener
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Cerrar")){
			
			ventanaAcercade.dispose(); //Se cierra la clase VentanaAcercaDe
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal ControllerVentanaPrincipal=new ControllerVentanaPrincipal();
		}
		
	}
	

}
