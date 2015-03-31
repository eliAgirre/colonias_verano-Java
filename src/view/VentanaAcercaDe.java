package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Contiene la información acerca de la aplicación y botón para poder cerrar la VentanaAcercaDe.
 * 
 * @author EA
 * @since Mayo 13, 2014
 * @version 1.0.0
 */

@SuppressWarnings("serial")
public class VentanaAcercaDe extends JFrame {
	
	//Atributos de la clase
	private JButton btnCerrar;
	private JLabel lblColoniasDeVerano;
	
	/**
	 * 
     * Constructor sin parámetros pero contiene el método llamado componentes.
     * 
     */
	public VentanaAcercaDe(){
				
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setType(Type.UTILITY);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Acerca De");
		setBounds(100, 100, 565, 335);
		
		componentes();			
		
	} //Cierre del constructor
	
	/**
     * Contiene todos los componentes para aparecer en la VentanaAcercaDe como una JLabel para dar información y un JButton para cerrar la ventana.
     *  
     */
	public void componentes(){		

		lblColoniasDeVerano = new JLabel("<html><h2><font color=\"#DC143C\">Colonias de verano</font><hr size=\"3\"></h2>Desarrollada para la gestion de colonias de verano.<br> La aplicaci\u00F3n permite gestionar las matr\u00EDculas, los monitores, los juegos y el calendario para visualizar la actividad de cada d\u00EDa. <br><br>Version 1.0<br><br>Desarollada por:  Eli Agirre<br>Proyecto Final   - Programacion<br><br> Zubiri-Manteo<br>Copyright &copy; 2014</html> ");
		lblColoniasDeVerano.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
		lblColoniasDeVerano.setIcon(new ImageIcon(VentanaAcercaDe.class.getResource("/view/Img/mariquita.png")));
		lblColoniasDeVerano.setLabelFor(this);
		lblColoniasDeVerano.setForeground(new Color(205, 92, 92));
		lblColoniasDeVerano.setBounds(53, 11, 463, 245);
		getContentPane().add(lblColoniasDeVerano);      
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBackground(new Color(255, 204, 204));
		btnCerrar.setBounds(285, 267, 89, 23);
		getContentPane().add(btnCerrar);
		
	} //Cierre de método componentes
	
	/**
     * Sirve para agregar un actionListener asociado al botón llamado "Cerrar".
     * 
     * @param accion Es un ActionListener para que tenga una escucha el botón "Cerrar".
     *  
     */
	public void agregarListener(ActionListener accion){
		
		btnCerrar.addActionListener(accion);
		
	} //Cierre de método agregarListener
	
} //Cierre de la clase
