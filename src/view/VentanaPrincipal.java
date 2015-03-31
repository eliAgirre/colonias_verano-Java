package view;

import manager.GestorCalendario;
import manager.GestorJuegos;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
//import java.util.*;

import controller.*;


/**
 * Extiende de la clase JFrame implementando el ActionListener para los botones de acceso rápido. Además contiene una barra de menú para indicar todas las opciones que contiene la aplicación. 
 * 
 * @author EA
 * @since Mayo 13, 2014
 * @version 1.0.0
 * @see	VentanaEntrada
 * @see ControllerVentanaAcercaDe
 */

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame implements ActionListener{

	//Atributos de la clase
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JLabel lblTipo;
	private JButton btnMatriculas;
	private JButton btnMonitores;
	private JButton btnJuegos;
	private JMenuItem mntmCerrarSesion;
	private JMenuItem mntmSalir;
	private JMenuItem mntmMatriculas;
	private JMenuItem mntmMonitores;
	private JMenuItem mntmJuegos;
	private JMenuItem mntmC_Grupos;
	private JMenuItem mntmC_Juegos;
	private JMenuItem mntmC_Calendario;
	private JMenuItem mntmAcercaDe;
	private JMenu mnEditar;
	private JButton btnAcercaDe;
	private JMenuItem mntmMatricula;
	private JMenuItem mntmMonitor;
	private JButton btnGrupos;
	private JButton btnCalendario;
	private GestorJuegos gestorJuegos=new GestorJuegos();
	private GestorCalendario gestorCal=new GestorCalendario();
	private JMenu mnConsultas;
	
	/**
	 * 
     * Constructor con dos parámetros de tipo String y contiene el método llamado componentes.
     * 
     * @param usuario Define el nombre del usuario de tipo String.
     * @param tipo Define el tipo de usuario a niveles de tipo String.
	 * @throws SQLException 
     * 
     */

	public VentanaPrincipal(String usuario,String tipo) throws SQLException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 326);
		setTitle("Colonias de verano");
		setIconImage(new ImageIcon(getClass().getResource("Img/logo.png")).getImage()); //logo de la ventana
		
		componentes();
		
		gestorJuegos.cargarArraylist();
		gestorCal.cargarArraylist("a");

		lblUsuario.setText("Usuario iniciado: "+usuario);
		lblTipo.setText("Tipo de Usuario: "+tipo);
		
		if(tipo.equals("consulta")){
			
			btnMatriculas.setEnabled(false);
			btnMonitores.setEnabled(false);
			btnJuegos.setEnabled(false);

			mnEditar.setEnabled(false);
			mntmMatriculas.setEnabled(false);
			mntmMonitores.setEnabled(false);
			
			mntmMatricula.setEnabled(false);
			mntmMonitor.setEnabled(false);
			
		}
		if(tipo.equals("monitor")){
			
			btnMatriculas.setEnabled(false);
			btnMonitores.setEnabled(false);

			mntmMatriculas.setEnabled(false);
			mntmMonitores.setEnabled(false);
			
			mntmMatricula.setEnabled(false);
			mntmMonitor.setEnabled(false);
		}

		/*Date abril, mayo; //Declaración de las dos variables llamadas abril y mayo de tipo Date.
		
		abril=new Date(115,3,01,0,0,0); //establece el dia 1 de Abril

		mayo=new Date(115,4,16,0,0,0); //establece el dia 16 de Mayo
		
		//Creación del objeto calendar
        Calendar calendario = Calendar.getInstance();

        int dia, mes; //Declaración de las variables llamadas dia y mes de tipo entero.
        
        dia=calendario.get(Calendar.DAY_OF_MONTH); //Obtiene el dia
  		mes=calendario.get(Calendar.MONTH); //Obtiene el mes
  		
  		Date hoy=new Date(115,mes,dia,0,0,0); //establece el dia y el mes
  		
  		if(hoy.before(abril)||hoy.after(mayo)==true){  //Si el dia es antes del dia 1 de Abril o despues del 16 de Mayo
  			
  			//Deshabilitados
  			btnMatriculas.setEnabled(false);
  			mntmMatricula.setEnabled(false);
  			mntmMatriculas.setEnabled(false);
  			
  		}
  		else{

  			//Habilitados
  			btnMatriculas.setEnabled(true);
  			mntmMatricula.setEnabled(true);
  			mntmMatriculas.setEnabled(true);
  		}
  		
  		
  		abril=new Date(115,3,16,0,0,0); //establece el dia 16 de Abril

		mayo=new Date(115,4,9,0,0,0); //establece el dia 9 de Mayo
		

		if(hoy.before(abril)||hoy.after(mayo)==true){  //Si el dia es antes del dia 16 de Abril o despues del 9 de Mayo
  			
  			//Botón deshabilitado
			btnMonitores.setEnabled(false);
			//Menú item deshabilitados
			mntmMonitor.setEnabled(false);
			mntmMonitores.setEnabled(false);

  		}
  		else{

  			//Botón habilitado
  			btnMonitores.setEnabled(true);
			//Menú item habilitados
  			mntmMonitor.setEnabled(true);
  			mntmMonitores.setEnabled(true);
  		}
		
		abril=new Date(115,4,10,0,0,0); //establece el dia 10 de Mayo
		
		if(hoy.after(mayo)){  //Si el dia es después del dia 10 de May serán habilitados
  			
  			//habilitados
			btnGrupos.setEnabled(true);
			btnJuegos.setEnabled(true);
			mntmJuegos.setEnabled(true);
			btnCalendario.setEnabled(true);
			mnConsultas.setEnabled(true);

  		}
  		else{

  			//deshabilitados
  			btnGrupos.setEnabled(false);
  			btnJuegos.setEnabled(false);
			mntmJuegos.setEnabled(false);
			btnCalendario.setEnabled(false);
			mnConsultas.setEnabled(false);
			
			if(tipo.equals("administrador")){
  				
				mnConsultas.setEnabled(true);
				mntmC_Grupos.setEnabled(true);
				btnGrupos.setEnabled(true);
				mntmC_Juegos.setEnabled(false);
				mntmC_Calendario.setEnabled(false);
  			}

  		}*/
		
		if(gestorJuegos.obtenerLongitud()==0){ //Si el ArrayList de la clase GestorJuegos está vacío el botón y el menú de juegos estarán deshabilitado.
			
			btnJuegos.setEnabled(false);
			mntmC_Juegos.setEnabled(false);
			mntmJuegos.setEnabled(false);
		}
		
		if(gestorCal.obtenerLongitud()==0){ //Si el ArrayList de la clase GestorCalendario está vacío el botón y el menú de calendario estará deshabilitado.
			
			btnCalendario.setEnabled(false);
			mntmC_Calendario.setEnabled(false);
		}
	}

	/**
     * Contiene todos los componentes para aparecer en la VentanaPrincipal.
     *  
     */
	public void componentes(){
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//contenedor de menús
		JMenuBar menuBar = new JMenuBar(); 
		menuBar.setBounds(0, 0, 581, 21);
		contentPane.add(menuBar);
		
		//menú
		JMenu mnArchivo = new JMenu("Archivo"); 
		menuBar.add(mnArchivo);
		
		mntmMatricula = new JMenuItem("Matricula");
		mntmMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/anadirMatricula.png"))); //Imagen de añadir la matricula
		mnArchivo.add(mntmMatricula);
		
		mntmMonitor = new JMenuItem("Monitor");
		mntmMonitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/anadirMonitores.png"))); //Imagen de añadir los monitores
		mnArchivo.add(mntmMonitor);
		
		JSeparator separator = new JSeparator(); //Añadir separador
		mnArchivo.add(separator);
		
		mntmCerrarSesion = new JMenuItem("Cerrar Sesion"); //SubMenu
		mntmCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/cerrarSesion.png"))); //Imagen de cierre de sesion
		mnArchivo.add(mntmCerrarSesion);
		
		separator = new JSeparator(); //Añadir separador
		mnArchivo.add(separator);
				
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/salir.png"))); //Imagen de salir de la aplicación
		mnArchivo.add(mntmSalir);
		
		mnEditar = new JMenu("Editar"); //menú
		menuBar.add(mnEditar);
		
		mntmMatriculas = new JMenuItem("Matriculas"); //SubMenu	
		mntmMatriculas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/editarMatricula.png"))); //Imagen para editar matricula
		mnEditar.add(mntmMatriculas);
		
		mntmMonitores = new JMenuItem("Monitores");
		mntmMonitores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/editarMonitores.png"))); //Imagen para editar la tabla monitores
		mnEditar.add(mntmMonitores);
		
		mntmJuegos = new JMenuItem("Juego");
		mntmJuegos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/editarJuegos.png"))); //Imagen para editar juegos
		mnEditar.add(mntmJuegos);
		
		mnConsultas = new JMenu("Consultas"); //menú
		menuBar.add(mnConsultas);
		
		mntmC_Grupos = new JMenuItem("Grupos"); //SubMenu
		mntmC_Grupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/grupo_icon.png"))); //Imagen de consulta el grupo
		mnConsultas.add(mntmC_Grupos);
		
		mntmC_Juegos = new JMenuItem("Juegos");
		mntmC_Juegos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/juegos_icon.png"))); //Imagen para la consulta de juegos
		mnConsultas.add(mntmC_Juegos);
		
		mntmC_Calendario = new JMenuItem("Calendario");
		mntmC_Calendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/calendar_icon.png"))); //Imagen para consultar el calendario
		mnConsultas.add(mntmC_Calendario );
		
		JMenu mnAyuda = new JMenu("Ayuda"); //menú
		menuBar.add(mnAyuda);
		
		mntmAcercaDe = new JMenuItem("Acerca De"); //SubMenu
		mntmAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/info.png"))); //Imagen acerca de informacion
		mnAyuda.add(mntmAcercaDe);
		
		//Labels
		lblUsuario = new JLabel();
		lblUsuario.setForeground(new Color(205, 92, 92));
		lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblUsuario.setBounds(22, 32, 189, 19);
		contentPane.add(lblUsuario);
		
		lblTipo = new JLabel();
		lblTipo.setForeground(new Color(205, 92, 92));
		lblTipo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblTipo.setBounds(22, 62, 176, 19);
		contentPane.add(lblTipo);
		
		//Botones	
		btnMatriculas = new JButton("Matriculas");
		btnMatriculas.addActionListener((ActionListener)this);
		btnMatriculas.setForeground(new Color(0, 0, 0));
		btnMatriculas.setBackground(new Color(255, 204, 204));
		btnMatriculas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/matricula.png")));		
		btnMatriculas.setBounds(22, 103, 161, 67);
		contentPane.add(btnMatriculas);
				
		btnGrupos = new JButton("Grupos");
		btnGrupos.addActionListener((ActionListener)this);
		btnGrupos.setBackground(new Color(255, 204, 204));
		btnGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/grupo.png")));
		btnGrupos.setBounds(388, 103, 161, 67);
		contentPane.add(btnGrupos);
		
		btnMonitores = new JButton("Monitores");
		btnMonitores.addActionListener((ActionListener)this);
		btnMonitores.setBackground(new Color(255, 204, 204));
		btnMonitores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/monitores.png")));
		btnMonitores.setBounds(204, 103, 161, 67);
		contentPane.add(btnMonitores);
		
		btnJuegos = new JButton("Juegos");
		btnJuegos.addActionListener((ActionListener)this);
		btnJuegos.setBackground(new Color(255, 204, 204));
		btnJuegos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/juegos.png")));
		btnJuegos.setBounds(22, 196, 161, 67);
		contentPane.add(btnJuegos);
		
		btnCalendario = new JButton("Calendario");
		btnCalendario.addActionListener((ActionListener)this);
		btnCalendario.setBackground(new Color(255, 204, 204));
		btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/calendario.png")));
		btnCalendario.setBounds(204, 197, 161, 65);
		contentPane.add(btnCalendario);
		
		btnAcercaDe = new JButton("Acerca De");
		btnAcercaDe.addActionListener((ActionListener)this);
		btnAcercaDe.setBackground(new Color(255, 204, 204));
		btnAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Img/acercaDe.png")));
		btnAcercaDe.setBounds(388, 196, 161, 67);
		contentPane.add(btnAcercaDe);		
	}
	
	//Se agrega el actionListener a cada menu item
	public void agregarListener(ActionListener accion){
		
		mntmMatricula.addActionListener(accion);
		mntmMonitor.addActionListener(accion);
		mntmCerrarSesion.addActionListener(accion);
		mntmSalir.addActionListener(accion);
		mntmMatriculas.addActionListener(accion);
		mntmMonitores.addActionListener(accion);
		mntmJuegos.addActionListener(accion);
		mntmC_Grupos.addActionListener(accion);
		mntmC_Juegos.addActionListener(accion);
		mntmC_Calendario.addActionListener(accion);
		mntmAcercaDe.addActionListener(accion);
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getSource()==btnMatriculas){ //Si se realiza el evento en el botón de Matrícula para añadir matrículas.
			

	  		ControllerVentanaAnadirMatricula ControllerAñadirMatricula=new ControllerVentanaAnadirMatricula();
	  		setVisible(false);
		}
		
		if(evento.getSource()==btnMonitores){ //Si se realiza el evento en el botón de Monitores para añadir monitores.
			
				
			ControllerVentanaAnadirMonitor ControllerAñadirMonitor=new ControllerVentanaAnadirMonitor();
			setVisible(false);

		}

		if(evento.getSource()==btnGrupos){ //Si se realiza el evento en el botón de Grupo para consultar grupos.
			
			ControllerVentanaConsultaGrupos ControllerConsultaGrupos=new ControllerVentanaConsultaGrupos();
			setVisible(false);
			
		}
		
		if(evento.getSource()==btnJuegos){ //Si se realiza el evento en el botón de Juegos para consultar juegos.

			ControllerVentanaConsultarJuegos ControllerConsultaJuegos=new ControllerVentanaConsultarJuegos();
			setVisible(false);
			
		}
		if(evento.getSource()==btnCalendario){ //Si se realiza el evento en el botón de Calendario para consultar calendario.

		String grupo=JOptionPane.showInputDialog("Introduce el grupo (A o B):");

			if(null==grupo){ //Si el usuario pulsa "Cancelar" el cuadro de diálogo se cerrará.
				
				setVisible(true);
			}
			else{ //Si el usuario pulsa "Aceptar".
			
				try{
					boolean valido=false;
					
					while (grupo.equals("")){
						
						JOptionPane.showMessageDialog(null, "Introduce el grupo A o B","Error", JOptionPane.WARNING_MESSAGE);
						grupo=JOptionPane.showInputDialog("Introduce el grupo (A o B):");
						
					}
					
					while(valido==false){
					
						grupo=grupo.toLowerCase();
						
						if(!(grupo.equals("a")||grupo.equals("b"))){
							JOptionPane.showMessageDialog(null, "Introduce el grupo A o B","Error", JOptionPane.WARNING_MESSAGE);
							grupo=JOptionPane.showInputDialog("Introduce el grupo (A o B):");
						}
						else{
							
							if(grupo.equals("a")||grupo.equals("b")){
								 ControllerVentanaConsultarCalendario ControllerECalendario = new ControllerVentanaConsultarCalendario(grupo);
								 setVisible(false);
									valido=true;
							}
						}
						
					}
					
				}catch(NullPointerException en){
				}
			}
			
		}
		
		if(evento.getSource()==btnAcercaDe){ //Si se realiza el evento en el botón de Acerca De para aparecerá un frame con cierta información sobre la aplicación.
			
			ControllerVentanaAcercaDe ControllerAcercaDe=new ControllerVentanaAcercaDe();
			setVisible(false);
		}
		
	}
}

