package es.florida.AE01;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		File directorio = new File(args[0]);
		Scanner teclado = new Scanner(System.in);
		// Muestr menú y captura opción elegida

		int opcion = 1;

		while (opcion != 5) {
			System.out.print(
					"\nQué acción desea realizar:\n\n	1. Mostrar información de la carpeta local\n	2. Crear una carpeta en directorio local\n	\n	5. Salir de la aplicación.\n\nElige una opción: ");
			opcion = Integer.parseInt(teclado.nextLine());

			// Menú
			if (opcion == 1) {
				getInformacion(directorio);

			}
		}
		System.out.print("\nFin del programa");
		teclado.close();
	}

	
	public static void getInformacion(File directorio) {

		String[] listaArchivos = directorio.list(); // crea una lista con el contenido del directorio
		System.out.println("\nContenido de " + directorio.getName() + ":\n");
		String tipo = "";
		String extension = "";
		
		

		for (String archivo : listaArchivos) {
			System.out.println("\nNombre: " + archivo); // Nombre del fichero

			File f = new File(archivo.toString()); // Tipo de fichero. Creamos objeto File para comprobar si es carpeta o fichero /
			int index = archivo.indexOf(".");		
						

			if(index!=-1) {
				extension = archivo.substring(index);
				tipo = "Archivo";
				System.out.println("Tipo: "+ tipo + extension);
				String rutaabs = f.getAbsolutePath().toString();			//ruta absoluta del fichero o carpeta
				System.out.println("Ruta: "+ rutaabs);
			
			long ultimamodificacion = f.lastModified();			//fecha
			Date date = new Date(ultimamodificacion);
			
			String fechamodif = date.toString().substring(0,20);
			System.out.println("Modif: "+ fechamodif);
			} else {
				tipo = "Carpeta";
			}
			
			
			
			
			
			

			
			
		}
	}
	
	public static String getString(String salida) {
		
		return "";
	}
	
	public static String ultimaModificacion(String archivo) {
		
		
		
		return "";
	}
}
