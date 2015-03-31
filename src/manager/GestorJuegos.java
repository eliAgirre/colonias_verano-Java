package manager;

import java.sql.*;
import java.util.*;

import javax.swing.*;

import model.Juego;

/**
 * Es un gestor de juegos.
 * 
 * @author EA
 * @since Mayo 16, 2014
 * @version 1.0.0
 */

public class GestorJuegos {

	//Atributos de la clase
	private ArrayList<Juego> gestorJuegos;
	private String cadena;
	private static Statement stmt;
	private static ResultSet rs;
	private String nombre="",tipo="",objetivo="",descripcion="",grupo="";
	private int id=0;
	
	//Constructor
	public GestorJuegos(){
		
		gestorJuegos=new ArrayList<Juego>();
		
	}
	
	//Buscar Juego mediante el id
	public int buscar(int id) throws ListaVaciaExcepcion{
	
		int posicion=0;
		
		int i=0;
		
		Iterator<Juego> it = gestorJuegos.iterator();
		
		if (!it.hasNext()){
			throw new ListaVaciaExcepcion();
		}
        else{
        	
        	while(it.hasNext()){
        		it.next();
        		
        		if(gestorJuegos.get(i).getId()==id){
        			return posicion;
        		}
        		posicion++;
        		i++;
        	}
        	
        JOptionPane.showMessageDialog(null,"El juego no existe!","NO ENCONTRADO",JOptionPane.WARNING_MESSAGE);
    	return -1;
        }
	}
	
	//Buscar Juego mediante el nombre del juego
	public int buscar(String nombre) throws ListaVaciaExcepcion{
	
		int id=0;
		
		int i=0;
		
		Iterator<Juego> it = gestorJuegos.iterator();
		
		if (!it.hasNext()){
			throw new ListaVaciaExcepcion();
		}
        else{
        	
        	while(it.hasNext()){
        		it.next();
        		
        		if(gestorJuegos.get(i).getNombre().equals(nombre)){
        			id=gestorJuegos.get(i).getId();
        			return id;
        		}
        		id++;
        		i++;
        	}
        	
        JOptionPane.showMessageDialog(null,"El juego no existe!","NO ENCONTRADO",JOptionPane.WARNING_MESSAGE);
    	return -1;
        }
	}
	
	//Eliminar juego
	public void eliminar(int id){

		gestorJuegos.remove(existeId(id));
		
	}
	
	//Comprobar si existe el id
	private int existeId(int id){
		
		boolean encontrado=false;
		int existe=-1;
		int i=0;
		
		while(!encontrado && i<gestorJuegos.size()){
			
			if(gestorJuegos.get(i).getId()==id){
				encontrado=true;
				existe=i; //devuelve la posición del juego
			}
			i++;
		}
		this.eliminarRegistroBD(id);
		return existe;
	}
	
	//obtener el tamaño del arraylist
	public int obtenerLongitud(){
		
		return gestorJuegos.size();
	}
	
	//obtener el la posicion del juego
	public Juego obtenerJuego (int posicion) throws ListaVaciaExcepcion{
		
		if (gestorJuegos.isEmpty()==true){
	           
			throw new ListaVaciaExcepcion();
		}  
		
		return gestorJuegos.get(posicion);
	}
	
	//Insertar los datos obtenidos del arraylist la base de datos
	public void insertarBd() throws SQLException{

		for(int i=0;i<gestorJuegos.size();i++){
		
			id=gestorJuegos.get(i).getId();
			nombre=gestorJuegos.get(i).getNombre();
			tipo=gestorJuegos.get(i).getTipo();
			objetivo=gestorJuegos.get(i).getObjetivo();
			descripcion=gestorJuegos.get(i).getDescripcion();
			grupo=gestorJuegos.get(i).getGrupo();
			
			cadena="INSERT INTO juegos VALUES ('"+id+"','"+nombre+"','"+tipo+"','"+objetivo+"','"+descripcion+"','"+grupo+"');"; //Sentencia de sql para insertar datos en la tabla Juegos
			GestorBD.consultaActualiza(cadena); //Ejecuta la actualización
		}

	}
	
	//Se modifica los datos en el arraylist
	public void modificarDatos(int id,String nombre, String tipo, String objetivo,String descripcion,String grupo){
		
		int i=0;
		Iterator<Juego> it = gestorJuegos.iterator();
		
		try {
		if (!it.hasNext()){
			
			throw new ListaVaciaExcepcion();
			
		}
		else{
			while(it.hasNext()){
        		it.next();
        		
        		if(gestorJuegos.get(i).getId()==id){

        			gestorJuegos.get(i).setNombre(nombre);
        			gestorJuegos.get(i).setTipo(tipo);
        			gestorJuegos.get(i).setObjetivo(objetivo);
        			gestorJuegos.get(i).setDescripcion(descripcion);
        			gestorJuegos.get(i).setGrupo(grupo);

        		}
        		i++;
			}
			
			this.eliminarDatosBD();
		}
		
		} catch (ListaVaciaExcepcion e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
		
	//Obtener los datos de la base de datos
	public void cargarArraylist() throws SQLException{

			
		cadena="SELECT * FROM Juegos;";
		rs=GestorBD.consulta(stmt,cadena);

		while (rs.next()){
			
			id=rs.getInt("id");
			nombre=rs.getString("nombre");
			tipo=rs.getString("tipo");
			objetivo=rs.getString("objetivo");
			descripcion=rs.getString("descripcion");
			grupo=rs.getString("grupo");
			
			Juego j=new Juego (id,nombre,tipo,objetivo,descripcion,grupo);
			gestorJuegos.add(j);
		}

	}
	
	private void eliminarDatosBD(){
		
		cadena="DELETE FROM juegos;"; //Se borra la tabla juegos
		GestorBD.eliminarDatos(cadena); //Ejecuta la consulta 
		
	}
	
	private void eliminarRegistroBD(int id){
		
		cadena="DELETE FROM juegos WHERE id=?;"; //Se borra la fila de la tabla juegos con el id adecuado
		GestorBD.eliminarRegistro(cadena,id); //Ejecuta la consulta 
		
	}
	
} //Cierre de la clase