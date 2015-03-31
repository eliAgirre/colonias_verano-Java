package manager;

/**
 * Extiende de la clase Exception.
 * 
 * @author EA
 * @since Mayo 14, 2014
 * @version 1.0.0
 */

@SuppressWarnings("serial")
public class ListaVaciaExcepcion extends Exception{
	
	ListaVaciaExcepcion(){
		
		super("No se encuentra en la lista");
		
	}
}