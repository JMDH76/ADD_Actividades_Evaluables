package es.florida.AE05_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import es.florida.AE05.Libro;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerCatalogo, actionListenerLibro, actionListenerLimpiar,
			actionListenerEditarLibro, actionListenerNuevoLibro, actionListenerBorrar;

	// Constructor pasamos los parametros desde main en principal
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control(); // invocamos control para iniciar la app
	}

	public void control() {

		mostrarCatalogo();

		// Consulta
		actionListenerCatalogo = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				mostrarCatalogo();
			}
		};
		vista.getBtnCatalogo().addActionListener(actionListenerCatalogo);

		// Detalle libro
		actionListenerLibro = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				int id = Integer.parseInt(vista.getTextIdConsulta().getText());
				String[] lib = modelo.consultaLibro(id);

				if (lib[0].equals("Ok")) {
					vista.getTextTitulo().setText(lib[1]);
					vista.getTextAutor().setText(lib[2]);
					vista.getTextNacimnineto().setText(lib[3]);
					vista.getTextPublicacion().setText(lib[4]);
					vista.getTextEditorial().setText(lib[5]);
					vista.getTextPaginas().setText(lib[6]);
				} else {
					JOptionPane.showMessageDialog(null, "Error. ID inexistente");
				}
			}
		};
		vista.getBtnConsulta().addActionListener(actionListenerLibro);

		
		// Nuevo libro
		actionListenerNuevoLibro = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				boolean error = false;
				Libro libro = capturarTexto(0);
				
				try {
					modelo.nuevoLibro(libro);
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, "¡Error!\nNo se ha podido crear la nueva referencia.\nRevise los datos");
					error = true;
				}
				if (error == false) {
					limpiarCampos();
					JOptionPane.showMessageDialog(null,
							"Libro añadido correctamente.\nNueva referencia: " + libro.getId() + ". " + libro.getTitulo());
					mostrarCatalogo();
				}
			}
		};
		vista.getBtnAgregar().addActionListener(actionListenerNuevoLibro);

		
		// Editar libro
		actionListenerEditarLibro = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				boolean error = false;
				Libro libro = capturarTexto(1);
				
				try {
					modelo.editarLibro(libro);
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, "¡Error!\nLa información no se ha podido actualizar.\nRevise los datos");
					error = true;
				}
				if (error == false) {
					limpiarCampos();
					JOptionPane.showMessageDialog(null, "Libro " + libro.getId() + " actualizado correctamente.");
					mostrarCatalogo();
				}
			}
		};
		vista.getBtnActualizar().addActionListener(actionListenerEditarLibro);

		
		// Borrar libro
		actionListenerBorrar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				int id = Integer.parseInt(vista.getTextIdConsulta().getText());
				modelo.borrarLibro(id);

				limpiarCampos();
				JOptionPane.showMessageDialog(null, "Libro con id " + id + " borrado correctamente");
				mostrarCatalogo();
			}
		};
		vista.getBtnBorrar().addActionListener(actionListenerBorrar);

		
		// Limpiar campos
		actionListenerLimpiar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				limpiarCampos();
			}
		};
		vista.getBtnLimpiar().addActionListener(actionListenerLimpiar);
	}

	
	
	
	// FUNCIONES AUXILIARES
	
	public Libro capturarTexto(int indicador) {

		Libro libro;
		String titulo = vista.getTextTitulo().getText().toString();
		String autor = vista.getTextAutor().getText().toString();
		String anyonacimiento = vista.getTextNacimnineto().getText().toString();
		String anyopublicacion = vista.getTextPublicacion().getText().toString();
		String editorial = vista.getTextEditorial().getText().toString();
		String paginas = vista.getTextPaginas().getText().toString();
		if (indicador != 0) {
			int id = Integer.parseInt(vista.getTextIdConsulta().getText());
			libro = new Libro(id, titulo, autor, anyonacimiento, anyopublicacion, editorial, paginas);
		} else
			libro = new Libro(titulo, autor, anyonacimiento, anyopublicacion, editorial, paginas);
		return libro;
	}

	
	public String fechaActual() {

		LocalDateTime localDate = LocalDateTime.now();
		String fecha = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth() + " "
				+ localDate.getHour() + ":" + localDate.getMinute() + ":" + localDate.getSecond();
		return fecha;
	}

	
	public void mostrarCatalogo() {

		vista.getTextArea().setText("\n");
		ArrayList<String> listaLibros = modelo.consultaCatalogo();
		for (String libro : listaLibros) {
			vista.getTextArea().append(" " + libro + "\n");
		}
		vista.getTextArea().append("\n Ultima actualización:\n\t\t" + fechaActual());
	}

	
	
	/*
	 * Método limpiarCampos() Action: borra el contenido de los textArea para no
	 * acumular consultas unas encima de otras. INPUT: nada. OUTPUT: borra el
	 * contenido de los JTextField
	 */
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