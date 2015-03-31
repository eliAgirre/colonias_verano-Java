package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

import controller.ControllerVentanaPrincipal;
import manager.GestorBD;


/**
 * Descripcion
 * 
 * @author EA
 * @since Mayo 15, 2014
 * @version 1.0.0
 * @see	VentanaPrincipal
 */

@SuppressWarnings("serial")
public class VentanaAnadirMonitores extends JFrame implements KeyListener,ActionListener{

	//Atributos de la clase
	private JPanel contentPane;
	private static JTextField textDni;
	private static JTextField textNombre;
	private static JTextField textTelefono;
	private static JTextField textApellido1;
	private static JTextField textApellido2;
	private String[] listaGrupo = {"A","B"}; //Lista de array para combobox
	private JComboBox<Object> cmbBoxGrupo;
	private JButton btnAnadir;
	private static String dni;
	private static Statement stmt;
	private static ResultSet rs;
	private static JTextField textEmail;
	private String grupo;
	private String cadena;
	private JButton btnVolver;

	//Constructor
	public VentanaAnadirMonitores() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 561, 330);
		setResizable(false);
		setTitle("Añadir monitores");
		setIconImage(new ImageIcon(getClass().getResource("Img/anadirMonitores.png")).getImage()); //logo de la ventana

		try {
			componentes();		
		
			GestorBD.conectar();
		
			stmt=GestorBD.conexion();
			
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
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(new Color(205, 92, 92));
		lblDni.setBounds(24, 88, 46, 14);
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.addKeyListener(this);
		textDni.setColumns(10);
		textDni.setBounds(106, 85, 115, 20);
		contentPane.add(textDni);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(205, 92, 92));
		lblNombre.setBounds(24, 126, 72, 14);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();	
		textNombre.setEnabled(false);
		textNombre.addKeyListener(this);
		textNombre.setColumns(10);
		textNombre.setBounds(106, 123, 115, 20);
		contentPane.add(textNombre);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setForeground(new Color(205, 92, 92));
		lblTelefono.setBounds(258, 91, 63, 14);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setEnabled(false);
		textTelefono.setColumns(10);
		textTelefono.setBounds(326, 88, 193, 20);
		contentPane.add(textTelefono);
		
		JLabel lblApellido1 = new JLabel("1\u00BA Apellido:");
		lblApellido1.setForeground(new Color(205, 92, 92));
		lblApellido1.setBounds(24, 168, 73, 14);
		contentPane.add(lblApellido1);
		
		textApellido1 = new JTextField();
		textApellido1.setEnabled(false);
		textApellido1.setColumns(10);
		textApellido1.setBounds(106, 165, 115, 20);
		contentPane.add(textApellido1);
		
		JLabel lblApellido2 = new JLabel("2\u00BA apellido:");
		lblApellido2.setForeground(new Color(205, 92, 92));
		lblApellido2.setBounds(24, 204, 86, 14);
		contentPane.add(lblApellido2);
		
		textApellido2 = new JTextField();
		textApellido2.setEnabled(false);
		textApellido2.setColumns(10);
		textApellido2.setBounds(106, 201, 115, 20);
		contentPane.add(textApellido2);
		
		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setForeground(new Color(205, 92, 92));
		lblGrupo.setBounds(258, 165, 46, 14);
		contentPane.add(lblGrupo);
		
		cmbBoxGrupo = new JComboBox<Object>(listaGrupo);
		cmbBoxGrupo.addActionListener((ActionListener)this);
		cmbBoxGrupo.setEnabled(false);
		cmbBoxGrupo.setBounds(326, 162, 193, 20);
		contentPane.add(cmbBoxGrupo);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setBackground(new Color(255, 204, 204));
		btnAnadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/anadir.png"))); //Imagen de añadir un/a monitor/a
		btnAnadir.setEnabled(false);
		btnAnadir.addActionListener((ActionListener)this);
		btnAnadir.setBounds(308, 247, 106, 33);
		getContentPane().add(btnAnadir);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(205, 92, 92));
		lblEmail.setBounds(258, 129, 63, 14);
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setEnabled(false);
		textEmail.setColumns(10);
		textEmail.setBounds(326, 126, 193, 20);
		contentPane.add(textEmail);
		
		btnVolver = new JButton("Menu");
		btnVolver.setBackground(new Color(255, 204, 204));
		btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/home.png"))); //Imagen de volver al menu
		btnVolver.setBounds(115, 247, 106, 33);
		contentPane.add(btnVolver);
		
		JLabel label = new JLabel("Introduzca los datos, por favor.");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(24, 38, 178, 14);
		contentPane.add(label);
		
	}
	
	/**
     * Para comprobar si sólo existe ese único DNI en la tabla Vehiculo
     * @throws SQLException
     * @return boolean. Retorna un booleano para saber si la existe de el dni.
     * 
     */
	public static boolean dniExiste(String dni) throws SQLException{
		 
		 boolean encontrado=false;

		 String cadena = "SELECT * FROM monitores;";
		 rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
		 
		 while (rs.next()){
			 
			 if(rs.getString("dni").equals(dni)){
				 encontrado=true;
			 }
		 }
		 return encontrado;
		 
	 }
	
	//Método para validar el email
	public static boolean validarEmail(String email){
		
		boolean correcto=false;
		
		int posarroba = email.indexOf("@");
		int pospunto = email.indexOf(".");
		if (((posarroba != -1)&& (pospunto != - 1)) && (pospunto - posarroba > 1)){
			
			return correcto=true; 
		}
		JOptionPane.showMessageDialog(textEmail, "Introduce el email correcto.","Error", JOptionPane.WARNING_MESSAGE);
		textEmail.setText("");
		return correcto;
		
	}
	 
	//Método para saber si todos los campos estan rellenados
	public static boolean validarCampos(String nombre,String apellido1,String apellido2,String telefono,String email){
		
		boolean correcto=true;		
		
		if(nombre.equals("")){
			JOptionPane.showMessageDialog(textNombre, "Introduce el nombre.","Campo vacío", JOptionPane.WARNING_MESSAGE);
			correcto=false;
		}
		if(apellido1.equals("")){
			JOptionPane.showMessageDialog(textApellido1, "Introduce el primer apellido.","Campo vacío", JOptionPane.WARNING_MESSAGE);
			correcto=false;
		}
		if(apellido2.equals("")){
			JOptionPane.showMessageDialog(textApellido2, "Introduce el segundo apellido.","Campo vacío", JOptionPane.WARNING_MESSAGE);
			
			correcto=false;
		}
		if(telefono.equals("")){
			JOptionPane.showMessageDialog(textTelefono, "Introduce el telefono.","Campo vacío", JOptionPane.WARNING_MESSAGE);
			correcto=false;
		}
		if(email.equals("")){
			JOptionPane.showMessageDialog(textEmail, "Introduce el email.","Campo vacío", JOptionPane.WARNING_MESSAGE);
			correcto=false;
		}

		return correcto;
	}
	
	/**
     * KeyPressed responde al KeyListener cuando el usuario pulsa una tecla.
     * 
     */
	public void keyPressed(KeyEvent key) {}

	/**
     * KeyReleased responde al KeyListener cuando el usuario suelta una tecla.
     * 
     */
	public void keyReleased(KeyEvent key) {
	
		if(key.getSource()==textDni){
			
			try {
				
				//Validar el campo textField
				if(textDni.getText().equals("")){
					JOptionPane.showMessageDialog(textDni, "Introduce 8 números y una letra.","Error DNI", JOptionPane.WARNING_MESSAGE);
				}
		
				dni=textDni.getText().toUpperCase(); //obtiene el dni desde el JTextField.
				
				boolean valida=false;
				
				if(dni.length()>8){
					valida=VentanaAnadirMatriculas.validarDni(dni);
				}
				
				if(valida==true){
	
					//Se realiza la consulta para saber si la matricula es Única en la base de datos y en el array
					if(dniExiste(dni)==true||VentanaAnadirMatriculas.existe(dni)){
						JOptionPane.showMessageDialog(textDni, "El DNI ya existe.","Error", JOptionPane.WARNING_MESSAGE);
						textDni.setText("");
	
					}
					else{
					
					textNombre.setEnabled(true);
					textDni.setEnabled(false);
					textApellido1.setEnabled(true);
					textApellido2.setEnabled(true);
					textTelefono.setEnabled(true);
					textEmail.setEnabled(true);
					cmbBoxGrupo.setEnabled(true);

					}
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} 
		}
	}

	/**
     * KeyTyped responde al KeyListener cuando el usuario pulsa y suelta una tecla.
     * 
     */
	public void keyTyped(KeyEvent key) {}
	
	/**
     * ActionPerformed responde al evento producido del ActionListener
     * 
     */
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getSource()==cmbBoxGrupo){
			
			grupo=cmbBoxGrupo.getSelectedItem().toString(); //Guardar un item del combobox la variable de clase de tipo String
			btnAnadir.setEnabled(true);
			cmbBoxGrupo.setEnabled(false);
		}

		if(evento.getSource()==btnAnadir){
			
			boolean validar=validarCampos(textNombre.getText(),textApellido1.getText(),textApellido2.getText(),textTelefono.getText(),textEmail.getText());			

			if(validar==false){}
			else{

				if(VentanaAnadirMatriculas.esNumerico(textNombre.getText()) || VentanaAnadirMatriculas.esCaracter(textNombre.getText().toUpperCase())==false  ||textNombre.getText().length()>15){
					
					JOptionPane.showMessageDialog(textNombre, "Introduce letras en el nombre.","Error", JOptionPane.WARNING_MESSAGE);
					textNombre.setText("");
					
				}
				else{
					
					if(VentanaAnadirMatriculas.esNumerico(textApellido1.getText())|| VentanaAnadirMatriculas.esCaracter(textApellido1.getText().toUpperCase())==false  ||textApellido1.getText().length()>15){
						
						JOptionPane.showMessageDialog(textApellido1, "Introduce letras en el 1º apellido","Error", JOptionPane.WARNING_MESSAGE);
						textApellido1.setText("");
					}
					else{
						
						if(VentanaAnadirMatriculas.esNumerico(textApellido2.getText())|| VentanaAnadirMatriculas.esCaracter(textApellido2.getText().toUpperCase())==false ||textApellido2.getText().length()>15){
							
							JOptionPane.showMessageDialog(textApellido1, "Introduce letras en el 2º apellido","Error", JOptionPane.WARNING_MESSAGE);
							textApellido2.setText("");
						}
						else{
							
							try{

								String nombre=textNombre.getText().toUpperCase().trim();
								String apellido1=textApellido1.getText().toUpperCase().trim();
								String apellido2=textApellido2.getText().toUpperCase().trim();
								
								if(textTelefono.getText().length()!=9){
								
									JOptionPane.showMessageDialog(null, "Introduce 9 numeros en el teléfono.","Error", JOptionPane.WARNING_MESSAGE);
									textTelefono.setText("");
								}
								else{
								
									int telefono=Integer.parseInt(textTelefono.getText().trim());
									
									if(validarEmail(textEmail.getText())==true && textEmail.getText().length()<46){
									
										cadena="INSERT INTO monitores VALUES ('"+dni+"','"+nombre+"','"+apellido1+"','"+apellido2+"','"+telefono+"','"+textEmail.getText().trim()+"','"+grupo+"');"; //Sentencia de sql para insertar datos en la tabla matriculas
										GestorBD.consultaActualiza(cadena); //Ejecuta la actualización
										
										cadena="INSERT INTO grupos VALUES ('"+dni+"','"+nombre+"','"+apellido1+"','"+apellido2+"','"+grupo+"','SI');"; //Sentencia de sql para insertar datos en la tabla matriculas
										GestorBD.consultaActualiza(cadena); //Ejecuta la actualización
										
										VentanaAnadirMatriculas.gruposLog("Añadir",dni,"-",textTelefono.getText().trim(),grupo,"SI",nombre,apellido1,apellido2);

										JOptionPane.showMessageDialog(null, "Los datos han sido guardados correctamente.");
										
										this.dispose();
										
										@SuppressWarnings("unused")
										ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
									}
									else{
										JOptionPane.showMessageDialog(null, "Introduce el email menor que 45 caracteres.","Error", JOptionPane.WARNING_MESSAGE);
										textEmail.setText("");
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

		}
		
	}
	
	/**
     * Sirve para agregar un actionListener asociado al botón llamado "Volver".
     * 
     * @param accion Es un ActionListener para que tenga una escucha el botón "Volver".
     *  
     */
	public void agregarListener(ActionListener accion){
		
		btnVolver.addActionListener(accion);
		
	} //Cierre de método agregarListener
}
