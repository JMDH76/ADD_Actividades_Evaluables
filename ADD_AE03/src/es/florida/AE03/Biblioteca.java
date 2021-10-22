package es.florida.AE03;

import java.util.ArrayList;

public class Biblioteca {

	private static ArrayList<Libro> listalibros = new ArrayList<Libro>();

	
	public Biblioteca() { 
	}

	
	/*Metodo deleteLibro()
	 * ACTION:	SETTER accede a lña lista y borra el elemento 
	 * correspondiente al índice que recibe como parámetro.
	 * INPUT:	int con el indice del objeto a borrar de la lista
	 * OUTPUT: 	borra el objeto de la lista*/
	public static void deleteLibro(int index) {
		listalibros.remove(index);
	}
	
	/*Método anyadirLibro()
	 * ACTION:	SETTER recibe un objeto Libro y lo incluye al 
	 * final de la lista.
	 * INPUT: 	Objeto Libro
	 * OUTPUT:	añade objeto a la lista*/
	public static void anyadirLibro(Libro lib) { 
		listalibros.add(lib);
	}

	/*Método getListaLibros()
	 * ACTION: GETTER devuelve la lista de objetos en un 
	 * ArrayList de objetos Libro
	 * INPUT:	nada
	 * OUTPUT:	lista de libros ArrayList<Libro> */
	public static ArrayList<Libro> getListaLibros() { 
		return listalibros;
	}
}

	

