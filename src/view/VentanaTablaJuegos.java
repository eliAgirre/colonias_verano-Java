package view;

import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import controller.ControllerVentanaEditarJuegos;
import manager.GestorBD;

/**
 * Descripcion
 * 
 * @author EA
 * @since Mayo 18, 2014
 * @version 1.0.0
 * @see	VentanaPrincipal
 * @see ControladorVentanaEditarMatriculas;
 * @see GestorBD;
 */

@SuppressWarnings("serial")
public class VentanaTablaJuegos extends JFrame {

	//Atributos de la clase
	private JPanel contentPane;
	private DefaultTableModel modelo;
	private String [][] tabla; //array bidimensional
	private String [] vector={"id", "nombre","tipo","objetivo"}; //cabecera
	private JScrollPane scrollPane;
	private JTable table;
	private JTextArea textArea;
	private static Statement stmt;
	private ResultSet rs;
	private static String[] datosBD=new String[4];
	private JButton btnCerrar;
	private int id;
	private String cadena;

	//Constructor
	public VentanaTablaJuegos() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 648, 407);
		setResizable(false);
		setTitle("Editar juegos");
		setIconImage(new ImageIcon(getClass().getResource("Img/editarJuegos.png")).getImage()); //logo de la ventana

		try {
			componentes();
			
			GestorBD.conectar();
			stmt=GestorBD.conexion();
			
			String cadena = "SELECT * FROM juegos;";
			rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
			
			while (rs.next()) {
				
				datosBD[0]=rs.getString(1);
				datosBD[1]=rs.getString(2);
				datosBD[2]=rs.getString(3);
				datosBD[3]=rs.getString(4);

	            modelo.addRow(datosBD);
	            
	            calendarioLog("Cargar","","","",rs.getString(6),rs.getString(1),rs.getString(2), rs.getString(5));
	         
			}
			rs.close(); //Cierra la consulta

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	//Metodo componentes
	public void componentes(){
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 11, 612, 303);
		contentPane.add(scrollPane);
		
		modelo = new DefaultTableModel(tabla,vector);
		
		table = new JTable(modelo);
		table.addMouseListener(new MouseAdapter() {
			
		      @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
		         int fila = table.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1))
		        	 cadena=(String) modelo.getValueAt(fila,columna);
		         	id=Integer.parseInt(cadena);
		         String nombre=(String) modelo.getValueAt(fila,1);

		         JOptionPane.showMessageDialog(null, "Has elegido "+nombre);
		         
		         ControllerVentanaEditarJuegos frame = new ControllerVentanaEditarJuegos(id);
					setVisible(false);
		         
		      }
		   });
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setEnabled(false);
		scrollPane.setColumnHeaderView(textArea);
		
		btnCerrar = new JButton("Menu");
		btnCerrar.setBackground(new Color(255, 204, 204));
		btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/home.png"))); //Imagen de volver al menu
		btnCerrar.setBounds(270, 325, 106, 33);
		contentPane.add(btnCerrar);
	}
	
	/**
     * Obtiene la feha de sistema 
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
	
	public static void calendarioLog(String evento,String dia, String nombreMonitor, String apellidoMonitor, String grupo, String idJuego, String nombreJuego,String descripcion){
		
		try {
			
			FileWriter ficheroW=new FileWriter("src/archivos/calendario.log",true);
			BufferedWriter bW=new BufferedWriter(ficheroW);
			
			Timestamp hora=obtenerFechaSistema();
			
			String datos=evento+"   "+hora +"   "+"         "+"   "+"     "+ " "+"       "+ "      "+grupo+"        "+idJuego+"      "+nombreJuego+"        "+descripcion;

			bW.write(datos);
			bW.newLine();
			bW.newLine();
			bW.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	} //Cierre del método archivoLog

	/**
     * Sirve para agregar un actionListener asociado al botón llamado "Menu".
     * 
     * @param accion Es un ActionListener para que tenga una escucha el botón "Menu".
     *  
     */
	public void agregarListener(ActionListener accion){
		
		btnCerrar.addActionListener(accion);
		
	} //Cierre de método agregarListener
	
} //Cierre de la clase
