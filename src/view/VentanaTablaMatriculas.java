package view;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import controller.ControllerVentanaEditarMatriculas;
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
public class VentanaTablaMatriculas extends JFrame {

	//Atributos de la clase
	private JPanel contentPane;
	private DefaultTableModel modelo;
	private String [][] tabla; //array bidimensional
	private String [] vector={"dni", "nombre","apellido1","apellido2","edad","telefono","grupo"}; //cabecera
	private JScrollPane scrollPane;
	private JTable table;
	private JTextArea textArea;
	private static Statement stmt;
	private ResultSet rs;
	private static String[] datosBD=new String[7];
	private JButton btnCerrar;
	private String dni;

	//Constructor
	public VentanaTablaMatriculas() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 594, 367);
		setResizable(false);
		setTitle("Editar matrículas");
		setIconImage(new ImageIcon(getClass().getResource("Img/editarMatricula.png")).getImage()); //logo de la ventana

		try {
			componentes();
			
			GestorBD.conectar();
			stmt=GestorBD.conexion();
			
			String cadena = "SELECT * FROM matriculas;";
			rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
			
			while (rs.next()) {
				
				datosBD[0]=rs.getString(1);
				datosBD[1]=rs.getString(2);
				datosBD[2]=rs.getString(3);
				datosBD[3]=rs.getString(4);
				datosBD[4]=rs.getString(5);
				datosBD[5]=rs.getString(6);
				datosBD[6]=rs.getString(7);

	            modelo.addRow(datosBD);
	            
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
		scrollPane.setBounds(10, 11, 558, 273);
		contentPane.add(scrollPane);
		
		modelo = new DefaultTableModel(tabla,vector);
		
		table = new JTable(modelo);
		table.addMouseListener(new MouseAdapter() {
			
		      @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
		         int fila = table.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1))
		            dni=(String) modelo.getValueAt(fila,columna);
		         String nombre=(String) modelo.getValueAt(fila,1);
		         String apellido=(String) modelo.getValueAt(fila,2);
		         JOptionPane.showMessageDialog(null, "Has elegido "+nombre+" "+apellido);
		         ControllerVentanaEditarMatriculas frame2 = new ControllerVentanaEditarMatriculas(dni);
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
		btnCerrar.setBounds(245, 295, 106, 33);
		contentPane.add(btnCerrar);
	}

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