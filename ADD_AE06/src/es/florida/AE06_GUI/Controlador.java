package es.florida.AE06_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import org.bson.Document;

import com.mongodb.client.MongoCollection;


public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerCatalogo, actionListenerLibro, actionListenerLimpiar,
			actionListenerEditarLibro, actionListenerNuevoLibro, actionListenerBorrar;

	// Constructor pasamos los parametros desde main en principal
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}

	public void control() {

		mostrarCatalogo(); // Mostramos listado de libros en TextArea al abrir la GUI

		// Actualiza el listado de la base de datos que se muestra
		actionListenerCatalogo = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				//modelo.quitarBlancos(coleccion);
				mostrarCatalogo();
			}
		};
		vista.getBtnCatalogo().addActionListener(actionListenerCatalogo);
	}
	
	
	
	
	/* METODO: 	mostrarCatalogo()
	 * ACTION:	presenta el contenido de la BDD en el TextArea de la GUI
	 * INPUT:	ArrayList de String con el contenido de la BDD
	 * OUTPUT:	Listado de objetos de la BDD en el TextArea. Fecha y Hora de la última actualización*/
	public void mostrarCatalogo() {
		
		vista.getTextArea().setText("\n");
		ArrayList<String> listaLibros = modelo.Catalogo();
		for (String libro : listaLibros) {
			vista.getTextArea().append(" " + libro + "\n");
		}
		vista.getTextArea().append("\n Ultima actualización:\n\t\t" + fechaActual());
	}
	
	
	
	
	
	
	/* METODO: 	capturarTexto()
	 * ACTION:	extrae los valores de los JTextField, los convierte a String y crea un 
	 * objeto libro empleando un constructor diferente en función de la información que
	 * necesitemos.
	 * INPUT: 	recibe un Integer que indica el constructor a usar; 1 > constructor con 'id' / 2 > constructor sin 'id'
	 * OUTPUT: 	devuelve un objeto Libro creado con la información delos JTextField de la GUI*/
//	public Libro capturarTexto(int indicador) {
//
//		Libro libro;
//		String titulo = vista.getTextTitulo().getText().toString();
//		String autor = vista.getTextAutor().getText().toString();
//		String anyonacimiento = vista.getTextNacimnineto().getText().toString();
//		String anyopublicacion = vista.getTextPublicacion().getText().toString();
//		String editorial = vista.getTextEditorial().getText().toString();
//		String paginas = vista.getTextPaginas().getText().toString();
//		if (indicador != 0) {
//			int id = Integer.parseInt(vista.getTextIdConsulta().getText());
//			libro = new Libro(id, titulo, autor, anyonacimiento, anyopublicacion, editorial, paginas);
//		} else
//			libro = new Libro(titulo, autor, anyonacimiento, anyopublicacion, editorial, paginas);
//		return libro;
//	}
	
	
	
	
	/* METODO:	fechaActual()
	 * ACTION: 	obtiene la fecha y hora del momneto en que es invocado
	 * INPUT:	nada
	 * OUTPUT:	String formateado con la fecha y hora actuales*/
	public String fechaActual() {

		LocalDateTime localDate = LocalDateTime.now();
		String fecha = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth() + " "
				+ localDate.getHour() + ":" + localDate.getMinute() + ":" + localDate.getSecond();
		return fecha;
	}
	
	
	
	
	/* METODO:	limpiarCampos() 
	 * ACTION:	borra el contenido de los JTextField escribiendo en blanco en cada uno de ellos(""). 
	 * El campo de ID sólo lo borra si tiene texto si no, provoca un error. 
	 * INPUT:	nada. 
	 * OUTPUT: 	borra el contenido de los JTextField escribiendo ("") en cada uno*/
	public void limpiarCampos() {

		if (!vista.getTextIdConsulta().getText().toString().equals(""))
			vista.getTextIdConsulta().setText("");
		vista.getTextTitulo().setText("");
		vista.getTextAutor().setText("");
		vista.getTextNacimnineto().setText("");
		vista.getTextPublicacion().setText("");
		vista.getTextEditorial().setText("");
		vista.getTextPaginas().setText("");
	}
}
