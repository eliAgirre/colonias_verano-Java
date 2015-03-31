package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

import controller.ControllerVentanaPrincipal;
import manager.GestorBD;

/**
 * 
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see	VentanaPrincipal
 * @see GestorBD
 */

@SuppressWarnings("serial")
public class VentanaEditarMonitores extends JFrame implements ActionListener{

	//Atributos de la clase
	private JPanel contentPane;
	private JTextField textDni;
	private static JTextField textNombre;
	private static JTextField textApellido1;
	private static JTextField textApellido2;
	private static JTextField textTelefono;
	private JComboBox<Object> cmbBoxGrupo;
	private String grupoS;
	private String[] listaGrupo = {"A","B"}; //Lista de array para combobox
	private JButton btnModificar;
	private String dni;
	private JButton btnEliminar;
	private JLabel lblEmail;
	private JTextField textEmail;
	private String email;
	private static Statement stmt;
	private String cadena;
	private ResultSet rs;
	private String group;
	private JButton btnCerrar;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String telefono;

	//Constructor
	public VentanaEditarMonitores(String dni) {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 560, 330);
		setResizable(false);
		setTitle("Editar monitores");
		setIconImage(new ImageIcon(getClass().getResource("Img/editarMonitores.png")).getImage()); //logo de la ventana

		try {
		
			componentes();
			
			GestorBD.conexion();
			
			stmt=GestorBD.conexion();
			
			cadena = "SELECT * FROM monitores WHERE dni='"+dni+"';";
			rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
			
			while (rs.next()) {
				
				textDni.setText(rs.getString("dni"));
				textNombre.setText(rs.getString("nombre"));
				textApellido1.setText(rs.getString("apellido1"));
				textApellido2.setText(rs.getString("apellido2"));
				textTelefono.setText(rs.getString("telefono"));
				textEmail.setText(rs.getString("email"));
				cmbBoxGrupo.setSelectedItem(rs.getString("grupo"));
				
			}
			rs.close(); //Cierra la consulta
			
			grupoS=cmbBoxGrupo.getSelectedItem().toString();
			group=cmbBoxGrupo.getSelectedItem().toString();
			nombre=textNombre.getText().toUpperCase().trim();
			apellido1=textApellido1.getText().toUpperCase().trim();
			apellido2=textApellido2.getText().toUpperCase().trim();
			telefono=textTelefono.getText().toUpperCase().trim();
			
		} catch (SQLException e) {
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
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(new Color(205, 92, 92));
		lblDni.setBounds(24, 82, 46, 14);
		getContentPane().add(lblDni);
		
		textDni = new JTextField();
		textDni.setEnabled(false);
		textDni.setBounds(106, 79, 111, 20);
		getContentPane().add(textDni);
		textDni.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(205, 92, 92));
		lblNombre.setBounds(24, 120, 72, 14);
		getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(106, 117, 111, 20);
		getContentPane().add(textNombre);
		
		JLabel lblPrimerApellido = new JLabel("1\u00BA Apellido:");
		lblPrimerApellido.setForeground(new Color(205, 92, 92));
		lblPrimerApellido.setBounds(24, 162, 73, 14);
		getContentPane().add(lblPrimerApellido);
		
		textApellido1 = new JTextField();
		textApellido1.setColumns(10);
		textApellido1.setBounds(106, 159, 111, 20);
		getContentPane().add(textApellido1);
		
		JLabel lblSegundoApellido = new JLabel("2\u00BA apellido:");
		lblSegundoApellido.setForeground(new Color(205, 92, 92));
		lblSegundoApellido.setBounds(24, 198, 86, 14);
		getContentPane().add(lblSegundoApellido);
		
		textApellido2 = new JTextField();
		textApellido2.setColumns(10);
		textApellido2.setBounds(106, 195, 111, 20);
		getContentPane().add(textApellido2);
		
		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setForeground(new Color(205, 92, 92));
		lblGrupo.setBounds(254, 159, 46, 14);
		getContentPane().add(lblGrupo);
		
		cmbBoxGrupo = new JComboBox <Object>(listaGrupo);
		cmbBoxGrupo.setBounds(322, 156, 86, 20);
		contentPane.add(cmbBoxGrupo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(255, 204, 204));
		btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/modificar.png"))); //Imagen de modificar
		btnModificar.addActionListener((ActionListener)this);
		btnModificar.setBounds(97, 239, 120, 28);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(255, 204, 204));
		btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/eliminar.png"))); //Imagen de eliminar
		btnEliminar.addActionListener((ActionListener)this);
		btnEliminar.setBounds(254, 239, 120, 28);
		getContentPane().add(btnEliminar);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setForeground(new Color(205, 92, 92));
		lblTelfono.setBounds(254, 85, 65, 14);
		contentPane.add(lblTelfono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(322, 82, 189, 20);
		contentPane.add(textTelefono);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(205, 92, 92));
		lblEmail.setBounds(254, 123, 63, 14);
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(322, 120, 189, 20);
		contentPane.add(textEmail);
		
		btnCerrar = new JButton("Menu");
		btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/home.png"))); //Imagen de volver al menu
		btnCerrar.setBackground(new Color(255, 204, 204));
		btnCerrar.setBounds(409, 234, 106, 33);
		contentPane.add(btnCerrar);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		
		
		if(evento.getSource()==btnModificar){ //Al realizar el evento en el sobre el botón Modificar
				
			
			boolean validar=VentanaAnadirMonitores.validarCampos(textNombre.getText(),textApellido1.getText(),textApellido2.getText(),textTelefono.getText(),textEmail.getText());			

			if(validar==false){
				
				textNombre.setText(nombre);
				textApellido1.setText(apellido1);
				textApellido2.setText(apellido2);
				textTelefono.setText(telefono);
				
			}
			else{

				if(VentanaAnadirMatriculas.esNumerico(textNombre.getText())  ||textNombre.getText().length()>15){
					
					JOptionPane.showMessageDialog(textNombre, "Introduce letras en el nombre.","Error", JOptionPane.WARNING_MESSAGE);
					textNombre.setText("");
					
				}
				else{
					
					if(VentanaAnadirMatriculas.esNumerico(textApellido1.getText())  ||textApellido1.getText().length()>15 ){
						
						JOptionPane.showMessageDialog(textApellido1, "Introduce letras en el 1º apellido","Error", JOptionPane.WARNING_MESSAGE);
						textApellido1.setText("");
					}
					else{
						
						if(VentanaAnadirMatriculas.esNumerico(textApellido2.getText())  ||textApellido2.getText().length()>15){
							
							JOptionPane.showMessageDialog(textApellido1, "Introduce letras en el 2º apellido","Error", JOptionPane.WARNING_MESSAGE);
							textApellido2.setText("");
						}
						else{
							
							try{

								nombre=textNombre.getText().toUpperCase().trim();
								apellido1=textApellido1.getText().toUpperCase().trim();
								apellido2=textApellido2.getText().toUpperCase().trim();
								
								if(textTelefono.getText().length()!=9){
									
									JOptionPane.showMessageDialog(null, "Introduce 9 numeros en el teléfono.","Error", JOptionPane.WARNING_MESSAGE);
									textTelefono.setText("");
								}
								else{
								
									int telefono=Integer.parseInt(textTelefono.getText().trim());
									
									if(VentanaAnadirMonitores.validarEmail(textEmail.getText())==true && textEmail.getText().length()<46){

										dni=textDni.getText().trim();
										email=textEmail.getText().trim();
										grupoS=cmbBoxGrupo.getSelectedItem().toString();
										
										cadena = "UPDATE monitores SET dni='"+dni+"',nombre = '"+nombre+"',apellido1 = '"+apellido1+"',apellido2 = '"+apellido2+"',telefono = '"+telefono+"',email = '"+email+"',grupo = '"+grupoS+"' WHERE dni='"+dni+"';";
										
										GestorBD.consultaActualiza(cadena);
										
										cadena = "UPDATE grupos SET dni='"+dni+"',nombre = '"+nombre+"',apellido1 = '"+apellido1+"',apellido2 = '"+apellido2+"',grupo = '"+grupoS+"' WHERE dni='"+dni+"';";
										
										GestorBD.consultaActualiza(cadena);
										
										if(grupoS.equals(group)){
											
											grupoS=grupoS.toLowerCase().toString();
											
											cadena = "UPDATE calendario_"+grupoS+" SET dniMonitor='"+dni+"',nombreResponsable = '"+nombre+"',apellidoResponsable = '"+apellido1+"',grupo = '"+grupoS.toUpperCase().toString()+"' WHERE dniMonitor='"+dni+"';";
											
											GestorBD.consultaActualiza(cadena);
											
										}
										
										VentanaAnadirMatriculas.gruposLog("Editar",dni,"-",textTelefono.getText().trim(),grupoS.toUpperCase().toString(),"SI",nombre,apellido1,apellido2);

										JOptionPane.showMessageDialog(null, "Los datos han sido modificados correctamente.");
										
										this.dispose();
										@SuppressWarnings("unused")
										ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
									}
									else{
										JOptionPane.showMessageDialog(null, "Introduce el email menor que 45 caracteres.","Error", JOptionPane.WARNING_MESSAGE);
									}
								}
	
							}catch (NumberFormatException nfe){
						    	
								JOptionPane.showMessageDialog(null, "Introduce numeros en el telefono.","Error", JOptionPane.WARNING_MESSAGE);
								textTelefono.setText("");
						    }
						}
					}
							
				}					
			}
		} //Cierre del evento del botón Modificar
		
		if(evento.getSource()==btnEliminar){ //Al realizar el evento en el sobre el botón Eliminar
			
			dni=textDni.getText().trim();
			grupoS=cmbBoxGrupo.getSelectedItem().toString();
			
			cadena="DELETE FROM monitores WHERE dni=?;"; //Se borra la fila de la tabla matriculas, porque esa persona ya no existe en la matricula.
			GestorBD.eliminarRegistro(cadena,dni); //Ejecuta la consulta 
			
			cadena="DELETE FROM grupos WHERE dni=?;"; //Se borra la fila de la tabla grupos.
			GestorBD.eliminarRegistro(cadena,dni); //Ejecuta la consulta 
			
			if(grupoS.equals(group)){
				
				grupoS=grupoS.toLowerCase().toString();
				
				cadena = "UPDATE calendario_"+grupoS+" SET dniMonitor='"+" "+"',nombreResponsable = '"+" "+"',apellidoResponsable = '"+" "+"',grupo = '"+" "+"' WHERE dniMonitor='"+dni+"';";
				
				GestorBD.consultaActualiza(cadena);

			}
			
			VentanaAnadirMatriculas.gruposLog("Borrar", dni, "", "", "", "","", "", "");
			
			JOptionPane.showMessageDialog(null, "Los datos han sido eliminados correctamente.");
			
			this.dispose();
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal

		} //Cierre del evento del botón Eliminar
		
	} //Cierre del método actionPerformed
	
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