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
		System.out.println("Contenido de " + directorio.getName() + ":\n");
		String tipo = "";
		String extension = "";
		String tamanyo = "";
		
		for (String archivo : listaArchivos) {

			File f = new File(directorio + "\\" + archivo);  		//Tipo de fichero. Creamos objeto File (ruta  + nombre)
			
									
			if(f.isFile()) {
				int index = archivo.indexOf("."); 					//capturamos extension del fichero
				extension = archivo.substring(index);
				tipo = "Archivo" + extension;
				tamanyo =  Long.toString(f.length()) + " bytes";
				
				
			} else {
				tipo = "Carpeta";

			}
			
			String nombre = archivo;
			String rutaabs = f.getAbsolutePath().toString(); 			//ruta absoluta del fichero o carpeta
			String fechamodif = ultimaModificacion(f);					//fecha modificacion
			String oculto = elementoOculto(f);
			
			
			
			System.out.println("Nombre: " + archivo);		//*
			System.out.println("Tipo: " + tipo);			//*
			System.out.println("Ruta: " + rutaabs);			//*
			System.out.println("Modif: " + fechamodif);		//*
			System.out.println("Oculto: " + oculto);	//*
			System.out.println("Tamaño: " + tamanyo +"\n");	//*
		}
	}

	public static String getString(String salida) {
		
		return "";
	}
	
	//Comprueba si elemento está oculto y devuel Sí o no
	public static String elementoOculto(File f) {		
		
		String mensaje = "";
		boolean oculto = f.isHidden();
		if (oculto == true) mensaje = "Sí";
		else mensaje = "No";
		return mensaje;
	}
	
	
	public static String ultimaModificacion(File f) {

			long date = f.lastModified();
			Date fecha = new Date(date);
			String fechamodif = fecha.toString().substring(0, 20);

		return fechamodif;
	}
}
