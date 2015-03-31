package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

import manager.GestorJuegos;
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
public class VentanaConsultaJuegos extends JFrame {

	//Atributos de la clase
	private JPanel contentPane;
	private int id;
	private JLabel lblObjetivo;
	private GestorJuegos gestorJuegos=new GestorJuegos();
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private static int ocurrencia=0;
	private JButton btnCerrar;
	private JLabel areaDescripcion;
	private JLabel areaObjetivo;
	private JLabel areaTipo;
	private JLabel textNombre;
	private JLabel textId;

	//Constructor
	public VentanaConsultaJuegos() {
	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 516, 368);
		setResizable(false);
		setTitle("Consultar juegos");
		setIconImage(new ImageIcon(getClass().getResource("Img/juegos_icon.png")).getImage()); //logo de la ventana
		try {
			
			componentes();

			gestorJuegos.cargarArraylist();		
			

			for(int i=0;i<gestorJuegos.obtenerLongitud();i++){
				
				textId.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getId()));
				textNombre.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getNombre()));
				areaTipo.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getTipo()));
				areaObjetivo.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getObjetivo()));
				areaDescripcion.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getDescripcion()));

			}
			
			//si hay uno el boton anterior tiene que estar deshabilitado
			if(gestorJuegos.obtenerLongitud()>0){
				
				btnSiguiente.setEnabled(false);
				
				for(int i=0;i<gestorJuegos.obtenerLongitud();i++){
					
					textId.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getId()));
					textNombre.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getNombre()));
					areaTipo.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getTipo()));
					areaObjetivo.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getObjetivo()));
					areaDescripcion.setText(String.valueOf(gestorJuegos.obtenerJuego(0).getDescripcion()));
					
				}
			}
			
			//si hay dos o más de dos tiene ue estar los dos habilitados
			if(gestorJuegos.obtenerLongitud()>1){
				
				btnSiguiente.setEnabled(true);
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
		
		JLabel lblId = new JLabel("Id:");
		lblId.setForeground(new Color(205, 92, 92));
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(26, 73, 72, 14);
		contentPane.add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(205, 92, 92));
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(26, 101, 72, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(new Color(205, 92, 92));
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setBounds(27, 129, 46, 14);
		getContentPane().add(lblTipo);
		
		lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setForeground(new Color(205, 92, 92));
		lblObjetivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblObjetivo.setBounds(26, 170, 71, 14);
		contentPane.add(lblObjetivo);
		
		JLabel lblDescipcion = new JLabel("Descipci\u00F3n:");
		lblDescipcion.setForeground(new Color(205, 92, 92));
		lblDescipcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescipcion.setBounds(26, 225, 77, 14);
		contentPane.add(lblDescipcion);
		
		JLabel lblListaDeLos = new JLabel("<html><h2>Lista de juegos:</h2>");
		lblListaDeLos.setForeground(new Color(220, 20, 60));
		lblListaDeLos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblListaDeLos.setHorizontalAlignment(SwingConstants.LEFT);
		lblListaDeLos.setBounds(26, 23, 246, 22);
		contentPane.add(lblListaDeLos);
		
		btnAnterior = new JButton("");
		btnAnterior.setBackground(new Color(255, 204, 204));
		btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/anterior.png"))); 
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					btnSiguiente.setEnabled(true);
					ocurrencia--;
					
					id=Integer.parseInt(textId.getText());
				
					@SuppressWarnings("unused")
					int posicion=gestorJuegos.buscar(id);
					
					for(int i=0;i<gestorJuegos.obtenerLongitud();i++){
						
						textId.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getId()));
						textNombre.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getNombre()));
						areaTipo.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getTipo()));
						areaObjetivo.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getObjetivo()));
						areaDescripcion.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getDescripcion()));

					}	
					
					if(ocurrencia==0){
						btnAnterior.setEnabled(false);
					}
					
				} catch (ListaVaciaExcepcion e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		btnAnterior.setEnabled(false);
		btnAnterior.setBounds(108, 283, 106, 33);
		contentPane.add(btnAnterior);
		
		btnSiguiente = new JButton("");
		btnSiguiente.setBackground(new Color(255, 204, 204));
		btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/siguiente.png"))); 
		btnSiguiente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					btnAnterior.setEnabled(true);
					ocurrencia++;
					
					id=Integer.parseInt(textId.getText());
				
					@SuppressWarnings("unused")
					int posicion=gestorJuegos.buscar(id);
					
					for(int i=0;i<gestorJuegos.obtenerLongitud();i++){
						
						textId.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getId()));
						textNombre.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getNombre()));
						areaTipo.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getTipo()));
						areaObjetivo.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getObjetivo()));
						areaDescripcion.setText(String.valueOf(gestorJuegos.obtenerJuego(ocurrencia).getDescripcion()));

					}	
					
					if(gestorJuegos.obtenerLongitud()==ocurrencia+1){
						btnSiguiente.setEnabled(false);
					}
					
				} catch (ListaVaciaExcepcion e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(236, 283, 106, 33);
		contentPane.add(btnSiguiente);
		
		btnCerrar = new JButton("Menu");
		btnCerrar.setBackground(new Color(255, 204, 204));
		btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/home.png"))); //Imagen de volver al menu
		btnCerrar.setBounds(366, 283, 106, 33);
		contentPane.add(btnCerrar);
		
		areaDescripcion = new JLabel("");
		areaDescripcion.setBounds(108, 204, 364, 53);
		contentPane.add(areaDescripcion);
		
		areaObjetivo = new JLabel("");
		areaObjetivo.setBounds(108, 160, 364, 33);
		contentPane.add(areaObjetivo);
		
		areaTipo = new JLabel("");
		areaTipo.setBounds(108, 129, 180, 14);
		contentPane.add(areaTipo);
		
		textNombre = new JLabel("");
		textNombre.setBounds(108, 101, 180, 17);
		contentPane.add(textNombre);
		
		textId = new JLabel("");
		textId.setBounds(108, 73, 180, 17);
		contentPane.add(textId);

	}

	//Metodo para cerrar la clase VentanaConsultarJuegos
	public void agregarListener(ActionListener accion){
		
		btnCerrar.addActionListener(accion);
		
	} //Cierre de método agregarListener
} //Cierre de la clase