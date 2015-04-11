package manager;

import java.sql.*;

import javax.swing.*;

import view.VentanaEntrada;

/**
 * Contiene tres atributos estáticos que hace referencia a la conexión MYSQL, a la conexión a la base de datos y a la conexión a preparar con la base de datos.
 * 
 * @author EA
 * @since Mayo 12, 2014
 * @version 1.0.0
 * @see	VentanaEntrada
 */

public class GestorBD {
	
	//Atributos de la clase
	private static Connection conexionMYSQL=null;
	private static Statement stmt = null;
	private static PreparedStatement ps=null;
	
	/**
     * Para conectarse al Driver y poder usar MySQL.
     * 
     * @throws SQLException Contiene información sobre un error de acceso a la base de datos.
     */
	public static void conectar() throws SQLException {
        try {
        	Class.forName("org.gjt.mm.mysql.Driver"); //El driver
        	
        	// Conexión remoto clearBD MySQL pivotal
        	//conexionMYSQL= DriverManager.getConnection("jdbc:mysql://us-cdbr-aws-east-105.cleardb.net:3306/ad_033c9629217c759","bb76fcb43f807b", "6776566b");

        	// Conexión remoto clearBD MySQL OpenShift
        	conexionMYSQL= DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-02.cleardb.net:3306/ad_15583adf16d2d86","b3905f855460a9","9fdb9c4f");
        	
 
        } catch (Exception e) {
        	
        	//Aviso por JOptionPane cuando la conexión no es posible realizarla.
        	JOptionPane.showMessageDialog(null, "Imposible realizar la conexion a BD.","Error ",JOptionPane.ERROR_MESSAGE);
        }
    }  //cierre del método conexión con la mysql

	
	 /**
     * Establece la conexión con la base de datos.
     *
     * @return Statement Devuelve la conexión con la base de datos
     */
	public static Statement conexion() {
		
        try {
        	
        	 stmt = conexionMYSQL.createStatement();
        	
        } catch (SQLException e) {
        	
        	//Aviso por JOptionPane cuando la conexión es incorrecta.
        	JOptionPane.showMessageDialog(null, "Conexión incorrecta.","Error ",JOptionPane.ERROR_MESSAGE);
        }
        
        return stmt;
       
    }  //cierre del método establecer la conexión
	
	 /**
     * Cierra la consulta.
     *
     * @param rs Es el resultado de la consulta.
     * @throws SQLException Contiene información sobre un error de ResultSet
     */
	public static void cerrarConsulta(ResultSet rs) throws SQLException {
		 
		 if (rs!= null){ //Si no hay datos
			 
            try{
            	
                rs.close(); //cierre de la consulta
                
            }catch (Exception e){
            	
            	//Aviso por JOptionPane cuando no se ha podido cerrar la consulta.
            	JOptionPane.showMessageDialog(null, "No es posible cerrar la consulta.","Error ",JOptionPane.ERROR_MESSAGE);
            }
            
	     }
	} //cierre del método cerrar resultSet
		
	 /**
     * Cierra la conexión con la MySQL.
     *
     * @throws SQLException Contiene información sobre un error del cierre de conexión.
     */
	 public static void cerrar() throws SQLException {

        try{
        	
        	 conexionMYSQL.close(); //cerrar la conexión MySQL.
        	 
        }catch(Exception e){

        	//Aviso por JOptionPane cuando no se ha podido cerrar la conexión con la MySQL.
    		JOptionPane.showMessageDialog(null, "Mal al cerrar la conexion","Error ",JOptionPane.ERROR_MESSAGE);
        }
	 } //cierre del método cerrar mysql
	 
	 /**
	   * Sirve para realizar consultas del tipo: SELECT * FROM tabla WHERE..."
	   *
	   * @param stmt Sirve para la conexión con la base de datos
	   * @param cadena La consulta en concreto.
	   * @throws SQLException Contiene información sobre un error con la sentencia SQL.
	   * @return ResultSet Devuelve el resultado de la consulta realizada.
	   */
	 public static ResultSet consulta(Statement stmt,String cadena) throws SQLException {
		 
		 ResultSet rs = null; //Se define el atributo ResultSet como null
		 
		 try {
			 
	 		stmt = conexionMYSQL.prepareStatement(cadena); //Se establece la conexión con la MySQL pasando como parámetro una sentencia de sql.
            rs = stmt.executeQuery(cadena); //Establece el valor del ResultSet ejecutando la consulta dependiendo de la sql. 
            
		 }catch (SQLException sql) {
			 
			//Aviso por JOptionPane cuando la cadena no está escrita adecuadamente.
			JOptionPane.showMessageDialog(null, "Error con: " + cadena,"Error ",JOptionPane.ERROR_MESSAGE);
			
			//Aviso por JOptionPane cuando se he producido un error de SQL.
           	JOptionPane.showMessageDialog(null, "SQLException: " + sql.getMessage(),"SQL ",JOptionPane.ERROR_MESSAGE);
		 }
		 
		 return rs; //devuelve el valor del ResultSet
		 
	 }//cierre del método consulta
	 
	 /**
     * Realiza las consultas de actualización o inserción. No retorna nada pero si la cadena no es adecuada el resultado es -1.
     *
     * @param cadena La consulta en concreto
     */
	public static void consultaActualiza(String cadena){
		 
			@SuppressWarnings("unused")
			int rs=-1; //Definir el ResultSet como -1 por defecto si la cadena sql no es adecuada.
			
	        try {
	        	
	            rs = stmt.executeUpdate(cadena); //Actualiza los datos en la base de datos pasando los datos de la cadena sql.
	            
	        }catch (SQLException sql) {
	        	
	        	//Aviso por JOptionPane cuando la cadena no está escrita adecuadamente.
	        	JOptionPane.showMessageDialog(null, "Error con: " + cadena,"Error ",JOptionPane.ERROR_MESSAGE);
	        	
	        	//Aviso por JOptionPane cuando se he producido un error de SQL.
	            JOptionPane.showMessageDialog(null, "SQLException: " + sql.getMessage(),"SQL ",JOptionPane.ERROR_MESSAGE);
	        }
	      
	 } //cierre del método actualización
	 
	/**
     * Elimina los datos de la base de datos.
     *
     * @param cadena La consulta en concreto
     * @param campo El campo para eliminar la fila.
     */
	 public static void eliminarRegistro(String cadena,String campo){
		 
		 try {
		 	
			ps=conexionMYSQL.prepareStatement(cadena); //Prepara la conexión con la base de datos pasando como parámetro la cadena sql.
			ps.setString(1, campo); //establecemos el número de del campo de la tabla matriculas o monitores y el parámetro de dato.
		 	ps.executeUpdate(); //Actualiza la ejecución
		 		
		 } catch (SQLException sql) {
			 
			//Aviso por JOptionPane cuando la cadena no está escrita adecuadamente.
			JOptionPane.showMessageDialog(null, "Error con: " + cadena,"Error ",JOptionPane.ERROR_MESSAGE);
			 
			//Aviso por JOptionPane cuando se he producido un error de SQL.
	        JOptionPane.showMessageDialog(null, "SQLException: " + sql.getMessage(),"SQL ",JOptionPane.ERROR_MESSAGE);
	           	
	     }
	 } //cierre del método eliminar
	 
	/**
     * Elimina los datos de la base de datos.
     *
     * @param cadena La consulta en concreto
     * @param id El id para eliminar la fila correspondiente
     */
	 public static void eliminarRegistro(String cadena,int id){
		 
		 try {
		 	
			ps=conexionMYSQL.prepareStatement(cadena); //Prepara la conexión con la base de datos pasando como parámetro la cadena sql.
			ps.setInt(1, id); //establecemos el número de del campo de la tabla juegos y el parámetro de dato.
		 	ps.executeUpdate(); //Actualiza la ejecución
		 		
		 } catch (SQLException sql) {
			 
			//Aviso por JOptionPane cuando la cadena no está escrita adecuadamente.
			JOptionPane.showMessageDialog(null, "Error con: " + cadena,"Error ",JOptionPane.ERROR_MESSAGE);
			 
			//Aviso por JOptionPane cuando se he producido un error de SQL.
	        JOptionPane.showMessageDialog(null, "SQLException: " + sql.getMessage(),"SQL ",JOptionPane.ERROR_MESSAGE);
	           	
	     }
	 } //cierre del método eliminar
	 
	 /**
     * Elimina los datos de la base de datos.
     *
     * @param cadena La consulta en concreto
     */
	 public static void eliminarDatos(String cadena){
		 
		 try {
		 	
			ps=conexionMYSQL.prepareStatement(cadena); //Prepara la conexión con la base de datos pasando como parámetro la cadena sql.
		 	ps.executeUpdate(); //Actualiza la ejecución
		 		
		 } catch (SQLException sql) {
			 
			//Aviso por JOptionPane cuando la cadena no está escrita adecuadamente.
			JOptionPane.showMessageDialog(null, "Error con: " + cadena,"Error ",JOptionPane.ERROR_MESSAGE);
			 
			//Aviso por JOptionPane cuando se he producido un error de SQL.
	        JOptionPane.showMessageDialog(null, "SQLException: " + sql.getMessage(),"SQL ",JOptionPane.ERROR_MESSAGE);
	           	
	     }
	 } //cierre del método eliminar

} //cierre de la clase
