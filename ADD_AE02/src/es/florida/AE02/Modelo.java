package es.florida.AE02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Modelo {
	
	/*Creamos las variables privadas para nombrar los ficheros*/
	private String fichero_lectura;
	private String fichero_escritura;
	
	
	/*Constructor de Modelo. Asigna nombre a los ficheros de 
	lectura y  de escritura. */
	public Modelo() {
		fichero_lectura = "AE02_T1_2_Streams_Groucho.txt";
		fichero_escritura = "Groucho_copia.txt";
	}
	

	//Getters para pasar el nombre de los ficheros a Principal.
	public String ficheroLectura() {
		return fichero_lectura;
	}
	
	public String ficheroEscritura() {
		return fichero_escritura;
	}


/*	METODO: contenidoFichero. 
	DESCRIPCION: Le pasamos el String con ubicación del fichero, 
	lo convierte a objeto File, lo lee línea a línea y guarda el contenido en
	el ArrayList "contenidofichero" que es el que devolverá cuando
	sea invocado el método. 
	INPUT: se le pasa el String con la ubicación del fichero que queremos leer. 
	OUTPUT: Lista con el contenido del fichero por líneas	
	*/
	public ArrayList<String> contenidoFichero(String fichero) {

		ArrayList<String> contenidofichero = new ArrayList<String>();

		try {
			File f = new File(fichero);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			String linea = br.readLine();

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
	

/*	METODO: buscarPalabra
	DESCRIPCION:  Cuenta las palabras que hay en el texto que coinciden con el campo de busqueda.
	Recorre el arrayList del texto que nos devuelve contenidoFichero() y cuneta las veces que se 
	repite el conjunto de caracteres indicado en el textField. Pasa la linea y la palabra a 
	minusculas para tener en cuenta las palabras escritas con alguna mayúscula. Cuando encuentra 
	una coincidencia divide la línea en un substring para seguir recorriendola y ver si hay alguna 
	más en esa misma línea.
	INPUT: String con la palabra a buscar
	OUTPUT: Int con la cantidad de veces que se repite ese conjunto de caracteres
		*/	
	public int buscarPalabra(String palabrabuscada) {
	
		String palabra = palabrabuscada.toLowerCase();
		int longitud = palabra.length(); //Para indicar inicio de substring sin contar palabra ya contabilizada.
		
		int cont = 0;
		for (String linea : contenidoFichero(ficheroLectura())) {
	
			String lineacomprobacion = linea.toLowerCase();
			if (lineacomprobacion.contains(palabra)) {
	
				while (lineacomprobacion.contains(palabra) == true) {
					cont++;
					int index = lineacomprobacion.indexOf(palabra);
					lineacomprobacion = lineacomprobacion.substring(index + longitud);
				}
			}
		}
		return cont;
	}
	
	
/*	METODO: reemplazarTexto
	DESCRIPCION: Sustituye las palabras buscadas por la que le indiquemos.
	Recibe la palabra a buscar y por cual hay que sustituirla. 
	Compara la palabra con la línea del fichero original en minúsculas
	y si la contiene (".contains(palabra)") reemplazamos todas las coincidencias 
	por la palabra nueva; enviamos la línea a un nuevo array que será el que devolveremos.	
	INPUT: String con la palabra que buscamos y String con la palabra que la va a sustituir 
	desde los textFields correspondientes
	OUTPUT: Lista de String con las palabras ya sustituidas en las líneas 
	 */	
	public ArrayList<String> reemplazarTexto(String palabrabuscada, String textoReemplazar) {

		ArrayList<String> ficheroTextoSustituido = new ArrayList<String>();
		String palabra = palabrabuscada.toLowerCase();
			
		for (String linea : contenidoFichero(ficheroLectura())) {
			String lineacomprobacion = linea.toLowerCase(); 
			if (lineacomprobacion.contains(palabra)) {		
				linea = lineacomprobacion.replaceAll(palabra, textoReemplazar);
			}	
			ficheroTextoSustituido.add(linea);
		}
		return ficheroTextoSustituido;
	}
		
		
	
	
/*	METODO: copiarFichero
	DESCRIPCION: crea un nuevo fichero con el nombre que asignamos en ficheroEscritura(), llama 
	al método reemplazarTexto() pasándole las palabras para buscar y reemplazar y copia el arrayList
	que esta devuelve en el ficheroEscritura linea a línea.
 	INPUT: String con palabra a buscar y String con palabra a sustituir.
	OUTPUT: No devuelve nada, crea el fichero ficheroEscritura
	 */
	public void copiarFichero(String palabrabuscada, String textoReemplazar) {
	
		try {
			// Sin Buffer porque leemos la lista línea a línea
			FileWriter fw = new FileWriter(ficheroEscritura());
	
			for (String linea : reemplazarTexto(palabrabuscada, textoReemplazar)) {
				fw.write(linea + "\n");
			}
			fw.close();
	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
