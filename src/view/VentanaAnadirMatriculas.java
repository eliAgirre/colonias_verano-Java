package view;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;

import controller.ControllerVentanaPrincipal;
import model.Grupo;
import model.Edad;
import manager.GestorBD;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Extiende de la clase JFrame implementando el ActionListener para el botón “Añadir” y el KeyListener para el JTextField llamado textDni. 
 * 
 * @author EA
 * @since Mayo 14, 2014
 * @version 1.0.0
 * @see	VentanaPrincipal
 */

@SuppressWarnings("serial")
public class VentanaAnadirMatriculas extends JFrame implements KeyListener,ActionListener{

	//Atributos de la clase
	private JPanel contentPane;
	private Grupo grupo;
	private Edad edad;
	private static JTextField textDni;
	private static JTextField textNombre;
	private static JTextField textTelefono;
	private static JTextField textApellido1;
	private static JTextField textApellido2;
	private JComboBox<Edad> cmbBoxEdad;
	private JComboBox<Grupo> cmbBoxGrupo;
	private JButton btnAnadir;
	private static String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
	private static String dni;
	private static Statement stmt;
	private static ResultSet rs;
	private String cadena;
	private int edadInt;
	private String grupoS;
	private JButton btnVolver;

	//Constructor
	public VentanaAnadirMatriculas() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 481, 330);
		setResizable(false);
		setTitle("Añadir matrícula");
		setIconImage(new ImageIcon(getClass().getResource("Img/anadirMatricula.png")).getImage()); //logo de la ventana
		
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
		
		JLabel lblIntroduzcaLosDatos = new JLabel("Introduzca los datos, por favor.");
		lblIntroduzcaLosDatos.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduzcaLosDatos.setBounds(24, 36, 178, 14);
		getContentPane().add(lblIntroduzcaLosDatos);
		
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
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setForeground(new Color(205, 92, 92));
		lblEdad.setBounds(258, 82, 46, 14);
		contentPane.add(lblEdad);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setForeground(new Color(205, 92, 92));
		lblTelefono.setBounds(258, 120, 63, 14);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setEnabled(false);
		textTelefono.setColumns(10);
		textTelefono.setBounds(326, 117, 86, 20);
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
		
		cmbBoxGrupo = new JComboBox<Grupo>();
		cmbBoxGrupo.setEnabled(false);
		cmbBoxGrupo.setBounds(326, 162, 86, 20);
		contentPane.add(cmbBoxGrupo);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setBackground(new Color(255, 204, 204));
		btnAnadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/anadir.png"))); //Imagen de añadir una matricula
		btnAnadir.setEnabled(false);
		btnAnadir.addActionListener((ActionListener)this);
		btnAnadir.setBounds(260, 247, 106, 33);
		getContentPane().add(btnAnadir);
		
		cmbBoxEdad = new JComboBox<Edad>();
		cmbBoxEdad.addActionListener((ActionListener)this);
		cmbBoxEdad.setEnabled(false);
		cmbBoxEdad.setBounds(326, 82, 86, 20);
		contentPane.add(cmbBoxEdad);
		
		btnVolver = new JButton("Menu");
		btnVolver.setBackground(new Color(255, 204, 204));
		btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/home.png"))); //Imagen de volver al menu
		btnVolver.setBounds(105, 247, 106, 33);
		contentPane.add(btnVolver);
	}
	
	//Método para saber el formato correcto del DNI.
	public static boolean validarDni(String dni){
		
		boolean correcto=true;
		
		try{			
			
			int numDni=Integer.parseInt((dni.substring(0,8)));
			String letra=dni.substring(8,9);
			
			String l=String.valueOf(letras.charAt(numDni % 23));
			if(!l.equals(letra)){
				JOptionPane.showMessageDialog(textDni, "El DNI no es correcto.","Error", JOptionPane.WARNING_MESSAGE);
				textDni.setText("");
				correcto=false;				
			}	
			
		}catch(NumberFormatException ne){
			
			JOptionPane.showMessageDialog(textDni, "Introduce los 8 primeros números del DNI","Error", JOptionPane.WARNING_MESSAGE);
			textDni.setText("");
			correcto=false;
		}
		return correcto;
	}
	
	/**
     * Para comprobar si sólo existe ese único DNI en la tabla matriculas.
     * @throws SQLException
     * @return boolean. Retorna un booleano para saber si la existe de el dni.
     * 
     */
	public static boolean dniExiste(String dni) throws SQLException{
		 
		 boolean encontrado=false;

		 String cadena = "SELECT * FROM matriculas;";
		 rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
		 
		 while (rs.next()){
			 
			 if(rs.getString("dni").equals(dni)){
				 encontrado=true;
			 }
		 }
		 return encontrado;
		 
	 }
	
	/**
     * Para comprobar si sólo existe ese único DNI en la tabla grupos
     * @throws SQLException
     * @return boolean. Retorna un booleano para saber si la existe de el dni.
     * 
     */
	public static boolean existe(String dni) throws SQLException{
		 
		 boolean encontrado=false;

		 String cadena = "SELECT * FROM grupos;";
		 rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
		 
		 while (rs.next()){
			 
			 if(rs.getString("dni").equals(dni)){
				 encontrado=true;
			 }
		 }
		 return encontrado;
		 
	 }
	 
	//Método para saber si todos los campos estan rellenados
	public static boolean validarCampos(String nombre,String apellido1,String apellido2,String telefono){
		
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

		return correcto;
	}
	
	//Comprobar si la cadena es número.
    public static boolean esNumerico(String cadena){
	    try {
	    	Integer.parseInt(cadena);
	    	return true;
	    } catch (NumberFormatException nfe){
	    	return false;
	    }
    }

    //Metodo para saber si ha metido solo letras
	public static boolean esCaracter(String palabra){
		
		for(int i = 0; i < palabra.length(); i++){
			if(!((palabra.charAt(i)>64 && palabra.charAt(i)<91) || palabra.charAt(i)=='Ñ'))
				return false;
		}
		return true;	
	}
	
	/**
     * Obtiene la fecha de sistema.
     * 
     * @return ts Devuelve un Timestamp.
     * 
     */
	private static Timestamp obtenerFechaSistema(){
		
		//creamos el objeto calendar y obtenemos la hora del sistema
		Calendar cal = Calendar.getInstance();
		
		//Se convierte el objeto calendar a timestamp
		Timestamp ts = new Timestamp(cal.getTimeInMillis()); 
		
		return ts;
		
	} //Cierre del método obtenerHoraSistema
	
	public static void gruposLog(String evento,String dni, String edad, String telefono,String grupo, String monitor, String nombre,String apellido1, String apellido2){
		
		try {
			
			FileWriter ficheroW=new FileWriter("src/files/grupos.log",true);
			BufferedWriter bW=new BufferedWriter(ficheroW);
			
			Timestamp hora=obtenerFechaSistema();
			
			String datos=evento+"   "+hora +"   "+dni +"   "+edad+ "    "+telefono+ "      "+grupo+"        "+monitor+"      "+nombre+"        "+apellido1+"       "+apellido2;

			bW.write(datos);
			bW.newLine();
			bW.newLine();
			bW.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	} //Cierre del método gruposLog
	
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
					valida=validarDni(dni);
				}
				
				if(valida==true){
	
					//Se realiza la consulta para saber si la matricula es Única en la base de datos y en el array
					if(dniExiste(dni)==true||existe(dni)==true){
						JOptionPane.showMessageDialog(null, "El DNI ya existe.","DNI duplicado", JOptionPane.WARNING_MESSAGE);
						textDni.setText("");
	
					}
					else{
					
					textNombre.setEnabled(true);
					textDni.setEnabled(false);
					textApellido1.setEnabled(true);
					textApellido2.setEnabled(true);
					cmbBoxEdad.setEnabled(true);
					
					//Cuando haya validado el dni el combobox de la edad se activa. Y se hace la consulta.
					cadena = "SELECT * FROM edad;"; //SQL en un String
				
					rs = GestorBD.consulta(stmt,cadena);//Devuelve el resultado
					
					while (rs.next()) { //Mientras que haya datos
						
						//Obtiene la edad de la tabla edad.
						edadInt=rs.getInt("edad");						
						
						//Se crea un nuevo objeto pasando parámetro la edad obtenido por un entero.
						edad=new Edad(edadInt); 
						
						//Se añade al JComboBox como ítem.
						cmbBoxEdad.addItem(edad); //se muestra el método toString de la clase Edad.
					}
					rs.close(); //Cierre de la consulta
										
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
		
		if(evento.getSource()==cmbBoxEdad){ //Al realizar el evento en el JComboBox de la edad
				
			String edadS=cmbBoxEdad.getSelectedItem().toString(); //Guardar un item del combobox la variable de clase de tipo String
			edadInt=Integer.parseInt(edadS); //Se converte el String al entero para crear el objeto matricula
			
			if(cmbBoxEdad.isValid()==true){ //si es válido JComboBox
				
				textTelefono.setEnabled(true); //activamos el JTextField
			
				try {
	
					//Cuando haya seleccionado la edad el combobox del grupo se activa. Y se hace la consulta.
					cadena = "SELECT * FROM grupo WHERE id="+edadS+";"; //la sentencia sql se guarda en un String
					
					rs = GestorBD.consulta(stmt,cadena); //Realiza la consulta
					 
					while (rs.next()) { //Mientras que haya datos
						
						//Obtiene la edad de la tabla edad.
						edadInt=rs.getInt("id");	
						grupoS=rs.getString("grupo");
						
						//Se crea un nuevo objeto pasando parámetro la edad obtenido por un entero y el grupo de tipo String
						grupo=new Grupo (edadInt,grupoS); 
						
						//Se añade al JComboBox como ítem.
						cmbBoxGrupo.addItem(grupo); //se muestra el método toString de la clase Grupo.
					}
					rs.close(); //Cierre de la consulta
			
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

				btnAnadir.setEnabled(true);
			}
		}

		if(evento.getSource()==btnAnadir){
			
			boolean validar=validarCampos(textNombre.getText(),textApellido1.getText(),textApellido2.getText(),textTelefono.getText());			

			if(validar==false){}
			else{

				if((esNumerico(textNombre.getText()))||esCaracter(textNombre.getText().toUpperCase())==false ||textNombre.getText().length()>15){
					
					JOptionPane.showMessageDialog(textNombre, "Introduce letras en el nombre","Error", JOptionPane.WARNING_MESSAGE);
					textNombre.setText("");
					
				}
				else{
					
					if((esNumerico(textApellido1.getText())||esCaracter(textApellido1.getText().toUpperCase())==false) ||textApellido1.getText().length()>15){
						JOptionPane.showMessageDialog(textApellido1, "Introduce letras en el 1º apellido","Error", JOptionPane.WARNING_MESSAGE);
						textApellido1.setText("");
					}
					else{
						
						if((esNumerico(textApellido2.getText())||esCaracter(textApellido2.getText().toUpperCase())==false) ||textApellido2.getText().length()>15){
							
							JOptionPane.showMessageDialog(textApellido2, "Introduce letras en el 2º apellido","Error", JOptionPane.WARNING_MESSAGE);
							textApellido2.setText("");
						}
						else{
							
							try{

								String nombre=textNombre.getText().toUpperCase().trim();
								String apellido1=textApellido1.getText().toUpperCase().trim();
								String apellido2=textApellido2.getText().toUpperCase().trim();
								
								if(textTelefono.getText().length()!=9){
									JOptionPane.showMessageDialog(textTelefono, "Introduce 9 números en el teléfono.","Error", JOptionPane.WARNING_MESSAGE);
									textTelefono.setText("");
								}
								else{
								
									int telefono=Integer.parseInt(textTelefono.getText().trim());
	
									cadena="INSERT INTO matriculas VALUES ('"+dni+"','"+nombre+"','"+apellido1+"','"+apellido2+"','"+edadInt+"','"+telefono+"','"+grupo.getGrupo()+"');"; //Sentencia de sql para insertar datos en la tabla matriculas
									GestorBD.consultaActualiza(cadena); //Ejecuta la actualización
									
									cadena="INSERT INTO grupos VALUES ('"+dni+"','"+textNombre.getText().toUpperCase()+"','"+textApellido1.getText().toUpperCase()+"','"+textApellido2.getText().toUpperCase()+"','"+grupo.getGrupo()+"','NO');"; //Sentencia de sql para insertar datos en la tabla matriculas
									GestorBD.consultaActualiza(cadena); //Ejecuta la actualización
									
									gruposLog("Añadir",dni,cmbBoxEdad.getSelectedItem().toString(),textTelefono.getText().trim(),grupo.getGrupo(),"NO",nombre,apellido1,apellido2);

									JOptionPane.showMessageDialog(null, "Los datos han sido guardados correctamente.");
									
									this.dispose();
									
									@SuppressWarnings("unused")
									ControllerVentanaPrincipal ControllerVentanaPrincipal=new ControllerVentanaPrincipal(); //Hace visible la ventanaPrincipal
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
