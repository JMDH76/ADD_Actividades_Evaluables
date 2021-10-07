package es.florida.AE02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Modelo {
	
	
	/*Creamos las variables para nombrar los ficheros*/
	private String fichero_lectura;
	private String fichero_escritura;
	private String fichero_temporal;
	
	/*Este metodo lo usamos para dar nombre a los ficheros de lectura y  de escritura. Constructor*/
	public Modelo() {
		fichero_lectura = "AE02_T1_2_Streams_Groucho.txt";
		fichero_escritura = "Groucho_copia.txt";
	}
	

	
	//Métodos para pasar el nombre de los ficheros a Principal, son Getters.
	public String ficheroLectura() {
		return fichero_lectura;
	}
	
	public String ficheroEscritura() {
		return fichero_escritura;
	}


	
	/* Metodo para leer el fichero y almacenarlo en una lista que es la que se devuelve*/
	public ArrayList<String> contenidofichero(String fichero) {
		
		// Lista donde incluimos las líneas que va leyendo
		ArrayList<String> contenidofichero = new ArrayList<String>(); 

		try {
			//Lectura del fichero
			File f = new File(fichero);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			String linea = br.readLine(); // Linea será la linea actual en el buffer de lectura

			// Mientras haya lineas añade a la lista la línea del buffer y carga la
			// siguiente línea en el buffer
			while (linea != null) {
				contenidofichero.add(linea);
				linea = br.readLine();
			}
			br.close();
			fr.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return contenidofichero;
	}
	
	
	
	/*Metodo busca los caracteres consecutivos dentro del texto aunque estén incluidos dentro de una palabara*/
	public int buscarPalabra(ArrayList<String> contenidofichero, String palabrabusqueda) {
		
		String palabra = palabrabusqueda.toLowerCase();//hay que pasar la palabra del cajón de la aplicacion. La pasamos a minúsculas.
		int longitud = palabra.length();
		int cont = 0;
		
		for (String lin : contenidofichero) {
			
			String linea = lin.toLowerCase(); //pasa toda la linea a minúsculas para hacer la busqueda
			
			if (lin.contains(palabra)) {
				while (linea.contains(palabra) == true) {
					int index = linea.indexOf(palabra);
					linea = linea.substring(index + longitud); //Le añadimos la longitud de la palabra para que no vuelva a detectarla
					cont++;
				}
			}
		}
		return cont;
	}
	
	
	
	// Metodo para sustituir las palabras
	public ArrayList<String> reemplazarTexto(ArrayList<String> contenidofichero, String palabrabuscada, String textoReemplazar) {

		ArrayList<String> ficheroTextoSustituido = new ArrayList<String>();
		
		String palabra = palabrabuscada.toLowerCase();

		for (String linea : contenidofichero) {
			String lineacomprobacion = linea.toLowerCase(); // pasa toda la linea a minúsculas para hacer la busqueda
			if (lineacomprobacion.contains(palabra)) {
				linea = linea.replaceAll(palabra, textoReemplazar);
			}	
			ficheroTextoSustituido.add(linea); //Comparamos en minúsculas pero enviamos a la lista la palabra original.
		}
		return ficheroTextoSustituido;
	}
		
		
	
	
	//Este método lee el array con el texto ya sustituido y lo copia en el fichero de escritura.
	public void anyadirTexto(ArrayList<String> contenidofichero, String palabrabuscada, String textoReemplazar) {

		try {
			//Sin Buffer porque leemos línea a línea
			FileWriter fw = new FileWriter(ficheroEscritura()); 
		
			//Invocamos en el bucle al método "reemplazarTexto"
			for (String linea : reemplazarTexto(contenidofichero, palabrabuscada, textoReemplazar)) {
				fw.write(linea + "\n");	//Copiamos linea a linea de la lista en el fichero de escritura
				System.out.println(linea);
			}
			fw.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
