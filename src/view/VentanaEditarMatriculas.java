package view;

import java.awt.Color;
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
 * @since Mayo 14, 2014
 * @version 1.0.0
 * @see	VentanaPrincipal
 */

@SuppressWarnings("serial")
public class VentanaEditarMatriculas extends JFrame implements ActionListener{

	//Atributos de la clase
	private JPanel contentPane;
	private JTextField textDni;
	private static JTextField textNombre;
	private static JTextField textApellido1;
	private static JTextField textApellido2;
	private static JTextField textTelefono;
	private JComboBox<Object> cmbBoxEdad;
	private JComboBox<Object> cmbBoxGrupo;
	private int edadInt;
	private String grupoS;
	private String cadena;
	private static ResultSet rs;
	private static Statement stmt;
	private String[] listaEdad = {"3","4","5","6","7","8"}; //Lista de array para combobox
	private String[] listaGrupo = {"A","B"}; //Lista de array para combobox
	private JButton btnModificar;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private JButton btnEliminar;
	private JButton btnCerrar;
	private String telf;

	//Constructor
	public VentanaEditarMatriculas(String dni) {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 514, 330);
		setResizable(false);
		setTitle("Editar matrículas");
		setIconImage(new ImageIcon(getClass().getResource("Img/editarMatricula.png")).getImage()); //logo de la ventana
		
		try {
			componentes();
			
			GestorBD.conexion();
			
			stmt=GestorBD.conexion();
			
			cadena = "SELECT * FROM matriculas WHERE dni='"+dni+"';";
			rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
			
			while (rs.next()) {
				
				textDni.setText(rs.getString("dni"));
				textNombre.setText(rs.getString("nombre"));
				textApellido1.setText(rs.getString("apellido1"));
				textApellido2.setText(rs.getString("apellido2"));
				cmbBoxEdad.setSelectedItem(String.valueOf(rs.getInt("edad")));
				textTelefono.setText(rs.getString("telefono"));
				cmbBoxGrupo.setSelectedItem(rs.getString("grupo"));
				
			}
			rs.close(); //Cierra la consulta
			
			nombre=textNombre.getText().toUpperCase().trim();
			apellido1=textApellido1.getText().toUpperCase().trim();
			apellido2=textApellido2.getText().toUpperCase().trim();
			telf=textTelefono.getText().toUpperCase().trim();
			
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
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setForeground(new Color(205, 92, 92));
		lblEdad.setBounds(254, 76, 46, 14);
		getContentPane().add(lblEdad);
		
		cmbBoxEdad = new JComboBox<Object>(listaEdad);
		cmbBoxEdad.addActionListener((ActionListener)this);
		cmbBoxEdad.setBounds(322, 76, 86, 20);
		contentPane.add(cmbBoxEdad);
		
		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setForeground(new Color(205, 92, 92));
		lblGrupo.setBounds(254, 159, 46, 14);
		getContentPane().add(lblGrupo);
		
		cmbBoxGrupo = new JComboBox <Object>(listaGrupo);
		cmbBoxGrupo.setEnabled(false);
		cmbBoxGrupo.setBounds(322, 156, 86, 20);
		contentPane.add(cmbBoxGrupo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(255, 204, 204));
		btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/modificar.png"))); //Imagen de modificar
		btnModificar.addActionListener((ActionListener)this);
		btnModificar.setBounds(180, 239,  120, 28);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(255, 204, 204));
		btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/eliminar.png"))); //Imagen de eliminar
		btnEliminar.addActionListener((ActionListener)this);
		btnEliminar.setBounds(320, 239, 120, 28);
		getContentPane().add(btnEliminar);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setForeground(new Color(205, 92, 92));
		lblTelfono.setBounds(254, 114, 65, 14);
		contentPane.add(lblTelfono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(322, 111, 86, 20);
		contentPane.add(textTelefono);
		
		btnCerrar = new JButton("Volver");
		btnCerrar.setIcon(new ImageIcon(VentanaEditarMatriculas.class.getResource("/view/Img/volver.png"))); //Imagen de volver al menu
		btnCerrar.setBackground(new Color(255, 204, 204));
		btnCerrar.setBounds(37, 239, 106, 28);
		contentPane.add(btnCerrar);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getSource()==cmbBoxEdad){ //Al realizar el evento en el JComboBox de la edad
			
			String edadS=cmbBoxEdad.getSelectedItem().toString(); //Guardar un item del combobox la variable de clase de tipo String
			edadInt=Integer.parseInt(edadS); //Se converte el String al entero
			
			if(cmbBoxEdad.isValid()==true){ //si es válido JComboBox
				
				cmbBoxGrupo.setEnabled(false); //Desactivamos el JComboBox 
				
				try {
					
				
					//Cuando haya seleccionado la edad el combobox del grupo se activa. Y se hace la consulta.
					cadena = "SELECT * FROM grupo WHERE id="+edadS+";"; //la sentencia sql se guarda en un String
					
					rs = GestorBD.consulta(stmt,cadena); //Realiza la consulta
					 
					while (rs.next()) { //Mientras que haya datos
					
						
					}
					rs.close(); //Cierre de la consulta
				
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

				cmbBoxEdad.setEnabled(false); //Desactivamos el JComboBox 
				if(edadInt==3 || edadInt==4 || edadInt==5){
					
					for(int i=0;i<listaGrupo.length;i++){

						cmbBoxGrupo.setSelectedItem(listaGrupo[0].toString());

					}
					grupoS=cmbBoxGrupo.getSelectedItem().toString(); //Guardar un item del combobox la variable de clase de tipo String
				}
				
				if(edadInt==6 || edadInt==7 || edadInt==8){
					
					for(int i=0;i<listaGrupo.length;i++){

						cmbBoxGrupo.setSelectedItem(listaGrupo[1].toString());

					}
					grupoS=cmbBoxGrupo.getSelectedItem().toString(); //Guardar un item del combobox la variable de clase de tipo String
				}

			}
		}
		
		if(evento.getSource()==btnModificar){ //Al realizar el evento en el sobre el botón Modificar, se modificaran los datos en la tabla matriculas.
				
			boolean validar=VentanaAnadirMatriculas.validarCampos(textNombre.getText(),textApellido1.getText(),textApellido2.getText(),textTelefono.getText());			

			if(validar==false){
				
				textNombre.setText(nombre);
				textApellido1.setText(apellido1);
				textApellido2.setText(apellido2);
				textTelefono.setText(telf);
			}
			else{

				if((VentanaAnadirMatriculas.esNumerico(textNombre.getText()))||VentanaAnadirMatriculas.esCaracter(textNombre.getText().toUpperCase())==false ||textNombre.getText().length()>15){
					
					JOptionPane.showMessageDialog(textNombre, "Introduce letras en el nombre ","Error", JOptionPane.WARNING_MESSAGE);
					textNombre.setText("");
					
				}
				else{
					
					if((VentanaAnadirMatriculas.esNumerico(textApellido1.getText())||VentanaAnadirMatriculas.esCaracter(textApellido1.getText().toUpperCase())==false) ||textApellido1.getText().length()>15){
						JOptionPane.showMessageDialog(textApellido1, "Introduce letras en el 1º apellido","Error", JOptionPane.WARNING_MESSAGE);
						textApellido1.setText("");
					}
					else{
						
						if((VentanaAnadirMatriculas.esNumerico(textApellido2.getText())||VentanaAnadirMatriculas.esCaracter(textApellido2.getText().toUpperCase())==false) ||textApellido2.getText().length()>15){
							
							JOptionPane.showMessageDialog(textApellido2, "Introduce letras en el 2º apellido","Error", JOptionPane.WARNING_MESSAGE);
							textApellido2.setText("");
						}
						else{

							try{
								
								dni=textDni.getText().trim();
								nombre=textNombre.getText().toUpperCase().trim();
								apellido1=textApellido1.getText().toUpperCase().trim();
								apellido2=textApellido2.getText().toUpperCase().trim();
								
								if(textTelefono.getText().length()!=9){
									JOptionPane.showMessageDialog(textTelefono, "Introduce 9 números en el teléfono.","Error", JOptionPane.WARNING_MESSAGE);
									textTelefono.setText("");
								}
								else{
									
									telefono=Integer.parseInt(textTelefono.getText().trim());
									grupoS=cmbBoxGrupo.getSelectedItem().toString();
									
									cadena = "UPDATE matriculas SET dni='"+dni+"',nombre = '"+nombre+"',apellido1 = '"+apellido1+"',apellido2 = '"+apellido2+"',edad = "+edadInt+",telefono = '"+telefono+"',grupo = '"+grupoS+"' WHERE dni='"+dni+"'";
									
									GestorBD.consultaActualiza(cadena);
									
									cadena = "UPDATE grupos SET dni='"+dni+"',nombre = '"+nombre+"',apellido1 = '"+apellido1+"',apellido2 = '"+apellido2+"',grupo = '"+grupoS+"' WHERE dni='"+dni+"';";
									
									GestorBD.consultaActualiza(cadena);
									
									VentanaAnadirMatriculas.gruposLog("Editar",dni,cmbBoxEdad.getSelectedItem().toString(),textTelefono.getText().trim(),grupoS,"NO",nombre,apellido1,apellido2);
									
									JOptionPane.showMessageDialog(null, "Los datos han sido modificados correctamente.");
									
									this.dispose();
									@SuppressWarnings("unused")
									ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
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
			
			dni=textDni.getText();
			
			cadena="DELETE FROM matriculas WHERE dni=?;"; //Se borra la fila de la tabla matriculas, porque esa persona ya no existe en la matricula.
			GestorBD.eliminarRegistro(cadena,dni); //Ejecuta la consulta 
			
			cadena="DELETE FROM grupos WHERE dni=?;"; //Se borra la fila de la tabla grupos.
			GestorBD.eliminarRegistro(cadena,dni); //Ejecuta la consulta 
			
			VentanaAnadirMatriculas.gruposLog("Borrar", dni, "", "", "", "", "", "", "");
			
			JOptionPane.showMessageDialog(null, "Los datos han sido eliminados correctamente.");
			
			this.dispose();
			@SuppressWarnings("unused")
			ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal

		} //Cierre del evento del botón Eliminar
		
	} //Cierre del método actionPerformed
	
	/**
     * Sirve para agregar un actionListener asociado al botón llamado btnCerrar.
     * 
     * @param accion Es un ActionListener para que tenga una escucha el botón "btnCerrar.
     *  
     */
	public void agregarListener(ActionListener accion){
		
		btnCerrar.addActionListener(accion);
		
	} //Cierre de método agregarListener
	
} //Cierre de la clase