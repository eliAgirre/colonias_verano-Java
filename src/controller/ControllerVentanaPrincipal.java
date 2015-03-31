package controller;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import view.VentanaAnadirMonitores;
import view.VentanaPrincipal;

/**
 * Controla la ventana principal, el menú bar y cada Controller de cada ventana asociado a ella.
 * 
 * @author EA
 * @since Mayo 13, 2014
 * @version 1.0.0
 * @see VentanaPrincipal
 * @see ControllerEntrance
 * @see ControllerVentanaAnadirMatricula
 * @see ControllerVentanaAnadirMonitor
 * @see ControllerVentanaTablaMatriculas
 * @see ControllerVentanaEditarMatriculas
 * @see ControllerVentanaEditarMonitores
 * @see ControllerVentanaTablaJuegos
 * @see ControllerVentanaEditarJuegos
 * @see ControllerVentanaConsultaGrupos
 * @see ControllerVentanaConsultarJuegos
 * @see ControllerVentanaConsultarCalendario
 * @see ControllerVentanaAcercaDe
 */

@SuppressWarnings("serial")
public class ControllerVentanaPrincipal  extends JFrame implements ActionListener {
	
	//Atributo de la clase
	private static VentanaPrincipal ventanaPrincipal; //Se declara la clase VentanaPrincipal
	private static String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

	/**
	 * 
     * Constructor con dos parámetros de tipo String e instancia la clase VentanaPrincipal.
     * 
     * @param usuario Define el nombre del usuario de tipo String.
     * @param tipo Define el tipo de usuario a niveles de tipo String.
     * 
     */
	public ControllerVentanaPrincipal(String usuario,String tipo) {
		
		try {
			
			ventanaPrincipal=new VentanaPrincipal(usuario,tipo);  //Se instancia la VentanaVer
			ventanaPrincipal.setLocationRelativeTo(null); //Localiza en el centro de la pantalla
			ventanaPrincipal.setVisible(true); //Hace visible la ventana
			ventanaPrincipal.agregarListener(this);  //Agrega el Listener para la clase
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	} //Cierre del constructor
	
	/**
	 * 
     * Constructor vacío para hacer visible la clase VentanaPrincipal.
     * 
     */
	public ControllerVentanaPrincipal() {
		
		ventanaPrincipal.setVisible(true);
	}
		
	/**
	 * Validar el formato correcto del DNI.
	 * 
     * @param dni Es un String obtenido desde el cuadro de diálogo para editar los datos de los monitores.
     * 
     */
	private static boolean validarDni(String dni){
		
		boolean correcto=true;
		
		try{			
			
			int numDni=Integer.parseInt((dni.substring(0,8)));
			String letra=dni.substring(8,9);
			
			String l=String.valueOf(letras.charAt(numDni % 23));
			if(!l.equals(letra)){
				JOptionPane.showMessageDialog(null, "El DNI no es correcto.","Error", JOptionPane.WARNING_MESSAGE);
				correcto=false;				
			}	
			
		}catch(NumberFormatException|StringIndexOutOfBoundsException ne){
			
			correcto=false;
		}
		return correcto;
	}

	/**
	 * 
     * Responde al ActionListener al producir un evento.
     * 
     * @param evento Es una acción de tipo ActionEvent para dar una respuesta a cada item de la barra de menú.
     * 
     */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent evento) {
		
		 try {
			 
			 if (evento.getActionCommand().equalsIgnoreCase("Matricula")){
				 
				 ControllerVentanaAnadirMatricula ControllerAMatriculas = new ControllerVentanaAnadirMatricula();
				 ventanaPrincipal.setVisible(false);
			 }
			 else if (evento.getActionCommand().equalsIgnoreCase("Monitor")){
				 
				 ControllerVentanaAnadirMonitor ControllerAMonitres = new ControllerVentanaAnadirMonitor();
				 ventanaPrincipal.setVisible(false);
			 }
			 else if (evento.getActionCommand().equalsIgnoreCase("Cerrar Sesion")){				 
				 
					int valor1=0; //Se define el valor del entero para recoger la respuesta de la confirmación de cerrar sesion
	
					valor1=JOptionPane.showConfirmDialog(null,"¿Seguro que desea cerrar sesion?","Cerrar sesion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE); //Se confirma si el usuario desea salir			
					
					if(valor1==0){ //Si la respuesta es "Si", saldrá de la aplicacion
						
						ventanaPrincipal.setVisible(false);
						ControllerEntrance ControllerEntrance=new ControllerEntrance();
	
					}
					else if(valor1==1){ //Si la respuesta es "No", aparecerá la ventanaPrincipal
						
						ventanaPrincipal.setVisible(true);
					}
					
	         }
			 else if (evento.getActionCommand().equalsIgnoreCase("Salir")){				 

					int valor1=0; //Se define el valor del entero para recoger la respuesta de la confirmación de la salida
	
					valor1=JOptionPane.showConfirmDialog(null,"¿Seguro que desea salir?","Salir", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE); //Se confirma si el usuario desea salir			
					
					if(valor1==0){ //Si la respuesta es "Si", saldrá de la aplicacion
						
						System.exit(0);
	
					}
					else if(valor1==1){ //Si la respuesta es "No", aparecerá la ventanaPrincipal
						
						ventanaPrincipal.setVisible(true);
					}
				 				 
	         }
			 else if (evento.getActionCommand().equalsIgnoreCase("Matriculas")){

				ControllerVentanaTablaMatriculas ControllerTablaMatriculas = new ControllerVentanaTablaMatriculas();
				ventanaPrincipal.setVisible(false);

			 }
			 else if (evento.getActionCommand().equalsIgnoreCase("Monitores")){

				 String dni=JOptionPane.showInputDialog("Introduce el DNI:");

					if(null==dni){ //Si el usuario pulsa "Cancelar" el cuadro de diálogo se cerrará.
						this.dispose();
					}
					else{ //Si el usuario pulsa "Aceptar", el usuario tendrá que introducir el DNI y se debe validar el DNI.
					
						try{
							boolean valido=false;
							
							while (dni.equals("")){
								
								dni=JOptionPane.showInputDialog("Introduce el DNI:");
								JOptionPane.showMessageDialog(null, "Introduce los 8 primeros números del DNI","Error", JOptionPane.WARNING_MESSAGE);
							}
							
							while(valido==false){
							
								dni=dni.toUpperCase();
								
								if(!(validarDni(dni))){
									dni=JOptionPane.showInputDialog("Introduce el DNI:");
								}
								else{
									
									if(VentanaAnadirMonitores.dniExiste(dni)){
										ControllerVentanaEditarMonitores ControllerEMonitores = new ControllerVentanaEditarMonitores(dni);
										ventanaPrincipal.setVisible(false);
										valido=true;
									}
									else{
										JOptionPane.showMessageDialog(null, "El DNI no existe.","Error", JOptionPane.WARNING_MESSAGE);
										dni=JOptionPane.showInputDialog("Introduce el DNI:");
									}
								}
								
							}
						}catch(NullPointerException en){
						}
					}
				 }
			 else if (evento.getActionCommand().equalsIgnoreCase("Juego")){
				 
				 ControllerVentanaTablaJuegos ControllerTablaJuegos = new ControllerVentanaTablaJuegos();
				 ventanaPrincipal.setVisible(false);

			 }
			 else if (evento.getActionCommand().equalsIgnoreCase("Grupos")){
				 
					ControllerVentanaConsultaGrupos ControllerListarGrupos = new ControllerVentanaConsultaGrupos();
					ventanaPrincipal.setVisible(false);
			 }
			 else if (evento.getActionCommand().equalsIgnoreCase("Juegos")){
				 
					ControllerVentanaConsultarJuegos ControllerListarJuegos = new ControllerVentanaConsultarJuegos();
					ventanaPrincipal.setVisible(false);
			 }
			 else if (evento.getActionCommand().equalsIgnoreCase("Calendario")){
				 				
					String grupo=JOptionPane.showInputDialog("Introduce el grupo (A o B):");

					if(null==grupo){ //Si el usuario pulsa "Cancelar" el cuadro de diálogo se cerrará.
						ventanaPrincipal.setVisible(true);
					}
					else{ //Si el usuario pulsa "Aceptar", el usuario tendrá que introducir el DNI y se debe validar el DNI.
					
						try{
							boolean valido=false;
							
							while (grupo.equals("")){
								
								JOptionPane.showMessageDialog(null, "Introduce el grupo A o B","Error", JOptionPane.WARNING_MESSAGE);
								grupo=JOptionPane.showInputDialog("Introduce el grupo (A o B):");

							}
							
							while(valido==false){
							
								grupo=grupo.toUpperCase();
								
								if(!(grupo.equals("A")||grupo.equals("B"))){
									JOptionPane.showMessageDialog(null, "Introduce el grupo A o B","Error", JOptionPane.WARNING_MESSAGE);
									grupo=JOptionPane.showInputDialog("Introduce el grupo (A o B):");
								}
								else{
									
									if(grupo.equals("A")||grupo.equals("B")){
										 ControllerVentanaConsultarCalendario ControllerECalendario = new ControllerVentanaConsultarCalendario(grupo);
										 ventanaPrincipal.setVisible(false);
											valido=true;
									}
								}
								
							}
							
						}catch(NullPointerException en){
						}
					}

			 }
			 else if (evento.getActionCommand().equalsIgnoreCase("Acerca De")){
				 
					ControllerVentanaAcercaDe ControllerAcercaDe = new ControllerVentanaAcercaDe();
					ventanaPrincipal.setVisible(false);

			 }
			 
		 } catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				
		} //Cierre de la Exception SQL
		 
	 } //Cierre del método actionPerformed
} //Cierre de la clase