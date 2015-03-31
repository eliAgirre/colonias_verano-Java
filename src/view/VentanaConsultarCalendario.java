package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

import manager.GestorCalendario;
import manager.ListaVaciaExcepcion;

/**
 * 
 * 
 * @author EA
 * @since Mayo 18, 2014
 * @version 1.0.0
 * @see	VentanaPrincipal
 */

@SuppressWarnings("serial")
public class VentanaConsultarCalendario extends JFrame {

	//Atributos de la clase
	private JPanel contentPane;
	private JLabel lblObjetivo;
	private JLabel lblFecha;
	private JLabel lblMonitor;
	private JButton btnSiguiente;
	private JButton btnAnterior;
	private static int ocurrencia=0;
	private GestorCalendario gestorCalendario;
	private JButton btnCerrar;
	private JLabel lblGrupoColor;
	private JLabel areaDescripcion;
	private JLabel areaObjetivo;
	private JLabel textNombre;
	private JLabel areaGrupo;


	//Constructor
	public VentanaConsultarCalendario(String grupo) {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 589, 398);
		setResizable(false);
		setTitle("Calendario");
		setIconImage(new ImageIcon(getClass().getResource("Img/calendar_icon.png")).getImage()); //logo de la ventana
		
		try {
			
			componentes();
			
			gestorCalendario=new GestorCalendario();
			
			gestorCalendario.cargarArraylist(grupo);
			
			if(grupo.equals("a")){
				
				lblGrupoColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/grupoA.png"))); 
			}
			else{
				lblGrupoColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/grupoB.png"))); 
			}
			

			for(int i=0;i<gestorCalendario.obtenerLongitud();i++){
				
				lblFecha.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getDia()));
				lblMonitor.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getNombreResponsable()+" "+gestorCalendario.obtenerCalendario(0).getApellidoResponsable()));
				areaGrupo.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getGrupo()));
				textNombre.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getNombreJuego()));
				areaObjetivo.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getObjetivo()));
				areaDescripcion.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getDescripcion()));
				
				//Se carga el calendario.log para guardar los datos.
				gestorCalendario.calendarioLog("Cargar", String.valueOf(gestorCalendario.obtenerCalendario(i).getDia()),
				String.valueOf(gestorCalendario.obtenerCalendario(i).getNombreResponsable()), 
				gestorCalendario.obtenerCalendario(i).getApellidoResponsable(),
				String.valueOf(gestorCalendario.obtenerCalendario(i).getGrupo()),
				String.valueOf(gestorCalendario.obtenerCalendario(i).getIdJuego()),
				String.valueOf(gestorCalendario.obtenerCalendario(i).getNombreJuego()),
				String.valueOf(gestorCalendario.obtenerCalendario(i).getDescripcion()));

			}
			
			//si hay uno el boton anterior tiene que estar deshabilitado
			if(gestorCalendario.obtenerLongitud()>0){
				
				btnSiguiente.setEnabled(false);
				
				for(int i=0;i<gestorCalendario.obtenerLongitud();i++){
					
					lblFecha.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getDia()));
					lblMonitor.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getNombreResponsable()+" "+gestorCalendario.obtenerCalendario(0).getApellidoResponsable()));
					areaGrupo.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getGrupo()));
					textNombre.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getNombreJuego()));
					areaObjetivo.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getObjetivo()));
					areaDescripcion.setText(String.valueOf(gestorCalendario.obtenerCalendario(0).getDescripcion()));
					
				}
			}
			
			//si hay dos o más de dos tiene ue estar los dos habilitados
			if(gestorCalendario.obtenerLongitud()>1){
				
				btnSiguiente.setEnabled(true);
			}
			

		} catch (ListaVaciaExcepcion | SQLException e){
			
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	//Metodo componentes
	public void componentes(){
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblIntroduzcaLosDatos = new JLabel("<html><h2>Calendario:</h2>");
		lblIntroduzcaLosDatos.setForeground(new Color(220, 20, 60));
		lblIntroduzcaLosDatos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIntroduzcaLosDatos.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduzcaLosDatos.setBounds(26, 11, 246, 33);
		getContentPane().add(lblIntroduzcaLosDatos);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(205, 92, 92));
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(26, 137, 72, 14);
		getContentPane().add(lblNombre);
		
		lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setForeground(new Color(205, 92, 92));
		lblObjetivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblObjetivo.setBounds(26, 187, 71, 14);
		contentPane.add(lblObjetivo);
		
		JLabel lblDescipcion = new JLabel("Descipci\u00F3n:");
		lblDescipcion.setForeground(new Color(205, 92, 92));
		lblDescipcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescipcion.setBounds(26, 252, 77, 14);
		contentPane.add(lblDescipcion);
		
		JLabel lblDia = new JLabel("Dia:");
		lblDia.setForeground(new Color(205, 92, 92));
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDia.setBounds(26, 61, 72, 14);
		contentPane.add(lblDia);
		
		JLabel lblResponsable = new JLabel("Responsable:");
		lblResponsable.setForeground(new Color(205, 92, 92));
		lblResponsable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblResponsable.setBounds(200, 61, 86, 14);
		contentPane.add(lblResponsable);
		
		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setForeground(new Color(205, 92, 92));
		lblGrupo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGrupo.setBounds(451, 61, 72, 14);
		contentPane.add(lblGrupo);
		
		lblFecha = new JLabel("");
		lblFecha.setBounds(26, 86, 72, 14);
		contentPane.add(lblFecha);
		
		lblMonitor = new JLabel("");
		lblMonitor.setBounds(200, 89, 165, 14);
		contentPane.add(lblMonitor);
		
		lblGrupoColor = new JLabel("");
		lblGrupoColor.setBounds(356, 61, 72, 62);
		contentPane.add(lblGrupoColor);
		
		btnAnterior = new JButton("");
		btnAnterior.setBackground(new Color(255, 204, 204));
		btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/anterior.png"))); 
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					btnSiguiente.setEnabled(true);
					ocurrencia--;

					for(int i=0;i<gestorCalendario.obtenerLongitud();i++){					
						
						lblFecha.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getDia()));
						lblMonitor.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getNombreResponsable()+" "+gestorCalendario.obtenerCalendario(ocurrencia).getApellidoResponsable()));
						areaGrupo.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getGrupo()));
						textNombre.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getNombreJuego()));
						areaObjetivo.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getObjetivo()));
						areaDescripcion.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getDescripcion()));
	
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
		btnAnterior.setBounds(108, 303, 106, 33);
		contentPane.add(btnAnterior);
		
		btnSiguiente = new JButton("");
		btnSiguiente.setBackground(new Color(255, 204, 204));
		btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/siguiente.png"))); 
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					btnAnterior.setEnabled(true);
					ocurrencia++;
					
					for(int i=0;i<gestorCalendario.obtenerLongitud();i++){					
						
						lblFecha.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getDia()));
						lblMonitor.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getNombreResponsable()+" "+gestorCalendario.obtenerCalendario(ocurrencia).getApellidoResponsable()));
						areaGrupo.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getGrupo()));
						textNombre.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getNombreJuego()));
						areaObjetivo.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getObjetivo()));
						areaDescripcion.setText(String.valueOf(gestorCalendario.obtenerCalendario(ocurrencia).getDescripcion()));
	
					}	
					
					if(gestorCalendario.obtenerLongitud()==ocurrencia+1){
						btnSiguiente.setEnabled(false);
					}
					
				} catch (ListaVaciaExcepcion e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
			}
		});
		btnSiguiente.setBounds(259, 303, 106, 33);
		btnSiguiente.setEnabled(false);
		contentPane.add(btnSiguiente);
		
		btnCerrar = new JButton("Menu");
		btnCerrar.setBackground(new Color(255, 204, 204));
		btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/home.png"))); //Imagen de volver al menu
		btnCerrar.setBounds(404, 303, 106, 33);
		contentPane.add(btnCerrar);
		
		areaDescripcion = new JLabel("");
		areaDescripcion.setBounds(108, 232, 402, 50);
		contentPane.add(areaDescripcion);
		
		areaObjetivo = new JLabel("");
		areaObjetivo.setBounds(108, 181, 402, 20);
		contentPane.add(areaObjetivo);
		
		textNombre = new JLabel("");
		textNombre.setBounds(108, 137, 286, 14);
		contentPane.add(textNombre);
		
		areaGrupo = new JLabel("");
		areaGrupo.setBounds(461, 86, 46, 14);
		contentPane.add(areaGrupo);

	}

	public void agregarListener(ActionListener accion){
		
		btnCerrar.addActionListener(accion);
		
	} //Cierre de método agregarListener
	
} //Cierre de la clase
