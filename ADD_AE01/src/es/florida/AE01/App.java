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
 	M�todo: main		
	Descripci�n: lanza el men� principal de opciones llamando a los dem�s m�todos directa o indirectamente.
	INPUT:	ruta de acceso a la carpeta de trabajo desde argumento
	OUTPUT: men� principal de opciones  */	
	public static void main(String[] args) throws IOException {

		File directorio = new File(args[0]);
		Scanner teclado = new Scanner(System.in);

		// Muestra men� principal y captura opci�n elegida. Seg�n la opci�n llama al m�todo correspondiente
		int opcion = 1;
		while (opcion != 5) {
			System.out.print(
					"\nMENU PRINCIPAL\n\n	1. Mostrar informaci�n de la carpeta local\n	2. Crear carpeta en directorio local"
							+ "\n	3. Borrar elemento \n	4. Renombrar elemento\n\n	5. Salir de la aplicaci�n.\n\nElige una opci�n: ");
			opcion = Integer.parseInt(teclado.nextLine());
			
			// Men�
			if (opcion == 1)
				getInformacion(directorio);
			else if (opcion == 2) {
				System.out.println(creaCarpeta(directorio, teclado, args));
				subMenu(teclado, args);				//Crea el fichero pero s�lo despu�s de crear la carpeta contenedora
			} else if (opcion == 3) {
				elimina(directorio, teclado);
			} else if (opcion == 4) {
				System.out.println(renombra(directorio, teclado));
			}
		}
		System.out.print("\nFin del programa");
		teclado.close();	// cerramos al salir del programa para que no afecte a los m�todos
	}
	
	
/*	Comment:
 	M�todo: renombra
	Descripci�n: 	cambia el nonbre a un elemento (archivo o carpeta) despu�s de comprobar el tipo de elemento que es y si tiene o no permisos de escritura
	INPUT:		Objeto File directorio y objeto Scanner para poder capturar respuesta usuario
	OUTPUT: 	mensaje con resultado de la operacion*/
	public static String renombra(File directorio, Scanner teclado) throws IOException {

		String mensaje = "";
		
		//Llamamos al m�todo "ordenalista()" para presentar el contenido ordenado (1� ficheros, despu�s carpetas)
		ArrayList<String> elementos = ordenalista(directorio);	

		System.out.println(
				"\n4. RENOMBRAR ELEMENTO\n   Indica qu� elemento deseas renombrar en \"" + directorio.getName() + "\"");
		
		int cont = 0;
		for (String fichero : elementos) { // Muestra por pantalla el contenido de la carpeta padre numerando los elementos para seleccionar cual renombrar como un men�
			cont++;
			System.out.println("	" + cont + ". " + fichero);
		}

		//captura el elemento a renombrar y nuevo nombre
		System.out.print("\nElige un elemento: ");			
		int opcionmenu = Integer.parseInt(teclado.nextLine());
		
		
		// Busca en el ArrayList el nombre del elemento correspondienta al numero elegido en "opcion de menu"
		int cont2 = 0;
		String tipo = "";
		for (String elemento : elementos) {
			
			File nom = new File(elemento);
			if(nom.isFile()) tipo = "el archivo";
			else tipo = "la carpeta";
			
			System.out.print("Indica el nuevo nombre para" +tipo+" : ");		
			String nuevonombre = teclado.nextLine();

			
			// Encuentra dentro del ArrayList el elemento elegido y crea los dos objetos File para renombrar
			cont2++;
			if (cont2 == opcionmenu) { 
				File f = new File(directorio, elemento);
				File nf = new File(directorio, nuevonombre);
				
				if (f.canWrite()) {					//Comprobamos si el elemento original tiene permiso de escritura
					
					if (!nf.exists()) { 			// Comprobamos que el nuevo nombre no exista

						if (f.isFile()) { 			// Si es un fichero comprobamos que lleve una extension buscando el punto en el nuevo nombre

							int index = nf.getName().indexOf(".");
							if (index != -1) { 		// Si lleva el punto (extension) renombramos

								if (f.renameTo(nf))
									mensaje = "\n	El fichero \"" + f.getName()
											+ "\" se ha renombrado corerctamente:	" + f.getName() + " >>> "
											+ nf.getName();
							} else
								mensaje = "	El fichero debe llevar una extensi�n\n";

						} else {
							f.renameTo(nf);
							mensaje = "\n	La carpeta \"" + f.getName() + "\" se ha renombrado corerctamente:	"
									+ f.getName() + " >>> " + nf.getName();
						}
					} else
						mensaje = "	El elemento ya existe, elige otro nombre";
				}else 
					mensaje = " El elemento est� protegido contra escritura";
			}
		}
		return mensaje;
	}
	
	
/*	Comment:
 	M�todo:  ordenalista
	Descripci�n: crea una lista con el contenido de una carpeta y la ordena dentro de otra lista que devolveremos
	para presentar por pantalla los elementos oredenados por tipo (Ficheros y Carpetas).
	INPUT:	Objeto File con contenido del directorio a ordenar
	OUTPUT: devuelve un ArrayList con el contenido del directorio ordenado   */
	public static ArrayList<String> ordenalista(File directorio) {
		
		ArrayList<String> archivos = new ArrayList<String>();	//ArrayList para archivos
		ArrayList<String> carpetas = new ArrayList<String>();	//ArrayList para carpetas
		
		//Incluimos todos los elementos de la carpeta para mostrarlos por consola ordenados
		ArrayList<String> elementos = new ArrayList<String>();	
		
		String[] listaArchivos = directorio.list();
		
		//Recorremos la lista y enviamos cada elemento a su ArrayList comprobando si es fichero  o no  ".isFile()"
		for ( String fichero : listaArchivos) {			
			File f = new File(directorio, fichero);
			if (f.isFile())  archivos.add(fichero);
			else carpetas.add(fichero);
		}

		//Unimos los dos arraysList en uno "elementos". Y a est�n primero las carpetas y despu�s los archivos
		
		for (String archivo : archivos) {
			elementos.add(archivo);
		}
		for (String carpeta : carpetas) {
			elementos.add(carpeta);
		}
		return  elementos;
	}
	
	
/*	Comment:
 	M�todo: elimina
	Descripci�n: elimina los ficheros y carpetas contenidas en la carpeta de trabajo. Si hay una carpeta 
	con archivos dentro, accede a esta y borra los ficheros antes de borrar la carpeta. Las carpetas vac�as 
	las borra directamente como los ficheros. Aplicamos control de excepciones.
	INPUT:	directorio y objeto Scanner teclado para poder capturar selecci�n. 
	OUTPUT: Mensaje con resultado de la operacion		*/
	public static void elimina (File directorio, Scanner teclado) throws IOException {
		
		ArrayList<String> elementos = ordenalista(directorio); 	//Ordenamos la lista a presentar
		
		System.out.println("\n3. ELIMINAR ELEMENTO\n   Indica qu� elemento deseas eliminar de la carpeta \"" + directorio.getName() + "\"");

		//Muestra por pantalla el contenido de la carpeta padre numerando los elementos
		int cont = 0;
		for ( String fichero : elementos) {			
			cont++;
			System.out.println("	"  + cont + ". " + fichero);
		}
	
		System.out.print("\nElige un elemento: ");
		int opcionmenu = Integer.parseInt(teclado.nextLine());

		// Recorremos el ArrayList para buscar la posicion igual a la opcion elegida,
		// convierte a objeto File y aplica condiciones
		int cont2 = 0;
		for (String elemento : elementos) {
			cont2++;
			if (cont2 == opcionmenu) {
				File f = new File(directorio, elemento);

				// Condicion para borrar directorios
				if (f.isDirectory()) {
					int elemcarpeta = cuentaElementos (f);	// Llamamos a funcion cuentaElementos para ver si tiene elementos dentro
					
					//Borra directorios con elementos dentro
					if (elemcarpeta > 0) {	
						String[] listaSubdirectorio = f.list(); // Crea lista de elementos del subdirectorio
						for (String subelemento : listaSubdirectorio) {
							File fil = new File(f, subelemento);
							fil.delete(); 		// borra ardchivos del subdirectorio
							if (f.delete()) 	// Una vez vacio borra el directorio
								System.out.println("	Elemento eliminado correctamente");
						}
					//Borra directorio vac�o	
					} else if (f.delete())		
						System.out.println("	Elemento eliminado correctamente");
				} 
				//Borrar archivos
				else {
					if (f.delete()) { 			
						System.out.println("	Elemento eliminado correctamente");
					} else
						System.out.println("	Elemento NO eliminado");
				}
			}
		}
	}
	
/*	Comment:
 	M�todo: subMenu
	Descripci�n: submenu emergente (2.1.) cuando hemos creado una nueva carpeta, nos da opci�n de crear ficheros dentro de esta
	INPUT:	Objeto Scanner teclado para poder capturar y String de argumentos.
	OUTPUT:  devuelve a "args[1]" el nuevo path donde crear el fichero o "null" si se elige la opci�n 2 */	
	public static void subMenu(Scanner teclado, String [] args) {

		//Pasamos a String con path a objeto Path y as� poder extraer el nombre de la nueva carpeta contenedora
		Path nuevopath = Paths.get(args[1]);

		int opcion2 = 1;
		if (args[1] != null) {
			while (opcion2 != 2) {
				System.out.print("\n	Deseas crear un fichero nuevo en la carpeta \"" + nuevopath.getFileName() + "\""
						+ "\n	1. S�\n	2. No	\n\n	Elige una opci�n: ");
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
 	M�todo: creaFichero
	Descripci�n: crea un fichero nuevo dentro de la carpeta que hemos creado previamente. 
	Para ello, comprueba si existe y si lleva extension buscando el ".". No se puede crear si 
	no hemos creado una carpeta nueva en la misma ejecuci�n. Hacemos una captura de errores cuando se crea.
	INPUT:	String "carpetapath" con la direccion del directorio padre y Objeto Scanner para capturar.
	OUTPUT: Mensaje con resultado de la operacion */	
	public static String creaFichero(String carpetapath, Scanner teclado) {

		System.out.print("\n2.1. CREAR FICHERO\n     Indica un nombre y extensi�n para el nuevo fichero: ");
		String nombrefichero = teclado.nextLine();

		String mensaje = "";

		int index = nombrefichero.indexOf(".");
		if (index != -1) {												//Condicion para comprobar que el texto lleva un punto para la extension
			
			if (carpetapath != null) {									//Si se ha creado un directorio previamente deja crear el fichero dentro
				File f = new File(carpetapath, nombrefichero);
				
				if (!f.exists()) {										//Verificaci�n de que no existe el fichero.
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
			mensaje = "	El fichero debe llevar una extensi�n\n";
		return mensaje;
	}
	
	
/*	Comment:
 	M�todo: creaCarpeta
	Descripci�n: crea una carpeta nueva en el directorio de trabajo. Primero comprueba que no exista previamente.
	Cuando la crea pasamos el path de esta a "args[1]" para poder crear los ficheros dentro despu�s.
	INPUT:	Objetos File directorio y Scanner teclado.
	OUTPUT: String con mensaje del resultado de la operaci�n. */
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
 	M�todo: getInformacion
	Descripci�n: hace un listado del contenido de la carpeta de trabajo, la cual recorremos extrayendo 
	las caracter�sticas de cada elemento en funci�n de si es archivo o carpeta. Luego las mete en sendas Listas
	para acabar presentando por los archivos y las carpetas unos a continuaci�n del otro. 
	INPUT:	Objeto directorio
	OUTPUT: Arrays con los datos de cada uno de los elementos primeroarchivos a continuaci�n carpetas */
	public static void getInformacion(File directorio) {

		//Usamos las lista para presentar ordenados los ficheros y despu�s las carpetas y que no salgan mezcladas por consola
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

			//A�adimos caracter�sticas espec�ficas
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
		//Presentamos de forma ordenada leyendo primera la lista de archivos y despu�s la de carpetas
		for (String archivo : listaarchivos) {
			System.out.println(archivo);
		}
		for (String carpeta : listacarpetas) {
			System.out.println(carpeta);
		}
	}

	
/*	Comment:
 	M�todo: getStringInfo
	Descripci�n: da formato al String de salida de getInformacion en funci�n de si es fichero o carpeta
	INPUT:	Car�cter�sticas de cada elemento (nombre, tipo, elementos, tama�o, espacio, fecha de modificacion, 
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
			infoelemento = "  " + tipo + ":	" + nombre + "	" + tamanyo + "  " + oculto + "\n  �ltima modif:	"
					+ fechamodif + "\n  Ubicaci�n:	" + rutaabsoluta
					+ "\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		} else {
			infoelemento = "  Carpeta:	" + nombre + "	" + elementos + elem + "  " + oculto + "\n  �ltima modif:	"
					+ fechamodif + "\n  Ubicaci�n:	" + rutaabsoluta + "\n" + "  Disco:	" + espacio
					+ "\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		}
		return infoelemento;
	}
	
	
/*	Comment:
 	M�todo: espacioDisco
	Descripci�n: obtiene el espacio total y disponible y calcula el ocupado. Se pasa a GB (1e-9) 
	para presentar y se formatea para devolver un String a getInformacion.
	INPUT: 	Objeto File de la iteraci�n actual de getInformaci�n
	OUTPUT: String montado con los 3 datos */
	public static String espacioDisco(File f) {
			
		long esptotal = f.getTotalSpace()/1000000000;		
		long espdisponible = f.getUsableSpace()/1000000000;
		long espocupado = (esptotal - espdisponible);
		
		String espaciototal = Long.toString(esptotal); 		
		String espaciodisponible = Long.toString(espdisponible);
		String espacioocupado = Long.toString(espocupado);

		String salida = espacioocupado + " GB ocupados		" + espaciodisponible + " GB disponibles	" + espaciototal + " GB totales	";
		return salida;
	}

	
/*	Comment:
 	M�todo: cuentaElementos
	Descripci�n: cuenta los elementos que hay dentro de la carpeta que le pasamos por par�metro
	desde getInformacion. Crea una lista con el contenido y la itera para contar los elementos que contiene
	INPUT:	Objeto File de la iteraci�n actual de getInformacion
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
 	M�todo: elementoOculto
	Descripci�n: comprueba si el elemento actual de getInformacion est� oculto o no 
	devolviendo un mensaje con el resultado 
	INPUT: Objeto File actual en getInformacion
	OUTPUT: String indiocando si est� oculto, si no lo est� lo devuelve vacio.  */
	public static String elementoOculto(File f) {

		String mensaje = "";
		boolean oculto = f.isHidden();
		if (oculto == true) mensaje = "(Oculto)";
		else mensaje = "";

		return mensaje;
	}
	
	
/*	Comment:
 	M�todo: ultimaModificacion
	Descripci�n: obtiene la fecha de la �ltima modificaci�n, la pasa a objeto Date y pasandolo a String
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
