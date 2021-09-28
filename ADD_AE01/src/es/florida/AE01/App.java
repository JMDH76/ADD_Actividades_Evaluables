package es.florida.AE01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App {

/*	Comment:
 	Método: main		
	Descripción: lanza el menú principal de opciones llamando a los demás métodos directa o indirectamente.
	INPUT:	ruta de acceso a la carpeta de trabajo desde argumento
	OUTPUT: menú principal de opciones  */	
	public static void main(String[] args) throws IOException {

		File directorio = new File(args[0]);
		Scanner teclado = new Scanner(System.in);

		// Muestra menú principal y captura opción elegida. Según la opción llama al método correspondiente
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
				System.out.println(creaCarpeta(directorio, teclado, args));
				subMenu(teclado, args);
			} else if (opcion == 3) {
				elimina(directorio, teclado);
			} else if (opcion == 4) {
				System.out.println(renombra(directorio, teclado));
			}
		}
		System.out.print("\nFin del programa");
		teclado.close();	// cerramos al salir del programa para que no afecte a los métodos
	}
	
	
/*	Comment:
 	Método: renombra
	Descripción: 	cambia el nonbre a un elemento (archivo o carpeta) después de comprobar el tipo de elemento que es y si tiene o no permisos de escritura
	INPUT:		directorio y objeto Scanner para poder capturar respuesta usuario
	OUTPUT: 	mensaje con resultado de la operacion*/
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

		// Busca en el ArrayList el nombre del elemento correspondienta al numero elegido en "opcion de menu"
		int cont2 = 0;
		for (String elemento : elementos) {
			
			cont2++;
			if (cont2 == opcionmenu) { // Encuentra dentro del ArrayList el elemento elegido y crea los dos objetos File para renombrar
				File f = new File(directorio, elemento);
				File nf = new File(directorio, nuevonombre);
				if (f.canWrite()) {		//Comprobamos si el elemento original tiene permiso de escritura
					
					if (!nf.exists()) { // Comprobamos que el nuevo nombre no exista

						if (f.isFile()) { // Si es un fichero comprobamos que lleve una extension buscando el punto en el nuevo nombre

							int index = nf.getName().indexOf(".");
							if (index != -1) { // Si lleva el punto (extension) renombramos

								if (f.renameTo(nf))
									mensaje = "\n	El fichero \"" + f.getName()
											+ "\" se ha renombrado corerctamente:	" + f.getName() + " >>> "
											+ nf.getName();
							} else
								mensaje = "	El fichero debe llevar una extensión\n";

						} else {
							f.renameTo(nf);
							mensaje = "\n	La carpeta \"" + f.getName() + "\" se ha renombrado corerctamente:	"
									+ f.getName() + " >>> " + nf.getName();
						}
					} else
						mensaje = "	El elemento ya existe, elige otro nombre";
				}else 
					mensaje = " El elemento está protegido contra escritura";
			}
		}
		return mensaje;
	}
	
	
/*	Comment:
 	Método: elimina
	Descripción: elimina los ficheros y carpetas contenidas en la carpeta de trabajo. Si hay una carpeta 
	con archivos dentro, accede a esta y borra los ficheros antes de borrar la carpeta. Las carpetas vacías 
	las borra directamente como los ficheros. Aplicamos control de excepciones.
	INPUT:	directorio y objeto Scanner teclado para poder capturar selección. 
	OUTPUT: Mensaje con resultado de la operacion		*/
	public static void elimina (File directorio, Scanner teclado) throws IOException {
		
		ArrayList<String> elementos = new ArrayList<String>();	//Incluimos todos los elementos de la carpeta para buscarlo con la opcion del submenú después
		
		System.out.println("\n3. ELIMINAR ELEMENTO\n   Indica qué elemento deseas eliminar de la carpeta \"" + directorio.getName() + "\"");
		String[] listaArchivos = directorio.list();
		
		//Muestra por pantalla el contenido de la carpeta padre numerando los elementos y los añade al ArrayList
		int cont = 0;
		for ( String fichero : listaArchivos) {			
			cont++;
			System.out.println("	"  + cont + ". " + fichero);
			elementos.add(fichero);	
		}

		System.out.print("\nElige un elemento: ");
		int opcionmenu = Integer.parseInt(teclado.nextLine());

		// Recorremos el ArrayList para buscar la posicion igual a la opcion elegida y
		// convierte a objeto File y aplica condiciones
		int cont2 = 0;
		for (String elemento : elementos) {
			cont2++;
			if (cont2 == opcionmenu) {
				File f = new File(directorio, elemento);

				// Condicion para borrar directorio
				
				if (f.isDirectory()) {
					String[] listaSubdirectorio = f.list(); // Crea lista de elementos del subdirectorio
					int elemcarpeta = cuentaElementos (f);

					if (elemcarpeta > 0) {		//borra directorio con archivos
						for (String subelemento : listaSubdirectorio) {
							File fil = new File(f, subelemento);
							fil.delete(); 		// borra ardchivos del subdirectorio
							if (f.delete()) 	// Una vez vacio borra el directorio
								System.out.println("	Elemento eliminado correctamente");
						}
					} else if (f.delete())		//borra directorio vacio
						System.out.println("	Elemento eliminado correctamente");
				} else {
					if (f.delete()) { 			// borra archivos
						System.out.println("	Elemento eliminado correctamente");
					} else
						System.out.println("	Elemento NO eliminado");
				}
			}
		}
	}
	
/*	Comment:
 	Método: subMenu
	Descripción: submenu emergente (2.1.) cuando hemos creado una nueva carpeta, nos da opción de crear ficheros dentro de esta
	INPUT:	Objeto Scanner teclado para poder capturar y String de argumentos.
	OUTPUT:  devuelve a "args[1]" el nuevo path donde crear el fichero o "null" si se elige la opción 2 */	
	public static void subMenu(Scanner teclado, String [] args) {

		//Pasamos a String con path a objeto Path y así poder extraer el nombre de la nueva carpeta contenedora
		//Path nuevopath = Paths.get(carpetapath);
		Path nuevopath = Paths.get(args[1]);

		int opcion2 = 1;
		if (args[1] != null) {
			while (opcion2 != 2) {
				System.out.print("\n	Deseas crear un fichero nuevo en la carpeta \"" + nuevopath.getFileName() + "\""
						+ "\n	1. Sí\n	2. No	\n\n	Elige una opción: ");
				opcion2 = Integer.parseInt(teclado.nextLine());
				if (opcion2 == 1) {
					System.out.print(creaFichero(args[1], teclado));
				} else if (opcion2 == 2) {
					args[1] = null;
				}
			}
		}
	}
	
		
/*	Comment:
 	Método: creaFichero
	Descripción: crea un fichero nuevo dentro de la carpeta que hemos creado previamente. 
	Para ello, comprueba si existe y si lleva extension buscando el ".". No se puede crear si 
	no hemos creado una carpeta nueva en la misma ejecución. Hacemos una captura de errores cuando se crea.
	INPUT:	String "carpetapath" con la direccion del directorio padre y Objeto Scanner para capturar.
	OUTPUT: Mensaje con resultado de la operacion */	
	public static String creaFichero(String carpetapath, Scanner teclado) {

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
	
	
/*	Comment:
 	Método: creaCarpeta
	Descripción: crea una carpeta nueva en el directorio de trabajo. Primero comprueba que no exista previamente.
	Cuando la crea pasamos el path de esta a "args[1]" para poder crear los ficheros dentro después.
	INPUT:	Objetos File directorio y Scanner teclado.
	OUTPUT: String con mensaje del resultado de la operación. */
	public static String creaCarpeta(File directorio, Scanner teclado, String [ ] args) {

		System.out.print("\n2. CREAR CARPETA\n   Indica un nombre para la nueva carpeta: ");
		String nombrecarpeta = teclado.nextLine();

		String mensaje = "";

		File f = new File(directorio, nombrecarpeta);
		if (!f.exists()) {
			if (f.mkdir()) {
				mensaje = "\n	La carpeta \"" + nombrecarpeta + "\" se ha creado correctamente\n";
				args [1] = f.toString();
			} else 
				mensaje = "\n	La carpeta no se ha podio crear\n";
		} else {
			mensaje = "\n	La carpeta no se ha podio crear\n";
		}
		return mensaje;
	}


/*	Comment:
 	Método: getInformacion
	Descripción: hace un listado del contenido de la carpeta de trabajo, la cual recorremos extrayendo 
	las características de cada elemento en función de si es archivo o carpeta. Luego las mete en sendas Listas
	para acabar presentando por los archivos y las carpetas unos a continuación del otro. 
	INPUT:	Objeto directorio
	OUTPUT: Arrays con los datos de cada uno de los elementos primeroarchivos a continuación carpetas */
	public static void getInformacion(File directorio) {

		//Usamos las lista para presentar ordenados los ficheros y después las carpetas y que no salgan mezcladas por consola
		ArrayList<String> listacarpetas = new ArrayList<String>();
		ArrayList<String> listaarchivos = new ArrayList<String>();
		
		String[] listaArchivos = directorio.list(); // crea una lista con el contenido del directorio
		System.out.println("\n1. INFORMACION DE CARPETA\n   Contenido de \"" + directorio.getName() + "\"\n");

		String tipo = "", extension = "", tamanyo = "", espacio = "";
		int elementos = 0;

		//Extrae los datos de cada objeto y los incluye en el ArrayList correspondiente para presentar 
		for (String archivo : listaArchivos) {

			File f = new File(directorio, archivo); 				// Tipo de fichero. Creamos objeto File (ruta + nombre)
			String nombre = archivo;
			
			String rutaabsoluta = f.getAbsolutePath().toString(); 	// ruta absoluta del fichero o carpeta
			String fechamodif = ultimaModificacion(f); 				// fecha modificacion
			String oculto = elementoOculto(f);

			//Añadimos características específicas
			if (f.isFile()) {
				int index = archivo.indexOf("."); // capturamos extension del fichero
				extension = archivo.substring(index);
				tipo = "Archivo" + extension;
				tamanyo = Long.toString(f.length()) + " bytes";
				listaarchivos.add(getStringInfo(nombre, tipo, elementos, tamanyo, espacio, fechamodif, oculto, rutaabsoluta, f));
				
			} else {
				tipo = "Carpeta";
				elementos = cuentaElementos(f);
				espacio = espacioDisco(f);
				listacarpetas.add(getStringInfo(nombre, tipo, elementos, tamanyo, espacio, fechamodif, oculto, rutaabsoluta, f));
			}
		}
		//Presentamos de forma ordenada leyendo primera la lista de archivos y después la de carpetas
		for (String archivo : listaarchivos) {
			System.out.println(archivo);
		}
		for (String carpeta : listacarpetas) {
			System.out.println(carpeta);
		}
	}

	
/*	Comment:
 	Método: getStringInfo
	Descripción: da formato al String de salida de getInformacion en función de si es fichero o carpeta
	INPUT:	Carácterísticas de cada elemento (nombre, tipo, elementos, tamaño, espacio, fecha de modificacion, 
	oculto? y ruta absoluta).
	OUTPUT:   String formateado para incluir en las listas de getInformacion		*/
	public static String getStringInfo(String nombre, String tipo, int elementos, String tamanyo, String espacio,
			String fechamodif, String oculto, String rutaabsoluta, File f) {
		//En caso de varios elementos lo cambia a plural
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
	
	
/*	Comment:
 	Método: espacioDisco
	Descripción: obtiene el espacio total y disponible y calcula el ocupado. Se pasa a GB para presentar
	y se formatea para devolver un String a getInformacion.
	INPUT: 	Objeto File de la iteración actual de getInformación
	OUTPUT: String montado con los 3 datos */
	public static String espacioDisco(File f) {
			
		long esptotal = f.getTotalSpace()/1000000000;		//pasamos a GB dividiendo por 1e-9
		long espdisponible = f.getUsableSpace()/1000000000;
		long espocupado = (esptotal - espdisponible);
		
		String espaciototal = Long.toString(esptotal); 	//convertimos a String dando formato al numero
		String espaciodisponible = Long.toString(espdisponible);
		String espacioocupado = Long.toString(espocupado);

		String salida = espacioocupado + " GB ocupados		" + espaciodisponible + " GB disponibles	" + espaciototal + " GB totales	";
		
		return salida;
	}

	
/*	Comment:
 	Método: cuentaElementos
	Descripción: cuenta los elementos que hay dentro de la carpeta que le pasamos por parámetro
	desde getInformacion. Crea una lista con el contenido y la itera para contar los elementos que contiene
	INPUT:	Objeto File de la iteración actual de getInformacion
	OUTPUT: entero con los elementos que contiene la subcarpeta */
	public static  int cuentaElementos (File subdirectorio) {
		
		String[] listaArchivos = subdirectorio.list();
		int cont = 0;
		for (String archivos : listaArchivos) {
			cont++;
		}
		return cont;
	}
	
	
/*	Comment:
 	Método: elementoOculto
	Descripción: comprueba si el elemento actual de getInformacion está oculto o no 
	devolviendo un mensaje con el resultado 
	INPUT: Objeto File actual en getInformacion
	OUTPUT: String indiocando si está oculto, si no lo está lo devuelve vacio.  */
	public static String elementoOculto(File f) {

		String mensaje = "";
		boolean oculto = f.isHidden();
		if (oculto == true) mensaje = "(Oculto)";
		else mensaje = "";

		return mensaje;
	}
	
	
/*	Comment:
 	Método: ultimaModificacion
	Descripción: obtiene la fecha de la última modificación, la pasa a objeto Date y pasandolo a String
	capturamos la parte que deseamos mostrar
	INPUT: Objeto File de la iteracion actual de getInformacion 
	OUTPUT: String con la fecha y hora  */	
	public static String ultimaModificacion(File f) {

		long date = f.lastModified();
		Date fecha = new Date(date);
		String fechamodif = fecha.toString().substring(0, 20);

		return fechamodif;
	}
}
