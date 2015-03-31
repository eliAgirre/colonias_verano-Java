package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

import controller.ControllerVentanaPrincipal;
import manager.GestorJuegos;
import manager.GestorCalendario;
import manager.ListaVaciaExcepcion;

/**
 * 
 * 
 * @author EA
 * @since Mayo 16, 2014
 * @version 1.0.0
 * @see	VentanaPrincipal
 */

@SuppressWarnings("serial")
public class VentanaEditarJuegos extends JFrame implements ActionListener{

	//Atributos de la clase
	private JPanel contentPane;
	private static JTextField textNombre;
	private JComboBox<Object> cmbBoxTipo;
	private String[] listaTipo = {"Trabajo Manual","Al aire libre","En clase"}; //Lista de array para combobox
	private String[] listaGrupo = {"A","B"}; //Lista de array para combobox
	private JButton btnModificar;
	private int id;
	private String nombre;
	private String tipo;
	private String objetivo;
	private String descripcion;
	private String grupo;
	private JButton btnEliminar;
	private JLabel lblObjetivo;
	private JTextField textId;
	private static JTextArea areaObjetivo;
	private static JTextArea areaDescripcion;
	private JComboBox<Object> cmbBoxGrupo;
	private GestorJuegos gestorJuegos=new GestorJuegos();
	private GestorCalendario gestorCal=new GestorCalendario();
	private String group;
	private JButton btnCerrar;


	//Constructor
	public VentanaEditarJuegos(int id) {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 534, 389);
		setResizable(false);
		setTitle("Editar juegos");
		setIconImage(new ImageIcon(getClass().getResource("Img/editarJuegos.png")).getImage()); //logo de la ventana
		try {
			
			componentes();

			gestorJuegos.cargarArraylist();			
			
			int posicion=gestorJuegos.buscar(id);
			
			if(posicion!=-1){
				
				for(int i=0;i<gestorJuegos.obtenerLongitud();i++){
					
					textId.setText(String.valueOf(gestorJuegos.obtenerJuego(posicion).getId()));
					textNombre.setText(String.valueOf(gestorJuegos.obtenerJuego(posicion).getNombre()));
					cmbBoxTipo.setSelectedItem(gestorJuegos.obtenerJuego(posicion).getTipo());
					areaObjetivo.setText(String.valueOf(gestorJuegos.obtenerJuego(posicion).getObjetivo()));
					areaDescripcion.setText(String.valueOf(gestorJuegos.obtenerJuego(posicion).getDescripcion()));
					cmbBoxGrupo.setSelectedItem(gestorJuegos.obtenerJuego(posicion).getGrupo());

				}
			}
			
			nombre=textNombre.getText().toUpperCase();
			objetivo=areaObjetivo.getText();
			descripcion=areaDescripcion.getText();
			grupo=cmbBoxGrupo.getSelectedItem().toString();
			group=cmbBoxGrupo.getSelectedItem().toString();
			
			for(int i=0;i<gestorCal.obtenerLongitud();i++){
				
				System.out.println(gestorCal.obtenerCalendario(i).getIdJuego());
				
			}
			
		} catch (SQLException | ListaVaciaExcepcion e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	//Metodo componentes
	public void componentes(){
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblIntroduzcaLosDatos = new JLabel("Modifique o elimine los datos, por favor.");
		lblIntroduzcaLosDatos.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduzcaLosDatos.setBounds(26, 30, 246, 14);
		getContentPane().add(lblIntroduzcaLosDatos);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setForeground(new Color(205, 92, 92));
		lblId.setBounds(26, 73, 72, 14);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setEnabled(false);
		textId.setColumns(10);
		textId.setBounds(108, 70, 180, 20);
		contentPane.add(textId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(205, 92, 92));
		lblNombre.setBounds(26, 101, 72, 14);
		getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(108, 98, 364, 20);
		getContentPane().add(textNombre);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(new Color(205, 92, 92));
		lblTipo.setBounds(27, 129, 46, 14);
		getContentPane().add(lblTipo);
		
		cmbBoxTipo = new JComboBox <Object>(listaTipo);
		cmbBoxTipo.setEditable(false);
		cmbBoxTipo.setBounds(107, 129, 181, 20);
		contentPane.add(cmbBoxTipo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(255, 204, 204));
		btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/modificar.png"))); //Imagen de modificar
		btnModificar.addActionListener((ActionListener)this);
		btnModificar.setBounds(199, 312, 120, 28);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(255, 204, 204));
		btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/eliminar.png"))); //Imagen de eliminar
		btnEliminar.addActionListener((ActionListener)this);
		btnEliminar.setBounds(352, 312, 120, 28);
		getContentPane().add(btnEliminar);
		
		lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setForeground(new Color(205, 92, 92));
		lblObjetivo.setBounds(27, 165, 71, 14);
		contentPane.add(lblObjetivo);
		
		areaObjetivo = new JTextArea();
		areaObjetivo.setBounds(107, 160, 365, 28);
		contentPane.add(areaObjetivo);
		
		JLabel lblDescipcion = new JLabel("Descipci\u00F3n:");
		lblDescipcion.setForeground(new Color(205, 92, 92));
		lblDescipcion.setBounds(26, 204, 77, 14);
		contentPane.add(lblDescipcion);
		
		areaDescripcion = new JTextArea();
		areaDescripcion.setBounds(108, 218, 364, 35);
		contentPane.add(areaDescripcion);
		
		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setForeground(new Color(205, 92, 92));
		lblGrupo.setBounds(27, 267, 46, 14);
		contentPane.add(lblGrupo);
		
		cmbBoxGrupo = new JComboBox<Object>(listaGrupo);
		cmbBoxGrupo.setBounds(107, 264, 181, 20);
		contentPane.add(cmbBoxGrupo);
		
		btnCerrar = new JButton("Volver");
		btnCerrar.setIcon(new ImageIcon(VentanaEditarJuegos.class.getResource("/view/Img/volver.png"))); //Imagen de volver al menu
		btnCerrar.setBackground(new Color(255, 204, 204));
		btnCerrar.setBounds(57, 312, 106, 28);
		contentPane.add(btnCerrar);

	}

    //Metodo para saber si ha metido solo letras
	private static boolean esCaracter(String palabra){
		
		for(int i = 0; i < palabra.length(); i++){
			if((palabra.charAt(i)>=0 && palabra.charAt(i)<32) || (palabra.charAt(i)>32 && palabra.charAt(i)<44) || (palabra.charAt(i)>44 && palabra.charAt(i)<46) || (palabra.charAt(i)>46 && palabra.charAt(i)<65) || (palabra.charAt(i)>90 && palabra.charAt(i)<97) || (palabra.charAt(i)>122 && palabra.charAt(i)<=127))
				return false;
		}
		return true;	
	}
	
	//Metodo para saber si ha es mayúsculas letras o no
	private static boolean mayusculas(String palabra){
		
		for(int i = 0; i < palabra.length(); i++){
			if((palabra.charAt(i)>=0 && palabra.charAt(i)<32) || (palabra.charAt(i)>32 && palabra.charAt(i)<65) || (palabra.charAt(i)>90 && palabra.charAt(i)<=127))
				return false;
		}
		return true;	
	}
	
	//Método para saber si todos los campos estan rellenados
	private static boolean validarCampos(String nombre,String objetivo,String descripcion){
		
		boolean correcto=true;		
		
		if(nombre.equals("")){
			
			JOptionPane.showMessageDialog(textNombre, "Introduce el nombre del juego.","Campo vacío", JOptionPane.WARNING_MESSAGE);
			correcto=false;
		}
		if(objetivo.equals("")){
			JOptionPane.showMessageDialog(areaObjetivo, "Introduce el objetivo.","Campo vacío", JOptionPane.WARNING_MESSAGE);
			correcto=false;
		}
		if(descripcion.equals("")){
			JOptionPane.showMessageDialog(areaDescripcion, "Introduce la descripción.","Campo vacío", JOptionPane.WARNING_MESSAGE);
			correcto=false;
		}

		return correcto;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		if(evento.getSource()==btnModificar){ //Al realizar el evento en el sobre el botón Modificar
			
			boolean validar=validarCampos(textNombre.getText(),areaObjetivo.getText(),areaDescripcion.getText());			

			if(validar==false){
				
				textNombre.setText(nombre);
				areaObjetivo.setText(objetivo);
				areaDescripcion.setText(descripcion);
			}
			else{
				
				if((mayusculas(textNombre.getText().toUpperCase()))==false || textNombre.getText().length()>25){
					
					JOptionPane.showMessageDialog(textNombre, "Introduce letras en el nombre","Error", JOptionPane.WARNING_MESSAGE);
					textNombre.setText(nombre);
					
				}
				else{
					
					if((esCaracter(areaObjetivo.getText())==false) || areaObjetivo.getText().length()>45){
						JOptionPane.showMessageDialog(areaObjetivo, "Introduce letras en el objetivo.","Error", JOptionPane.WARNING_MESSAGE);
						areaObjetivo.setText(objetivo);
					}
					else{
						
						if((esCaracter(areaDescripcion.getText())==false) || areaDescripcion.getText().length()>60){
							
							JOptionPane.showMessageDialog(areaDescripcion, "Introduce letras en la descripción.","Error", JOptionPane.WARNING_MESSAGE);
							areaDescripcion.setText(descripcion);
						}
						else{
							
							try {
				
								id=Integer.parseInt(textId.getText());
								nombre=textNombre.getText().toUpperCase().trim();
								tipo=cmbBoxTipo.getSelectedItem().toString().trim();
								objetivo=areaObjetivo.getText().trim();
								descripcion=areaDescripcion.getText().trim();
								
								gestorCal.cargarArraylist(grupo);		
								
									
								if(cmbBoxGrupo.getSelectedItem().toString().equals(group)){

									grupo=grupo.toLowerCase().toString();
									int posicion=gestorCal.buscar(textId.getText());

									if(posicion!=-1){ //Si el juego existe en el calendario se modifica en el arraylist y en la base de datos
										
										gestorCal.modificarDatos(textId.getText(), textNombre.getText().toUpperCase().trim(), areaObjetivo.getText().trim(), areaDescripcion.getText().trim(), grupo.toUpperCase());
										
										gestorCal.eliminarDatosBD(grupo.toLowerCase());
										
										gestorCal.insertarBd();

										//Se crea una linea en el log para saber que ha sido modificado
										gestorCal.calendarioLog("Editar", String.valueOf(gestorCal.obtenerCalendario(posicion).getDia()),
												String.valueOf(gestorCal.obtenerCalendario(posicion).getNombreResponsable()), 
												gestorCal.obtenerCalendario(posicion).getApellidoResponsable(),
												String.valueOf(gestorCal.obtenerCalendario(posicion).getGrupo()),
												String.valueOf(gestorCal.obtenerCalendario(posicion).getIdJuego()),
												String.valueOf(gestorCal.obtenerCalendario(posicion).getNombreJuego()),
												String.valueOf(gestorCal.obtenerCalendario(posicion).getDescripcion()));
									}
									else{
											VentanaTablaJuegos.calendarioLog("Editar","","","",grupo.toUpperCase(),textId.getText(),nombre,descripcion);
									}
									
								}
								else { //Si se ha modificado el grupo
									
									//Se actualiza la fila de la tabla adecuada
									gestorCal.actualizarDatosBD(grupo, textId.getText());
									
									int posicion=gestorCal.buscar(textId.getText());
									
									//Se crea una linea en el log para saber que ha sido modificado
									gestorCal.calendarioLog("Editar", String.valueOf(gestorCal.obtenerCalendario(posicion).getDia()),
											String.valueOf(gestorCal.obtenerCalendario(posicion).getNombreResponsable()), 
											gestorCal.obtenerCalendario(posicion).getApellidoResponsable(),
											String.valueOf(cmbBoxGrupo.getSelectedItem().toString()),
											String.valueOf(gestorCal.obtenerCalendario(posicion).getIdJuego()),
											String.valueOf(gestorCal.obtenerCalendario(posicion).getNombreJuego()),
											String.valueOf(gestorCal.obtenerCalendario(posicion).getDescripcion()));
								}
					
								gestorJuegos.modificarDatos(id, nombre, tipo, objetivo, descripcion, cmbBoxGrupo.getSelectedItem().toString()); //Modifica los datos obtenidos de las variables y los modifica en el arraylist. Ademas borra todos los registros de la tabla juegos.
								
								gestorJuegos.insertarBd();
								
								JOptionPane.showMessageDialog(null, "Los datos han sido modificados correctamente.");
								
								this.dispose();
								@SuppressWarnings("unused")
								ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
								
							} catch (SQLException | ListaVaciaExcepcion e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}
					}
				}
			}
			
		} //Cierre del evento del botón Modificar
		
		if(evento.getSource()==btnEliminar){ //Al realizar el evento en el sobre el botón Eliminar

			try {
				
				id=Integer.parseInt(textId.getText()); 
				
				if(cmbBoxGrupo.getSelectedItem().toString().equals(group)){ //Si no se ha modificado el grupo
					
					gestorCal.cargarArraylist(grupo.toLowerCase());
					
					int posicion=gestorCal.buscar(textId.getText());
					
					if(posicion!=-1){ //Si el juego existe en el calendario se modifica en el arraylist
						
						gestorCal.eliminarDatos(textId.getText(),"","","","");
						
						gestorCal.actualizarDatosBD(grupo, textId.getText());
						
						//Se crea una linea en el log para saber que ha sido modificado
						gestorCal.calendarioLog("Borrar", String.valueOf(gestorCal.obtenerCalendario(posicion).getDia()),
								String.valueOf(gestorCal.obtenerCalendario(posicion).getNombreResponsable()), 
								gestorCal.obtenerCalendario(posicion).getApellidoResponsable(),
								String.valueOf(cmbBoxGrupo.getSelectedItem().toString()),
								String.valueOf(gestorCal.obtenerCalendario(posicion).getIdJuego()),
								String.valueOf(gestorCal.obtenerCalendario(posicion).getNombreJuego()),
								String.valueOf(gestorCal.obtenerCalendario(posicion).getDescripcion()));

					}
					else{
						VentanaTablaJuegos.calendarioLog("Borrar","","","",grupo.toUpperCase(),textId.getText(),"                     "," ");
					}
				}
				
				gestorJuegos.eliminar(id); //Elimina un único registro en el arraylist y en la tabla juegos.
	
				JOptionPane.showMessageDialog(null, "Los datos han sido eliminados correctamente.");
				
				this.dispose();
				@SuppressWarnings("unused")
				ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal

			} catch (SQLException | ListaVaciaExcepcion e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		} //Cierre del evento del botón Eliminar
		
	} //Cierre del método actionPerformed
	
	/**
     * Sirve para agregar un actionListener asociado al botón llamado "Volver".
     * 
     * @param accion Es un ActionListener para que tenga una escucha el botón "Volver".
     *  
     */
	public void agregarListener(ActionListener accion){
		
		btnCerrar.addActionListener(accion);
		
	} //Cierre de método agregarListener
} //Cierre de la clase