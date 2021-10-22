package es.florida.AE03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class App {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		Libro libro;
		int lastId = cargarBibliotecaPrevia();

		//Presentamos men�
		int opcion = 0;
		String[] menu = { 
				" 1. Mostrar cat�logo", 
				" 2. Informaci�n libro", 
				" 3. Crear un libro",
				" 4. Actualizar libro", 
				" 5. Borrar libro", 
				" 6. Cerrar biblioteca" 
				};
		
				while (opcion != 6) {
					try {
					if (opcion >= 0 && opcion <= 6) {
						System.out.println("\n\n----------------------");
						System.out.println("\n**********************" + "\n      BIBLIOTECA\n**********************");
						Thread.sleep(200);
						for (int i = 0; i < menu.length; i++) {
							System.out.println(menu[i]);
							Thread.sleep(100);
						}
						System.out.println("\n**********************");
						System.out.print(" Indique una opci�n: ");

						BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
						opcion = Integer.parseInt(br.readLine());
						System.out.println("----------------------\n\n");

						//OPCIONES Y LLAMADAS A FUNCIONES SEGUN ELIJA EN EL MENU.
						//Muestra todos los libros de la lista de Libros
						if (opcion == 1) {
							System.out.println("\nCATALOGO (Ordenado por Id)\n--------------------------");
							for (Libro lib : recuperarTodos()) {
								System.out.println(lib.getStringInfoReducida());
							}

						//Muestra informaci�n completa del libro elegido
						} else if (opcion == 2) {
							System.out.println("\nINFORMACION COMPLETA DEL LIBRO\n------------------------------");
							
							String flag ="s";
							while (flag.equals("s")) {
								
								System.out.print("Indique la id del libro que desea consultar: ");
								int id = Integer.parseInt(br.readLine());
								mostarLibro(recuperarLibro(id));
								
								System.out.print("�Desea informaci�n de otro libro? (s/n) ");
								System.out.println();
								flag = br.readLine().toLowerCase();
							}							
							
						//Crea un nuevo objeto Libro y lo guarda en la lista y el fichero
						} else if (opcion == 3) {
							System.out.println("\nCREAR UN NUEVO LIBRO\n-------------------");
							try {
								String id = Integer.toString(lastId + 1); 
								System.out.print("Titulo: "); String titulo = br.readLine();
								System.out.print("Autor: "); String autor = br.readLine();
								System.out.print("A�o publicaci�n: "); String anyo = br.readLine();
								System.out.print("Editorial: "); String editorial = br.readLine();
								System.out.print("N� p�ginas: "); String paginas = br.readLine();
								libro = new Libro(id, titulo, autor, anyo, editorial, paginas);
								lastId++;
								crearLibro(libro);
								System.out.println("\nObjeto creado y guardado correctamente. Id asignada --> "
										+ libro.getId() + "\n");
								actualizarBiblioteca(1);	
								
							} catch (IOException e) {
								e.printStackTrace();
							}

						//Modifica atributos del objeto libro seleccionado
						} else if (opcion == 4) {
							System.out.println("\nMODIFICAR LIBRO\n--------------");
							System.out.print("Indique la id del libro que desea actualizar: ");
							int id = Integer.parseInt(br.readLine());
							actualizaLibro(id);

						//Borra el libro elegido de la lista y del fichero 
						} else if (opcion == 5) {
							System.out.println("\nBORRAR LIBRO\n-------------");
							System.out.print("Indique la id del libro que desea borrar: ");
							int id = Integer.parseInt(br.readLine());

							String libroborrado = recuperarLibro(id).getStringInfoReducida();
							borrarLibro(id);

							System.out.println("La referencia \"" + libroborrado + "\" se ha borrado correctamente");

						//Finaliza la aplicaci�n y realiza copia de seguridad del fichero	
						} else if (opcion == 6) {
							System.out.print("Fin de la aplicaci�n ");
							guardarCopiaSeguridad();
							br.close();
						}
					} else
						opcion = 0;
				} catch (NumberFormatException e) {
					System.out.println("Debe introducir un n�mero");
				}
			}
		}
	
	
	/*M�todo actualizaLibro(int id)
	 * ACTION:	recupera el objeto libro correspondiente a la id que solicita el usuario
	 * accede a este objeto y pregunta que par�metro deseamos cambiar con un submen� que
	 * presenta los originales. Seg�n elige el usuario accedemos al atributo mediante 
	 * los setters de la clase Libro y sustituimos el original por el nuevo. Entra en bucle
	 * por si queremos moddificar alg�n atributo m�s en ese mismo objeto. Cuando termina,  
	 * muestra el libro ya con los cambios y actualiza el fichero "biblioteca".
	 * INPUT:	int con el id del libro que se desea modificar.
	 * OUTPUT:	cambia atributos del objeto y actualiza fichero "biblioteca" */
	public static void actualizaLibro(int id) {
		
		Libro libro = recuperarLibro(id);
		String respuesta = "s";
		try {
			while (respuesta.equals("s")) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("\n�Qu� elemento del libro desea modificar ");
				System.out.println("\n 1. Titulo:	" + libro.getTitulo() + 
						"\n 2. Autor:	" + libro.getAutor() + 
						"\n 3. A�o:	" + libro.getAnyo() + 
						"\n 4. Editorial:	" + libro.getEditorial()+
						"\n 5. N� paginas:	" + libro.getPaginas());
				System.out.print("\n Indique una opci�n: ");
				
				String atributo = br.readLine().toLowerCase();

				switch (atributo) {
				case "1":
					System.out.print("\nNuevo titulo: ");
					String nuevotitulo = br.readLine();
					libro.setTitulo(nuevotitulo);
					break;
					
				case "2": 
					System.out.print("\nNuevo autor: ");
					String nuevoautor = br.readLine();
					libro.setAutor(nuevoautor);
					break;
				
				case "3":
					System.out.print("\nNuevo a�o de publicaci�n: ");
					String nuevoanyo = br.readLine();
					libro.setAnyo(nuevoanyo);
					break;
		
				case "4":
					System.out.print("\nNueva editorial: ");
					String nuevaeditorial = br.readLine();
					libro.setEditorial(nuevaeditorial);
					break;
					
				case "5":
					System.out.print("\nNuevo N� de p�ginas: ");
					String nuevopaginas = br.readLine();
					libro.setPaginas(nuevopaginas);
					break;		
				}
				System.out.print("\n�Desea modificar alg�n elemento m�s? (s/n)");
				respuesta = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			System.out.println("\n...el libro se ha actualzado correctamente:\n");
			mostarLibro(libro);
			actualizarBiblioteca(1);	
	}

	
	/*Metodo guardarCopiaSeguridad()
	 * ACTION:	Crea una copia de seguridad del fichero biblioteca cuando se cierra la app (6)
	 * Invoca a actualizarBiblioteca() pasandole un int que le indica el nombre del fichero
	 * de destino que incluye la fecha y hora del momento en que se guarda.
	 * INPUT: invoca actualizarBiblioteca()
	 * OUTPUT:	le pasa un int a actualizarBiblioteca() que condiciona el fichero de salida*/
	public static void guardarCopiaSeguridad() {
		actualizarBiblioteca(2);
	}
	
	
	/* Metodo borrarLibro()
	 * ACTION: recupera la lista de objetos libro, con un bucle la recorre hasta encontrar el 
	 * que tiene la misma id que la que le han pasado por par�metro, obtiene la posicion de 
	 * este objeto y se lo pasa a la funci�e Biblioteca.deleteLibro() para que lo 
	 * borre. Una vez borrado, actualiza el fichero con la "biblioteca".
	 * INPUT:  "id" del libro que se quiere borrar
	 * OUTPUT: borra objeto de la lista y actualiza biblioteca.xml*/
	public static void borrarLibro(int id) {

		ArrayList<Libro> lista = recuperarTodos();
		int index = -1;
		for (Libro lib : lista) {
			if (Integer.parseInt(lib.getId()) == id) {
				index = lista.indexOf(lib);
			}
		}
		Biblioteca.deleteLibro(index);
		actualizarBiblioteca(1);	
	}
	
	
	/*Metodo mostarLibro(Libro libro)
	 * ACTION: invoca y presenta por consola el metodo getStringInfoCompleta() de la clase
	 * Libro que tiene toda la informaci�n del libro que se ha pasado por par�metro ya formateada.
	 * INPUT: objeto libro que deseamos presentar.
	 * OUTPUT: String formateado con todos los par�metros del objeto Libro*/
	public static void mostarLibro(Libro libro) {
		System.out.println(libro.getStringInfoCompleta());
	}
	
	
	/*M�todo recuperarLibro(int id)
	 * ACTION: encuentra en la lista el objeto Libro que solicita el usuario
	 *  mediante la id. Recupera la lista, la recorreo y cuando coincide la id del 
	 *  objeto con la pasada por par�metro lo copia en una variable. Devuelve el objeto Libro solicitado por el usuario.
	 *  INPUT:	String "id" del libro buscado
	 *  OUTPUT: Objeto Libro correspondiente*/
	public static Libro recuperarLibro(int id) {

		Libro libro = new Libro();
		for (Libro lib : recuperarTodos()) {
			if (Integer.parseInt(lib.getId()) == id) {
				libro = lib;
			}
		}
		return libro;
	}
	
	
	/*M�todo recuperarTodos()
	 * ACTION: obtiene a traves de un getter la lista de objetos de la clase 
	 * Biblioteca, la pasa a un ArrayList que es el que devuelve.
	 * INPUT: vacio
	 * OUTPUT: ArrayList completo de libros*/
	public static ArrayList<Libro> recuperarTodos() {

		ArrayList<Libro> lista = Biblioteca.getListaLibros();
		return lista;
	}
	
	
	/*M�todo crearLibro(Libro lib) 
	 * ACTION:	invoca al m�todo al setter de Biblioteca ("anyadirLibro") pasandole
	 * por par�metro un objeto Libro para que lo incluya en la lista de Libros. Obtiene
	 * el id de este objeto y lo devuelve
	 * INPUT: 	Objeto Libro
	 * OUTPUT:	id del objeto que ha a�adido a la lista */
	public static int crearLibro(Libro lib) {	
		Biblioteca.anyadirLibro(lib);
		return Integer.parseInt(lib.getId());
	}
	
	
	/*M�todo cargarBibliotecaPrevia()
	 * Carga en memoria el contenido del fichero "biblioteca" y a la vez crea los objetos 
	 * libro y los a�ade a la lista usando el m�todo crearLibro(); una vez a terminado 
	 * actualiza el fichero "biblioteca" invocando a "actualizarBiblioteca()". Devuelve 
	 * la id del �ltimo objeto que ha creado 
	 * INPUT:	Nada
	 * OUTPUT:	int con el id del �ltimo objeto creado*/
	public static int cargarBibliotecaPrevia() {

		int lastId = 0;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("libros/biblioteca.xml"));
			NodeList nodeList = document.getElementsByTagName("libro");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.print("\n");
				Element eElement = (Element) node;

				String id = eElement.getAttribute("id");
				String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
				String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
				String anyo = eElement.getElementsByTagName("anyo").item(0).getTextContent();
				String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
				String paginas = eElement.getElementsByTagName("paginas").item(0).getTextContent();
				Libro lib = new Libro(id, titulo, autor, anyo, editorial, paginas);
				crearLibro(lib);
				lastId = Integer.parseInt(id);
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		actualizarBiblioteca(1);
		return lastId;
	}
	
	
	/*M�todo actualizarBiblioteca(int a)
	 ACTION:	obtiene la lista de objetos, la formatea (como XML) y la escribe en un fichero;
	 seg�n se le pase un 1 -> actualiza el fichero biblioteca o un 2 -> crea una copia
	 de seguridad (en el nombre incluye fecha y hora actual para poder localizar la copia
	 m�s reciente).
	 INPUT:		int 1 � 2 para saber si es actualizacion o copia de seguridad.
	 OUTPUT:	Actualiza o crea el fichero con la biblioteca. */
	public static void actualizarBiblioteca(int path) {
		
		ArrayList<Libro> lista = recuperarTodos();
		String fechahora = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());

		String destino="";
		if (path == 1 ) destino = "libros/biblioteca.xml";
		else if (path == 2) destino = "libros/bibilioteca_" + fechahora + ".xml"; //Copia seguridad
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.newDocument();
			
			Element raiz = document.createElement("biblioteca");
			document.appendChild(raiz);

			for (Libro lib : lista) {

				Element libro = document.createElement("libro");
				String id = String.valueOf(lib.getId());
				libro.setAttribute("id", id);
				raiz.appendChild(libro);

				Element titulo = document.createElement("titulo"); 
				titulo.appendChild(document.createTextNode(String.valueOf(lib.getTitulo())));
				libro.appendChild(titulo); 

				Element autor = document.createElement("autor");
				autor.appendChild(document.createTextNode(String.valueOf(lib.getAutor())));
				libro.appendChild(autor);

				Element anyo = document.createElement("anyo");
				anyo.appendChild(document.createTextNode(String.valueOf(lib.getAnyo())));
				libro.appendChild(anyo);

				Element editorial = document.createElement("editorial");
				editorial.appendChild(document.createTextNode(String.valueOf(lib.getEditorial())));
				libro.appendChild(editorial);

				Element paginas = document.createElement("paginas");
				paginas.appendChild(document.createTextNode(String.valueOf(lib.getPaginas())));
				libro.appendChild(paginas);
			}
			
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer aTransformer;

			aTransformer = tranFactory.newTransformer();

			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			aTransformer.setOutputProperty("{http://xml.apache.org/xslt} indent-amount", "4");													
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(document);

			File f = new File(destino);
			FileWriter fw = new FileWriter(f);
			StreamResult result = new StreamResult(fw);
			aTransformer.transform(source, result);

			fw.close();

		} catch (TransformerException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		} 
	}
	
}
