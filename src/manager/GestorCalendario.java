package manager;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import model.Calendario;

/**
 * Es un gestor de calendario.
 * 
 * @author EA
 * @since Mayo 20, 2014
 * @version 1.0.0
 */

public class GestorCalendario {

	//Atributos de la clase
	private ArrayList<Calendario> gestorCalendario;
	private String cadena;
	private static Statement stmt;
	private static ResultSet rs;
	private String dniMonitor="",nombreResponsable="",apellidoResponsable="",nombreJuego="",objetivo="",descripcion="",grupo="";
	private String dia;
	private String idJuego;
	
	//Constructor
	public GestorCalendario(){
		
		gestorCalendario=new ArrayList<Calendario>();
		
	}
	
	//Buscar Juego
	public int buscar(String idJuego) throws ListaVaciaExcepcion{
	
		int posicion=0;
		
		int i=0;
		
		Iterator<Calendario> it = gestorCalendario.iterator();
		
		if (!it.hasNext()){
			throw new ListaVaciaExcepcion();
		}
        else{
        	
        	while(it.hasNext()){
        		it.next();
        		
        		if(gestorCalendario.get(i).getIdJuego().equals(idJuego)){
        			return posicion;
        		}
        		posicion++;
        		i++;
        	}
    	return -1;
        }
	}
	
	//obtener el tamaño del arraylist
	public int obtenerLongitud(){
		
		return gestorCalendario.size();
	}
	
	//obtener el la posicion del calendario
	public Calendario obtenerCalendario (int posicion) throws ListaVaciaExcepcion{

		if (gestorCalendario.isEmpty()==true){
	           
			throw new ListaVaciaExcepcion();
		}  
		return gestorCalendario.get(posicion);
	}
	
	//Insertar los datos obtenidos
	public void insertarBd() throws SQLException{

		for(int i=0;i<gestorCalendario.size();i++){
		
			dia=gestorCalendario.get(i).getDia();
			dniMonitor=gestorCalendario.get(i).getDniMonitor();
			nombreResponsable=gestorCalendario.get(i).getNombreResponsable();
			apellidoResponsable=gestorCalendario.get(i).getApellidoResponsable();
			grupo=gestorCalendario.get(i).getGrupo();
			idJuego=gestorCalendario.get(i).getIdJuego();
			nombreJuego=gestorCalendario.get(i).getNombreJuego();
			objetivo=gestorCalendario.get(i).getObjetivo();
			descripcion=gestorCalendario.get(i).getDescripcion();
			
			
			cadena="INSERT INTO calendario_"+grupo.toLowerCase()+" VALUES ('"+dia+"','"+dniMonitor+"','"+nombreResponsable+"','"+apellidoResponsable+"','"+grupo+"','"+idJuego+"','"+nombreJuego+"','"+objetivo+"','"+descripcion+"');"; //Sentencia de sql para insertar datos en la tabla Juegos
			GestorBD.consultaActualiza(cadena); //Ejecuta la actualización
		}

	}
		
	//Obtener los datos de la base de datos
	public void cargarArraylist(String grupo) throws SQLException{

			
		cadena="SELECT * FROM calendario_"+grupo+";";
		rs=GestorBD.consulta(stmt,cadena);

		while (rs.next()){
			
			dia=rs.getString("dia");
			dniMonitor=rs.getString("dniMonitor");
			nombreResponsable=rs.getString("nombreResponsable");
			apellidoResponsable=rs.getString("apellidoResponsable");
			grupo=rs.getString("grupo");
			idJuego=rs.getString("idJuego");
			nombreJuego=rs.getString("nombreJuego");
			objetivo=rs.getString("objetivo");
			descripcion=rs.getString("descripcion");
			
			Calendario cal=new Calendario (dia,dniMonitor,nombreResponsable,apellidoResponsable,grupo,idJuego,nombreJuego,objetivo,descripcion);
			gestorCalendario.add(cal);
		}

	}	
	
	//Modificar los datos del juego en el arraylist Calendario 
	public void modificarDatos(String idJuego,String nombreJuego,String objetivo,String descripcion, String grupo){
		
		int i=0;
		Iterator<Calendario> it = gestorCalendario.iterator();
		
		try {
		if (!it.hasNext()){
			
			throw new ListaVaciaExcepcion();
			
		}
		else{
			while(it.hasNext()){
        		it.next();
        		
        		if(gestorCalendario.get(i).getIdJuego().equals(idJuego)){

        			gestorCalendario.get(i).setGrupo(grupo);
        			gestorCalendario.get(i).setNombreJuego(nombreJuego);
        			gestorCalendario.get(i).setObjetivo(objetivo);
        			gestorCalendario.get(i).setDescripcion(descripcion);

        		}
			}
			
		}
		
		} catch (ListaVaciaExcepcion e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	//Eliminar los datos del juego en el arraylist Calendario 
	public void eliminarDatos(String idJuego,String nombreJuego,String objetivo,String descripcion, String grupo){
		
		int i=0;
		Iterator<Calendario> it = gestorCalendario.iterator();
		
		try {
		if (!it.hasNext()){
			
			throw new ListaVaciaExcepcion();
			
		}
		else{
			while(it.hasNext()){
        		it.next();
        		
        		if(gestorCalendario.get(i).getIdJuego().equals(idJuego)){

        			gestorCalendario.get(i).setIdJuego("");
        			gestorCalendario.get(i).setGrupo(grupo);
        			gestorCalendario.get(i).setNombreJuego(nombreJuego);
        			gestorCalendario.get(i).setObjetivo(objetivo);
        			gestorCalendario.get(i).setDescripcion(descripcion);

        		}
			}
			
		}
		
		} catch (ListaVaciaExcepcion e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void eliminarDatosBD(String grupo){
		
		cadena="DELETE FROM calendario_"+grupo+";"; //Se borra la tabla calendario
		GestorBD.eliminarDatos(cadena); //Ejecuta la consulta 
		
	}
	
	//Actualiza los datos obtenidos de la VentanaEditarJuego a la tabla calendario_a o calendario_b
	public void actualizarDatosBD(String grupo,String idJuego) throws SQLException{
		
		cadena = "UPDATE calendario_"+grupo+" SET idJuego='"+" "+"',nombreJuego='"+" "+"', objetivo = '"+" "+"', descripcion = '"+" "+"' WHERE idJuego="+idJuego+";";
		GestorBD.consultaActualiza(cadena);
		
	}
	
	/**
     * Obtiene la hora y la fecha de sistema.
     * 
     * @return ts Devuelve un Timestamp.
     * 
     */
	private Timestamp obtenerHoraSistema(){
		
		//creamos el objeto calendar y obtenemos la hora del sistema
		Calendar cal = Calendar.getInstance();
		
		//Se convierte el objeto calendar a timestamp
		Timestamp ts = new Timestamp(cal.getTimeInMillis()); 
		
		return ts;
		
	} //Cierre del método obtenerHoraSistema
	

	//Crear y escribir en el calendario.log
	public void calendarioLog(String evento,String dia, String nombreMonitor, String apellidoMonitor, String grupo, String idJuego, String nombreJuego,String descripcion){
		
		try {
			
			FileWriter ficheroW=new FileWriter("src/files/calendario.log",true);
			BufferedWriter bW=new BufferedWriter(ficheroW);
			
			Timestamp hora=obtenerHoraSistema();
			
			String datos=evento+"   "+hora +"   "+ dia +"   "+ nombreMonitor+ " "+apellidoMonitor+ "      "+grupo+"        "+idJuego+"      "+nombreJuego+"        "+descripcion;

			bW.write(datos);
			bW.newLine();
			bW.newLine();
			bW.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	} //Cierre del método archivoLog

} //Cierre de la clase