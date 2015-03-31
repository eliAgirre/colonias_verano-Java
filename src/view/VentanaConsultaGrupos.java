package view;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import manager.GestorBD;

import java.awt.Color;

/**
 * 
 * 
 * @author EA
 * @since Mayo 19, 2014
 * @version 1.0.0
 * @see	VentanaPrincipal
 */

@SuppressWarnings("serial")
public class VentanaConsultaGrupos extends JFrame implements ActionListener{

	//Atributos de la clase
	private JPanel contentPane;
	private static JTextField textDni;
	private DefaultTableModel modelo;
	private String [][] tabla; //array bidimensional
	private String [] vector={"dni", "nombre","apellido1","apellido2","grupo","monitor"}; //cabecera
	private JScrollPane scrollPane;
	private JTable table;
	private JTextArea textArea;
	private static Statement stmt;
	private ResultSet rs;
	private JButton btnBuscar;
	private static String[] datosBD=new String[6];
	private JButton btnCerrar;
	@SuppressWarnings("unused")
	private String cadena;
	private static String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

	//Constructor
	public VentanaConsultaGrupos() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 539, 415);
		setResizable(false);
		setTitle("Consulta de grupos");
		setIconImage(new ImageIcon(getClass().getResource("Img/grupo_icon.png")).getImage()); //logo de la ventana

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
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduzcaElDni = new JLabel("Introduzca el DNI de la persona o el grupo:");
		lblIntroduzcaElDni.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduzcaElDni.setBounds(21, 28, 246, 14);
		contentPane.add(lblIntroduzcaElDni);
		
		textDni = new JTextField();
		textDni.setBounds(22, 65, 205, 20);
		contentPane.add(textDni);
		textDni.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(255, 204, 204));
		btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/buscar.png"))); //Imagen de buscar
		btnBuscar.addActionListener((ActionListener) this);
		btnBuscar.setBounds(269, 59, 112, 33);
		contentPane.add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(21, 112, 492, 214);
		contentPane.add(scrollPane);
		
		modelo = new DefaultTableModel(tabla,vector);
		
		table = new JTable(modelo);
		table.setBackground(new Color(255, 255, 255));
		table.addMouseListener(new MouseAdapter() {
			
		      public void mouseClicked(MouseEvent e) {
		         int fila = table.rowAtPoint(e.getPoint());
		         int columna = 0;
		         if ((fila > -1) && (columna > -1))
		        	 cadena=(String) modelo.getValueAt(fila,columna);
	         
		      }
		   });
		
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		scrollPane.setColumnHeaderView(textArea);
		
		btnCerrar = new JButton("Menu");
		btnCerrar.setBackground(new Color(255, 204, 204));
		btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/home.png"))); //Imagen de volver al menu
		btnCerrar.setBounds(212, 337, 106, 33);
		contentPane.add(btnCerrar);
	}
	
	private void limpiarTabla(JTable tabla){
		
        try {
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            int filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
        
    }
	
	private static boolean validarDni(String dni){
		
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
	
	//Método para dar respuesta al ActionListener
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource()==btnBuscar){
			
			try {
				
				if(textDni.getText().equals("")){
					
					JOptionPane.showMessageDialog(textDni, "Introduce DNI o grupo (A o B).","Error", JOptionPane.WARNING_MESSAGE);
					
				}
				else{
					
					limpiarTabla(table);
					
					String texto=textDni.getText().toUpperCase();
					
					if(VentanaAnadirMatriculas.esNumerico(texto)==false&&texto.length()==1){ //Es una letra
						
						if(texto.length()!=1){
							
							JOptionPane.showMessageDialog(textDni, "Introduce una letra.","Error", JOptionPane.WARNING_MESSAGE);
						}
						else{
							
							if(!(texto.equals("A")||texto.equals("B"))){
								
								JOptionPane.showMessageDialog(textDni, "Introduce el grupo A o B.","Error", JOptionPane.WARNING_MESSAGE);
							}
							
							if(texto.equals("A")||texto.equals("B")){
								
								table.setEnabled(true);
								
								if(texto.length()==1){
									
									String cadena = "SELECT * FROM grupos WHERE grupo='"+texto+"';";
									rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
									
									while (rs.next()) {
										
										datosBD[0]=rs.getString(1);
										datosBD[1]=rs.getString(2);
										datosBD[2]=rs.getString(3);
										datosBD[3]=rs.getString(4);
										datosBD[4]=rs.getString(5);
										datosBD[5]=rs.getString(6);
					
							            modelo.addRow(datosBD);
							            
							            VentanaAnadirMatriculas.gruposLog("Cargar", rs.getString(1),"-","---------",rs.getString(5),rs.getString(6),rs.getString(2), rs.getString(3), rs.getString(4));
									}
									rs.close(); //Cierra la consulta
								}
								
							}
						}
					}
					
					else{ //Es un número
						
						if(texto.length()!=9){
							
							JOptionPane.showMessageDialog(textDni, "Introduce 8 números y una letra.","Error", JOptionPane.WARNING_MESSAGE);
						}
						else{
							if(VentanaAnadirMatriculas.existe(texto)==false){
								
								JOptionPane.showMessageDialog(null, "El DNI no es existe.","Error", JOptionPane.WARNING_MESSAGE);
								
							}
							
							if(validarDni(texto)&&VentanaAnadirMatriculas.existe(texto)){
								
								table.setEnabled(true);
								
								String cadena = "SELECT * FROM grupos WHERE dni='"+texto+"';";
								rs = GestorBD.consulta(stmt,cadena); //devuelve el resultado
								
								while (rs.next()) {
									
									datosBD[0]=rs.getString(1);
									datosBD[1]=rs.getString(2);
									datosBD[2]=rs.getString(3);
									datosBD[3]=rs.getString(4);
									datosBD[4]=rs.getString(5);
									datosBD[5]=rs.getString(6);
				
						            modelo.addRow(datosBD);
						            
								}
								rs.close(); //Cierra la consulta
							}
						}
					}
					textDni.setText("");
				}
				
			
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
	}

	//Metodo para cerrar esta la clase
	public void agregarListener(ActionListener accion){
		
		btnCerrar.addActionListener(accion);
		
	} //Cierre de método agregarListener

}