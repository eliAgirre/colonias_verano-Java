package view;

import java.awt.*;
import java.awt.event.*;
import java.security.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

import manager.GestorBD;
import model.Usuario;
import controller.ControllerVentanaPrincipal;


/**
 * Extiende de la clase JFrame implementando el ActionListener para el botón llamado "Iniciar Sesion" y "Ayuda". 
 * Contiene dos JTextFields, uno para introducir el usuario y el otro para la contraseña. 
 * Para cada JTextField se incluye los JLabels. 
 * Además se ha incluido un botón llamado "Ayuda" para recordar el usuario y su contraseña.
 * 
 * @author EA
 * @since Mayo 12, 2014
 * @version 1.0.0
 * @see Main
 * @see GestorBD
 * @see	Usuario
 * @see ControladorVentanaPrincipal
 */

@SuppressWarnings("serial")
public class VentanaEntrada extends JFrame implements ActionListener{

	//Atributos de la clase
	private JPanel contentPane;
	private static JTextField textUsuario;
	private static JPasswordField textContrasena;
	private JButton btnIniciarSesion;
	private JButton btnAyuda;
	private JLabel label;
	private JLabel lblCompleteSusDatos;
	private static Statement stmt;
	private static ResultSet rs;
	private String usuario;
	private Usuario login=new Usuario();

	/**
     * Constructor sin parámetros, contiene el método componentes y realiza la conexión con la base de datos.
	 * @throws SQLException Contiene información sobre un error de acceso a la base de datos.
     */
	public VentanaEntrada() throws SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 298, 354);
		setTitle("Login de usuario");
		setIconImage(new ImageIcon(getClass().getResource("Img/logo.png")).getImage()); //logo de la ventana
		setResizable(false);
		
		componentes();
		this.getRootPane().setDefaultButton(btnIniciarSesion); //para poder pulsar "ENTER" y que active btnIniciarSesion 
		
		GestorBD.conectar();
		stmt=GestorBD.conexion();
	}

	/**
     * Contiene todos los componentes para aparecer en la VentanaEntrada.
     *  
     */
	public void componentes(){
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		label = new JLabel("\u00A1Bienvenido!");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(80, 11, 127, 21);
		contentPane.add(label);
		
		lblCompleteSusDatos = new JLabel("Complete sus datos, por favor.");
		lblCompleteSusDatos.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompleteSusDatos.setBounds(66, 43, 178, 14);
		contentPane.add(lblCompleteSusDatos);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(205, 92, 92));
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuario.setBounds(80, 81, 66, 21);
		getContentPane().add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setForeground(new Color(205, 92, 92));
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContrasena.setBounds(80, 144, 87, 21);
		getContentPane().add(lblContrasena);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(80, 113, 127, 20);
		getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		
		textContrasena = new JPasswordField();
		textContrasena.setColumns(10);
		textContrasena.setBounds(80, 176, 127, 20);
		getContentPane().add(textContrasena);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBackground(new Color(255, 204, 204));
		btnIniciarSesion.addActionListener((ActionListener)this);
		btnIniciarSesion.setBounds(80, 218, 127, 23);
		getContentPane().add(btnIniciarSesion);
		
		JLabel lblsiNoRecuerda = new JLabel();
		lblsiNoRecuerda.setText("<html><p>Si no recuerda sus datos<br>Haga clic en el icono de ayuda.</p></html>");
		lblsiNoRecuerda.setForeground(new Color(102, 102, 102));
		lblsiNoRecuerda.setBounds(50, 270, 177, 35);
		contentPane.add(lblsiNoRecuerda);
		
		btnAyuda = new JButton();
		btnAyuda.addActionListener((ActionListener)this);
		btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Img/ayuda.png"))); // Imagen del boton ayuda
		btnAyuda.setBorder(null);
		btnAyuda.setBounds(227, 270, 33, 33);
		contentPane.add(btnAyuda);
		
		
	}

	/**
     * Comprueba si el usuario existe en la base de datos de la tabla de usuarios.
     *  
     * @param usuario Obtiene un String del JTextField.
     * @throws SQLException Contiene información sobre un error de acceso a la base de datos.
     *  
     */
	private static String usuarioExiste(String usuario) throws SQLException{
		 
		 String cadena = "SELECT * FROM usuarios";

		 rs=GestorBD.consulta(stmt, cadena);
		 
		 while (rs.next()){
			 
			 if(rs.getString("usuario").equals(usuario))
			 	 return usuario;
		 }
		 rs.close();
		 return null;
		 
	 }
	
	/**
     * Cifra la contraseña en MD5.
     *  
     * @param clear Obtiene un String del JTextField de la contraseña.
     * @throws Exception Es una clase Exception que indica que hay algún código problemático.
     *  
     */
	private static String md5(String clear) throws Exception {
		
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] b = md.digest(clear.getBytes());
	    int size = b.length;
	    StringBuffer h = new StringBuffer(size);
	    //algoritmo y arreglo md5
	        for (int i = 0; i < size; i++) {
	            int u = b[i] & 255;
	                if (u < 16) {
	                    h.append("0" + Integer.toHexString(u));
	                }
	               else {
	                    h.append(Integer.toHexString(u));
	               }
	           }
	      //clave encriptada
	      return h.toString();
	}
	
	/**
     * Comprueba el usuario y su contraseña en la tabla usuarios.
     *  
     * @param usuario Obtiene un String del JTextField llamado "textUsuario".
     * @throws Exception Es una clase Exception que indica que hay algún código problemático.
     * @throws SQLException Contiene información sobre un error de acceso a la base de datos.
     *  
     */
	@SuppressWarnings("deprecation")
	private static boolean accesoValido(String usuario) throws Exception,SQLException{
		 
		 boolean correcto=true;

		 String cadena="SELECT * FROM usuarios WHERE usuario='"+usuario+"';";
		 
		 rs=GestorBD.consulta(stmt,cadena);
		 
		 while (rs.next()){

			 if(!(rs.getString("password").equals(md5(textContrasena.getText())))){
				 JOptionPane.showMessageDialog(null, "La contraseña no es correcta","Error", JOptionPane.WARNING_MESSAGE);
				 textContrasena.setText("");
				 return correcto=false;
			 }
		 }
		 return correcto;
		 
	 }
	
	/**
     * ActionPerformed responde al evento producido del ActionListener.
     *  
     * @param evento ActionEvent es un evento ocurrido por una acción generado por el botón "Iniciar Sesion" o el botón "Ayuda".
     *  
     */
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getSource()==btnAyuda){
			
			JOptionPane.showMessageDialog(null, "Lista de Usuarios:\n"
	                + "\nAdministrador: \n\tadmin - - - - Adm1n"
	        		+ "\n\nMonitor:    \n\tmonit - - - - M0nit"
	                + "\n\nDe Consulta:\n\tpadre - - - - Padr3 \n  ","Usuarios", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(evento.getSource()==btnIniciarSesion){
			
			try {
				
				if(textUsuario.getText().equals("")||textContrasena.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Hay campos vacios","Error", JOptionPane.WARNING_MESSAGE);
				}
				else{
				
					usuario=textUsuario.getText(); //Obtiene el texto del JTextField de usuario
					
					//Si el usuario no existe
					if(usuarioExiste(usuario)==null){ 
						
						JOptionPane.showMessageDialog(null, "El usuario no es válido.","Error", JOptionPane.WARNING_MESSAGE);
						textUsuario.setText(""); //Establece el JTextField de usuario en blanco
						textContrasena.setText(""); //Establece el JTextField de contraseña en blanco
					}
					
					//Si el usuario existe
					else{
						
						usuario=usuarioExiste(usuario); //Se obtiene el usuario válido
						
						//Si la contraseña encriptada es la del usuario en concreto se abre la clase VentanaPrincipal
						if(accesoValido(usuario)==true){  
							
							String clave=md5(textContrasena.getText());
							
							String cadena="SELECT * FROM usuarios WHERE usuario='"+usuario+"';";
							
							rs=GestorBD.consulta(stmt,cadena);
							
							while (rs.next()){
								 
								login.setTipo(rs.getString("tipo")); //Establece el tipo del usuario mediante la consulta de la base de datos
								 
							}
							
							login.setUsuario(usuario); //Establece el usuario
							login.setPassword(clave); //Establece la contraseña
							
							JOptionPane.showMessageDialog(null, "Usuario de tipo "+login.getTipo()+" autentificado "+ "exitosamente.", "Autentificado", JOptionPane.PLAIN_MESSAGE);
							
							String tipo=login.getTipo();
							
							ControllerVentanaPrincipal controladorVentanaPrincipal=new ControllerVentanaPrincipal(usuario,tipo); //Se instancia el controlador de la ventana principal
							this.dispose(); //La clase VentanaEntrada se cierra
							
						}
					}
				}
				
			} catch (Exception e) {
	
					JOptionPane.showMessageDialog(null, e.getMessage());
			} //Cierre del exception
				
		} //Cierre del evento (botón Iniciar Sesion)
		
	} //Cierre del actionPerformed		
} //Cierre de la clase

