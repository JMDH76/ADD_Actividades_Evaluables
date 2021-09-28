package es.florida.AE01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App {

	static String carpetapath;
	static String carpetafichero;
	
	public static void main(String[] args) throws IOException {

		File directorio = new File(args[0]);
		Scanner teclado = new Scanner(System.in);

		// Muestra menú y captura opción elegida
		int opcion = 1;
		while (opcion != 5) {
			System.out.print(
					"\nMENU PRINCIPAL\n\n	1. Mostrar información de la carpeta local\n	2. Crear carpeta en directorio local"
							+ "\n	3. Borrar elemento \n	4. Renombrar elemento\n\n	5. Salir de la aplicación.\n\nElige una opción: ");
			opcion = Integer.parseInt(teclado.nextLine());

			// Menú
			if (opcion == 1)
				getInformacion(directorio);
			else if (opcion == 2) {
				System.out.println(creaCarpeta(directorio, teclado));
				subMenu(teclado);
			} else if (opcion == 3) {
				elimina(directorio, teclado);
			} else if (opcion == 4) {
				System.out.println(renombra(directorio, teclado));
			}
		}
		System.out.print("\nFin del programa");
		teclado.close(); // cerramos al salir del programa para que no afecte a los métodos
	}
	
	
	public static String renombra(File directorio, Scanner teclado) throws IOException {

		String mensaje = "";
		ArrayList<String> elementos = new ArrayList<String>();	//Incluimos todos los elementos de la carpeta para buscarlo con la opcion del submenú

		System.out.println(
				"\n4. RENOMBRAR ELEMENTO\n   Indica qué elemento deseas renombrar en \"" + directorio.getName() + "\"");
		String[] listaArchivos = directorio.list();

		int cont = 0;
		for (String fichero : listaArchivos) { // Muestra por pantalla el contenido de la carpeta padre numerando los elementos para seleccionar cual renombrar como un menú
			cont++;
			System.out.println("	" + cont + ". " + fichero);
			elementos.add(fichero);
		}

		System.out.print("\nElige un elemento: ");			//captura el elemento a renombrar
		int opcionmenu = Integer.parseInt(teclado.nextLine());
		System.out.print("Indica el nuevo nombre: ");		//Introducimos nuevo nombre
		String nuevonombre = teclado.nextLine();

		// Busca en el ArrayList el nombre del elemento correspondienta al numero
		// elegido en "opcion de menu"
		int cont2 = 0;
		for (String elemento : elementos) {
			
			cont2++;
			if (cont2 == opcionmenu) { // Encuentra dentro del ArrayList el elemento elegido y crea los dos objetos File para renombrar
				File f = new File(directorio, elemento);
				File nf = new File(directorio, nuevonombre);
				
				if (!nf.exists()) { // Comprobamos que el nuevo nombre no exista

					if (f.isFile()) { // Si es un fichero comprobamos que lleve una extension buscando el punto en el nuevo nombre
						int index = nf.getName().indexOf(".");
						
						if (index != -1) { // Si lleva el punto renombramos
							
							if (f.renameTo(nf))
								mensaje = "\n	El fichero \""+ f.getName() +"\" se ha renombrado corerctamente:	" + f.getName() + " >>> "
										+ nf.getName();
						} else
							mensaje = "	El fichero debe llevar una extensión\n";
					} else {
						f.renameTo(nf);
						mensaje = "\n	La carpeta \""+ f.getName() +"\" se ha renombrado corerctamente:	" + f.getName() + " >>> " + nf.getName();
					}
				} else
					mensaje = "	El elemento ya existe, elige otro nombre";
			}
		}
		return mensaje;
	}
	
	public static void elimina (File directorio, Scanner teclado) throws IOException {
		
		ArrayList<String> elementos = new ArrayList<String>();
		
		System.out.println("\n3. ELIMINAR ELEMENTO\n   Indica qué elemento deseas eliminar de la carpeta \"" + directorio.getName() + "\"");
		String[] listaArchivos = directorio.list();
		
		int cont = 0;
		for ( String fichero : listaArchivos) {			//Muestra por pantalla el contenido de la carpeta padre numerando los elementos para seleccionar cual borrar como un menú
			cont++;
			System.out.println("	"  + cont + ". " + fichero);
			elementos.add(fichero);	
		}

		System.out.print("\nElige un elemento: ");
		int opcionmenu = Integer.parseInt(teclado.nextLine());

		
		int cont2 = 0;
		for (String elemento : elementos) {
			cont2++;
			if (cont2 == opcionmenu) {
				File f = new File(directorio, elemento);

				if (f.isDirectory()) { // Condicion para borrar directorio con archivos
					String[] listaSubdirectorio = f.list();

					for (String subelemento : listaSubdirectorio) { // Crea lista de elementos del subdirectorio
						File fil = new File(f, subelemento);
						fil.delete(); // borra ardchivos
						if (f.delete()) // Una vez vacio borra el directorio
							System.out.println("Elemento eliminado");
					}

				} else {
					if (f.delete()) { // borra elementos del directorio padre
						System.out.println("Elemento eliminado");
					} else
						System.out.println("Elemento NO eliminado");
				}
			}
		}
	}

	// Saca el submenú (2.1.) para crear un fichero dentro de la carpeta que hemos creado
	public static void subMenu(Scanner teclado) {

		int opcion2 = 1;
		if (carpetapath != null) {
			while (opcion2 != 2) {
				System.out.print("\n	Deseas crear un fichero nuevo en la carpeta \"" + carpetafichero + "\""
						+ "\n	1. Sí\n	2. No	\n\n	Elige una opción: ");
				opcion2 = Integer.parseInt(teclado.nextLine());
				if (opcion2 == 1) {
					System.out.print(creaFichero(carpetapath, teclado));
				} else if (opcion2 == 2) {
					carpetapath = null;
				}
			}
		}
	}
	
	//comprueba que no exista y crea un fichero dentro de la carpeta que hemos creado anteriormente "static String carpetapath"
	public static String creaFichero(String carpetapath, Scanner teclado) {

		//Scanner teclado = new Scanner(System.in);
		System.out.print("\n2.1. CREAR FICHERO\n     Indica un nombre y extensión para el nuevo fichero: ");
		String nombrefichero = teclado.nextLine();

		String mensaje = "";

		int index = nombrefichero.indexOf(".");
		if (index != -1) {												//Condicion para comprobar que el texto lleva un punto para la extension
			if (carpetapath != null) {									//Si se ha creado un directorio previamente deja crear el fichero dentro
				File f = new File(carpetapath, nombrefichero);
				if (!f.exists()) {										//Verificación de que no existe el fichero.
					try {
						if (f.createNewFile()) {						//creamos el fichero
							mensaje = "\n	El fichero \"" + nombrefichero + "\" se ha creado correctamente\n";
						}
					} catch (IOException e) {
						e.printStackTrace();
						mensaje = "\n	El fichero no se ha podio crear\n\n";
					}
				}
			} else
				mensaje = "	Primero debes crear una carpeta que lo contenga\n\n";
		} else
			mensaje = "	El fichero debe llevar una extensión\n";
		return mensaje;
	}
	
	
	//Si no existe, crea una carpeta y devuelve un mensaje de confirmacion
	public static String creaCarpeta(File directorio, Scanner teclado) {

		//Scanner teclado = new Scanner(System.in);
		System.out.print("\n2. CREAR CARPETA\n   Indica un nombre para la nueva carpeta: ");
		String nombrecarpeta = teclado.nextLine();

		String mensaje = "";

		File f = new File(directorio, nombrecarpeta);
		if (!f.exists()) {
			if (f.mkdir()) {
				mensaje = "\n	La carpeta \"" + nombrecarpeta + "\" se ha creado correctamente\n";
				carpetapath = f.toString();
				carpetafichero = nombrecarpeta;
				
			} else 
				mensaje = "\n	La carpeta no se ha podio crear\n";
		} else {
			mensaje = "\n	La carpeta no se ha podio crear\n";
		}
		return mensaje;
	}


	// Extrae la informacion de todos los elementos de la carpeta y los muestra por consola.
	public static void getInformacion(File directorio) {

		String[] listaArchivos = directorio.list(); // crea una lista con el contenido del directorio
		System.out.println("\n1. INFORMACION DE CARPETA\n   Contenido de \"" + directorio.getName() + "\"\n");

		String tipo = "", extension = "", tamanyo = "", espacio = "";
		int elementos = 0;

		for (String archivo : listaArchivos) {

			File f = new File(directorio, archivo); // Tipo de fichero. Creamos objeto File (ruta + nombre)
			String nombre = archivo;
			String rutaabsoluta = f.getAbsolutePath().toString(); // ruta absoluta del fichero o carpeta
			String fechamodif = ultimaModificacion(f); // fecha modificacion
			String oculto = elementoOculto(f);

			if (f.isFile()) {
				int index = archivo.indexOf("."); // capturamos extension del fichero
				extension = archivo.substring(index);
				tipo = "Archivo" + extension;
				tamanyo = Long.toString(f.length()) + " bytes";

				 System.out.println(getStringInfo(nombre, tipo, elementos, tamanyo, espacio, fechamodif, oculto, rutaabsoluta, f));

			} else {
				tipo = "Carpeta";
				elementos = cuentaElementos(f);
				espacio = espacioDisco(f);
				
				System.out.println(getStringInfo(nombre, tipo, elementos, tamanyo, espacio, fechamodif, oculto, rutaabsoluta, f));
			}
		}
	}

	
	// Monta la informacion en un String y se lo pasa a "getInformacion" para que lo muestre por consola
	public static String getStringInfo(String nombre, String tipo, int elementos, String tamanyo, String espacio,
			String fechamodif, String oculto, String rutaabsoluta, File f) {

		String elem = "";
		if (elementos == 1)
			elem = " elemento";
		else
			elem = " elementos ";

		String infoelemento;
		if (f.isFile()) {
			infoelemento = "  " + tipo + ":	" + nombre + "	" + tamanyo + "  " + oculto + "\n  Última modif:	"
					+ fechamodif + "\n  Ubicación:	" + rutaabsoluta
					+ "\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		} else {
			infoelemento = "  Carpeta:	" + nombre + "	" + elementos + elem + "  " + oculto + "\n  Última modif:	"
					+ fechamodif + "\n  Ubicación:	" + rutaabsoluta + "\n" + "  Disco:	" + espacio
					+ "\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		}
		return infoelemento;
	}
	
	
	//Obtiene el espaciototal y disponible en disco y calculamos el ocupado 
	public static String espacioDisco(File f) {
			
		long esptotal = f.getTotalSpace()/1000000000;		//pasamos a GB dividiendo por 1e-9
		long espdisponible = f.getUsableSpace()/1000000000;
		long espocupado = (esptotal - espdisponible);
		
		String espaciototal = Long.toString(esptotal); //convertimos a String dando formato al numero
		String espaciodisponible = Long.toString(espdisponible);
		String espacioocupado = Long.toString(espocupado);

		String salida = espacioocupado + " GB ocupados		" + espaciodisponible + " GB disponibles	" + espaciototal + " GB totales	";
		//String salida = " Esp. Ocupado:	" + espacioocupado + " MB\n Esp. Disp.:	" + espaciodisponible + " MB\n Esp. Total:	" + espaciototal + " MB	";
		
		return salida;
	}
	
	//Cuenta los elementos que hay dentro de un subdirectorio
	public static  int cuentaElementos (File subdirectorio) {
		
		String[] listaArchivos = subdirectorio.list();
		int cont = 0;
		for (String archivos : listaArchivos) {
			cont++;
		}
		return cont;
	}
	
	//Comprueba si elemento está oculto y devuel Sí o no
	public static String elementoOculto(File f) {

		String mensaje = "";
		boolean oculto = f.isHidden();
		if (oculto == true) mensaje = "(Oculto)";
		else mensaje = "";

		return mensaje;
	}
	
	//Extrae a String la fecha de la ultima modificación
	public static String ultimaModificacion(File f) {

		long date = f.lastModified();
		Date fecha = new Date(date);
		String fechamodif = fecha.toString().substring(0, 20);

		return fechamodif;
	}
}
