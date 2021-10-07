package es.florida.AE02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
		//fichero_temporal ="temporal";
	}
	
	
	
	
	//Métodos para pasar el nombre de los ficheros a Principal ya que son "private" Getters.
	public String ficheroLectura() {
		return fichero_lectura;
	}
	
	public String ficheroEscritura() {
		return fichero_escritura;
	}
	
	public String ficheroTemporal() {
		return fichero_temporal;
	}
	
	
	
	
	
	/*
	 * Metodo para leer el fichero y almacenarlo en una lista que es la que devuelve
	 */
	public ArrayList<String> contenidofichero(String fichero) {
		
		// Lista donde incluimos las líneas que va leyendo
		ArrayList<String> contenidofichero = new ArrayList<String>(); 

		try {
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
					linea = linea.substring(index + longitud);
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

		for (String lin : contenidofichero) {
			String linea = lin.toLowerCase(); // pasa toda la linea a minúsculas para hacer la busqueda
			if (linea.contains(palabra)) {
				linea = linea.replaceAll(palabra, textoReemplazar);
			}	
			ficheroTextoSustituido.add(linea);
		}
		return ficheroTextoSustituido;
	}
		
		
	
	
	//Este método lee el texto del fichero de lectura y lo copia en el fichero de escritura añadiendo el texto que indiquemos.
	public void anyadirTexto(ArrayList<String> textoSustituido) {
		try {
			//for (String line : textoSustituido) {
				File f1 = new File (textoSustituido);
				
				// File f1 = new File(ficheroLectura());
				File f2 = new File(ficheroEscritura());

				FileReader fr = new FileReader(f1);
				BufferedReader br = new BufferedReader (fr);

				FileWriter fw = new FileWriter(f2);
				BufferedWriter bw = new BufferedWriter(fw);

				String linea = br.readLine();

				while (linea != null) {
					bw.write(linea);
					bw.newLine();
					linea = br.readLine();
				}

				br.close();
				bw.close();
				fr.close();
				fw.close();
			//}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
